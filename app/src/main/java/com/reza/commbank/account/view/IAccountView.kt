package com.reza.commbank.account.view

import com.reza.commbank.account.model.Account
import com.reza.commbank.account.model.AccountTransactions
import com.reza.commbank.account.model.ListItem
import java.util.ArrayList

/**
 * Created by reza on 30/11/17.
 */
interface IAccountView {
    fun showAccount(accountTransactions: AccountTransactions, transactionsItem: ArrayList<ListItem>)
    fun loadingStarted()
    fun loadingFailed(errorMessage: String?)
    fun accountClicked(accountTransactions: AccountTransactions)
}