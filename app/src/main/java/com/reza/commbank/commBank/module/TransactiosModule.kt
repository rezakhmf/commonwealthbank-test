package com.reza.commbank.commBank.module

import com.reza.commbank.account.model.GroupedTransactions
import com.reza.commbank.account.model.ListItem
import dagger.Module
import dagger.Provides
import java.util.ArrayList
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by reza on 2/12/17.
 */
@Module
class TransactiosModule(var transactionsItem: GroupedTransactions) {



    @Provides
    @Singleton
    fun provideGroupedTransactions(): GroupedTransactions {
        return transactionsItem
    }
}