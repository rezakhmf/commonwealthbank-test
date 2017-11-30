package com.reza.commbank.account.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.reza.commbank.R
import com.reza.commbank.account.model.Account
import com.reza.commbank.account.model.AccountTransactions

/**
 * Created by reza on 30/11/17.
 */
class AccountActivity : AppCompatActivity(), AccountFragment.CallBack {

    val ACCOUNT_FRAGMENT = "AccountFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_account_container, AccountFragment(), ACCOUNT_FRAGMENT)
                .addToBackStack(ACCOUNT_FRAGMENT)
                .commit()
    }

    override fun onAccountClicked(accountTransactions: AccountTransactions) {

    }

    override fun onAccountLoaded(accountTransactions: AccountTransactions) {

    }

}