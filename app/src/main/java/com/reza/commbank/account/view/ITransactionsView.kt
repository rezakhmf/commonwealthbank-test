package com.reza.commbank.account.view

import com.reza.commbank.account.model.ListItem
import com.reza.commbank.account.model.Transaction
import java.util.ArrayList

/**
 * Created by reza on 2/12/17.
 */
interface ITransactionsView {
    //fun showTransactions(transactions: ArrayList<ListItem>)
    fun loadingStarted()
    fun loadingFailed(errorMessage: String?)
    fun TransactionsClicked(transactions: ArrayList<ListItem>)
}