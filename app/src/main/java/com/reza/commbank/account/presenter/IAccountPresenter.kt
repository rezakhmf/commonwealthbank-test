package com.reza.commbank.account.presenter

import com.reza.commbank.account.view.IAccountView
import com.reza.commbank.account.view.ITransactionsView

/**
 * Created by reza on 30/11/17.
 */
interface IAccountPresenter {

    fun displayAccount()

    fun setView(view: IAccountView)
    fun setView(view: ITransactionsView)

    fun destroy()
}