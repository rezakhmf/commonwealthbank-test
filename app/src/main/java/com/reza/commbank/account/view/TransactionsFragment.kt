package com.reza.commbank.account.view

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.reza.commbank.CommBankApp
import com.reza.commbank.R
import com.reza.commbank.account.model.GroupedTransactions
import com.reza.commbank.account.model.ListItem
import com.reza.commbank.account.module.AccountModule
import com.reza.commbank.account.presenter.IAccountPresenter
import java.util.*
import javax.inject.Inject

/**
 * Created by reza on 2/12/17.
 */
class TransactionsFragment: Fragment(), ITransactionsView {

    @Inject
    lateinit var adapter: TransactionsAdapter

    @Inject
    lateinit var groupedTransactions: GroupedTransactions

    @Inject
    lateinit var accountPresenter: IAccountPresenter

    val component by lazy {
        CommBankApp.get(activity).commBankComponent.plus(AccountModule(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        component.inject(this)
        accountPresenter?.setView(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_transactions, container, false)

        initLayoutReferences(rootView)

        return rootView
    }

    private fun initLayoutReferences(rootView: View?) {
        val rvTransaction = rootView?.findViewById<View>(R.id.rv_transactions_listing) as RecyclerView

        rvTransaction.layoutManager = LinearLayoutManager(activity)
        rvTransaction.setHasFixedSize(true)


        this.adapter?.updateTransactions(groupedTransactions.transactions, groupedTransactions.pendigs)
        rvTransaction.adapter = this.adapter
    }

    override fun loadingStarted() {

    }

    override fun loadingFailed(errorMessage: String?) {

    }

    override fun TransactionsClicked(transactions: ArrayList<ListItem>) {
    }
}