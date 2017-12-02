package com.reza.commbank.account.presenter

import android.util.Log
import com.reza.commbank.account.model.Account
import com.reza.commbank.account.model.AccountTransactions
import com.reza.commbank.account.model.Transaction
import com.reza.commbank.account.view.IAccountView
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
import kotlin.reflect.KMutableProperty1

/**
 * Created by reza on 30/11/17.
 */
class AccountPresenter(accountEndpoint: IAccountEndpoint) : IAccountPresenter {


    private var accountEndPoint: IAccountEndpoint = accountEndpoint
    private var view: IAccountView? = null
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()


    override fun displayAccount() {

        val disposableAccount = accountEndPoint?.fetchAccount(API.ACCOUNT_PATH.value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe({
                    if (isViewAttached()) {
                        view?.loadingStarted()
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
        this.view = view
    }

    override fun destroy() {
        view = null
        compositeDisposable?.clear()
    }

    private fun onAccountResult(accountTransactions: AccountTransactions) {
        if(isViewAttached()) {
            if (accountTransactions != null) {

               var   groupedTransactionMap : Map<String?, List<Transaction>>?

                doAsync {
                    val groupedTransactions =   accountTransactions.transactions
                            ?.groupBy { it.effectiveDate }
                    groupedTransactionMap = groupedTransactions
                    
                uiThread {
                        groupedTransactionMap
                        view?.showAccount(accountTransactions)
                    }
                }

            }
        }
    }

    private fun reza(test: MutableMap<Transaction, Transaction>) {
        println(test)
    }

    private fun onAccountFetchError(throwable: Throwable) {
        if (isViewAttached()) {
            view?.loadingFailed(throwable.message)
        } else {
            // do nothing
        }
    }

    private fun isViewAttached(): Boolean {
        return view != null
    }

}