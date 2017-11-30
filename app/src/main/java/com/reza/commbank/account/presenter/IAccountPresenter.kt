package com.reza.commbank.account.presenter

import com.reza.commbank.account.view.IAccountView

/**
 * Created by reza on 30/11/17.
 */
interface IAccountPresenter {

    fun displayAccount()

    fun setView(view: IAccountView)

    fun destroy()
}