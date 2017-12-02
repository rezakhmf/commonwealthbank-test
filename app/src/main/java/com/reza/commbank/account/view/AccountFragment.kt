package com.reza.commbank.account.view

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.reza.commbank.CommBankApp
import com.reza.commbank.R
import com.reza.commbank.account.model.AccountTransactions
import com.reza.commbank.account.model.GroupedTransactions
import com.reza.commbank.account.model.ListItem
import com.reza.commbank.account.model.Pending
import com.reza.commbank.account.module.AccountModule
import com.reza.commbank.account.presenter.IAccountPresenter
import kotlinx.android.synthetic.main.fragment_account.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*
import javax.inject.Inject

/**
 * Created by reza on 30/11/17.
 */
class AccountFragment : Fragment(), IAccountView {

    val TRANSACTION_FRAGMENT = "TransactionFragment"

    @Inject
    lateinit var accountPresenter: IAccountPresenter
    @Inject
    lateinit var groupedTransactions: GroupedTransactions

    private var callback: CallBack? = null

    private var accountTransactions: AccountTransactions? = null

    val component by lazy {
        CommBankApp.get(activity).commBankComponent.plus(AccountModule(this))
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        callback = context as CallBack
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        component.inject(this)
        accountPresenter?.setView(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accountPresenter?.displayAccount()

    }

    override fun showAccount(accountTransactions: AccountTransactions,
                             transactionsItem: ArrayList<ListItem>) {

        this.accountTransactions = accountTransactions
        groupedTransactions.transactions = transactionsItem
        groupedTransactions.pendigs = accountTransactions.pending

        accountNumber?.text = accountTransactions?.account?.accountNumber
        fundsResult?.text = accountTransactions?.account?.balance.toString()
        balanceResult?.text = accountTransactions?.account?.available.toString()

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_transactions_container, TransactionsFragment(), TRANSACTION_FRAGMENT)
                .commit()
        progressBarContainer.visibility = View.INVISIBLE


    }

    override fun loadingStarted() {

    }

    override fun loadingFailed(errorMessage: String?) {

    }

    override fun accountClicked(accountTransactions: AccountTransactions) {
    }

    interface CallBack {
        fun onAccountLoaded(accountTransactions: AccountTransactions)
        fun onAccountClicked(accountTransactions: AccountTransactions)
    }
}