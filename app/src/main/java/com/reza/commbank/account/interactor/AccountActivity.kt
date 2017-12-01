package com.reza.commbank.account.interactor

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.reza.commbank.R
import com.reza.commbank.R.color.colorDark
import com.reza.commbank.account.model.AccountTransactions
import com.reza.commbank.account.view.AccountFragment




/**
 * Created by reza on 30/11/17.
 */
class AccountActivity : AppCompatActivity(), AccountFragment.CallBack {

    val ACCOUNT_FRAGMENT = "AccountFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setIcon(R.drawable.icon_welcome_logo)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_account_container, AccountFragment(), ACCOUNT_FRAGMENT)
                .addToBackStack(ACCOUNT_FRAGMENT)
                .commit()
    }

    override fun onAccountClicked(accountTransactions: AccountTransactions) {

    }

    override fun onAccountLoaded(accountTransactions: AccountTransactions) {

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }

}