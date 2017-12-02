package com.reza.commbank.commBank.component

import com.reza.commbank.CommBankApp
import com.reza.commbank.account.component.AccountComponent
import com.reza.commbank.account.module.AccountModule
import com.reza.commbank.commBank.module.CommBankModule
import com.reza.commbank.commBank.module.NetworkModule
import com.reza.commbank.commBank.module.TransactiosModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by reza on 30/11/17.
 */
@Singleton
@Component(modules = arrayOf(CommBankModule::class, NetworkModule::class, TransactiosModule::class))
interface CommBankComponent {
    fun inject(app: CommBankApp)
    fun plus(accountModule: AccountModule) : AccountComponent
}