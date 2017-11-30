package com.reza.commbank.account.view

import com.reza.commbank.account.model.Account
import com.reza.commbank.account.model.AccountTransactions

/**
 * Created by reza on 30/11/17.
 */
interface IAccountView {
    fun showAccount(accountTransactions: AccountTransactions)
    fun loadingStarted()
    fun loadingFailed(errorMessage: String?)
    fun accountClicked(accountTransactions: AccountTransactions)
}