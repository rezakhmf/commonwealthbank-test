package com.reza.commbank.account.presenter

import android.util.Log
import com.reza.commbank.account.model.*
import com.reza.commbank.account.view.IAccountView
import com.reza.commbank.account.view.ITransactionsView
import com.reza.commbank.commBank.util.API
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observables.GroupedObservable
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.ArrayList
import java.util.HashMap
import java.util.stream.Collectors.toMap
import javax.inject.Inject
import javax.inject.Named
import kotlin.reflect.KMutableProperty1


/**
 * Created by reza on 30/11/17.
 */
class AccountPresenter(accountEndpoint: IAccountEndpoint) : IAccountPresenter {




    private var accountEndPoint: IAccountEndpoint = accountEndpoint
    private var accountView: IAccountView? = null
    private var transactionsView: ITransactionsView? = null
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val transactionsItem = ArrayList<ListItem>()


    override fun displayAccount() {

        val disposableAccount = accountEndPoint?.fetchAccount(API.ACCOUNT_PATH.value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe({
                    if (isViewAttached()) {
                        accountView?.loadingStarted()
                    }
                })
                .subscribeBy(
                 onNext = {
                    accountTransactions -> onAccountResult(accountTransactions)
                },
                onError = {
                    error -> onAccountFetchError(error)
                },
                onComplete = {})

        compositeDisposable.add(disposableAccount)

    }

    override fun setView(view: IAccountView) {
        this.accountView = view
    }

    override fun setView(view: ITransactionsView) {
        this.transactionsView = view
    }

    override fun destroy() {
        accountView = null
        compositeDisposable?.clear()
    }

    private fun onAccountResult(accountTransactions: AccountTransactions) {
        if(isViewAttached()) {
            if (accountTransactions != null) {

               var   groupedTransactionMap : Map<String?, List<Transaction>>?

                doAsync {
                    val groupedTransactions =   accountTransactions.transactions
                            ?.groupBy { it.effectiveDate }

                    groupedTransactions?.forEach { (date, transactions) ->

                        if(date != null) {
                            transactionsItem.add(HeaderItem(date))
                        }

                        transactions.map { it ->
                            transactionsItem.add(TransactionItem(it))
                        }



                        if(transactions != null) {



//                            for (i in 0 until transactions.count() - 1) {
//                                items.add(TransactionItem(transactions[i]))
//                            }

                        }

                    }

                uiThread {
                    //finalTransactions.transactions = transactionsItem
                    accountView?.showAccount(accountTransactions, transactionsItem)
                   // transactionsView?.showTransactions(transactionsItem)
                    }
                }

            }
        }
    }


    private fun onAccountFetchError(throwable: Throwable) {
        if (isViewAttached()) {
            accountView?.loadingFailed(throwable.message)
        } else {
            // do nothing
        }
    }

    private fun isViewAttached(): Boolean {
        return accountView != null
    }

}