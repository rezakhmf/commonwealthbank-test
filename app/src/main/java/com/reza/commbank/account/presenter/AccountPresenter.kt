package com.reza.commbank.account.presenter

import com.reza.commbank.account.model.Account
import com.reza.commbank.account.model.AccountTransactions
import com.reza.commbank.account.view.IAccountView
import com.reza.commbank.commBank.util.API
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * Created by reza on 30/11/17.
 */
class AccountPresenter(accountEndpoint: IAccountEndpoint) : IAccountPresenter {


    private var accountEndPoint: IAccountEndpoint = accountEndpoint
    private var view: IAccountView? = null
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()


    override fun displayAccount() {

        val disposableAccount: Disposable = accountEndPoint?.fetchAccount(API.ACCOUNT_PATH.value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe({
                    if (isViewAttached()) {
                        view?.loadingStarted()
                    }
                }).subscribeBy (
                    onNext = {
                        accountTransactions -> onAccountResult(accountTransactions)
                },
                    onError = {
                        error -> onAccountFetchError(error)
                },
                    onComplete = {}
        )

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
                view?.showAccount(accountTransactions)
            }
        }
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