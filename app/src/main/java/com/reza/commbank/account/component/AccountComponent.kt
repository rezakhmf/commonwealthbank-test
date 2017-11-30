package com.reza.commbank.account.component

import com.reza.commbank.account.module.AccountModule
import com.reza.commbank.account.module.FragmentScope
import com.reza.commbank.account.view.AccountFragment
import dagger.Subcomponent

/**
 * Created by reza on 30/11/17.
 */
@FragmentScope
@Subcomponent(modules = arrayOf(AccountModule::class))
interface AccountComponent {
    fun inject(accountFragment: AccountFragment)
}