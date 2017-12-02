package com.reza.commbank.account.module

import com.reza.commbank.account.presenter.AccountPresenter
import com.reza.commbank.account.presenter.IAccountEndpoint
import com.reza.commbank.account.presenter.IAccountPresenter
import com.reza.commbank.account.view.AccountFragment
import com.reza.commbank.account.view.TransactionsAdapter
import com.reza.commbank.account.view.TransactionsFragment
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by reza on 30/11/17.
 */
@Module
class AccountModule {

    private lateinit var accountFragment: AccountFragment
    private lateinit var transactionsFragment: TransactionsFragment

    constructor(accountFragment: AccountFragment) {
        this.accountFragment = accountFragment
    }

    constructor(transactionsFragment: TransactionsFragment){
        this.transactionsFragment = transactionsFragment
    }

    @Provides
    @FragmentScope
    fun provideAccountFragment(): AccountFragment {
        return accountFragment
    }

    @Provides
    @FragmentScope
    fun provideTransactionFragment(): TransactionsFragment {
        return transactionsFragment
    }

    @Provides
    @FragmentScope
    fun provideAccountPresenter(accountEnpoint: IAccountEndpoint): IAccountPresenter {
        return AccountPresenter(accountEnpoint)
    }

    @Provides
    @FragmentScope
    fun provideAccountEndpoint(retrofit: Retrofit): IAccountEndpoint {
        return retrofit.create(IAccountEndpoint::class.java)
    }

    @Provides
    @FragmentScope
    fun provideTransactionsAdapter(): TransactionsAdapter {
        return TransactionsAdapter()
    }
}