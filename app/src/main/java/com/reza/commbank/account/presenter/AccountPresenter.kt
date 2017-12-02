package com.reza.commbank.account.presenter

import com.reza.commbank.account.model.*
import com.reza.commbank.account.view.IAccountView
import com.reza.commbank.account.view.ITransactionsView
import com.reza.commbank.commBank.util.API
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*


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
                        onNext = { accountTransactions ->
                            onAccountResult(accountTransactions)
                        },
                        onError = { error ->
                            onAccountFetchError(error)
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
        if (isViewAttached()) {
            if (accountTransactions != null) {

                doAsync {
                    val groupedTransactions = accountTransactions.transactions
                            ?.groupBy { it.effectiveDate }

                    groupedTransactions?.forEach { (date, transactions) ->

                        if (date != null) {
                            transactionsItem.add(HeaderItem(date))
                        }

                        transactions.map { it ->
                            transactionsItem.add(TransactionItem(it))
                        }
                    }
                    uiThread {
                        accountView?.showAccount(accountTransactions, transactionsItem)
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