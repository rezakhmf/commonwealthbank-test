package com.reza.commbank.commBank.module

import android.content.Context
import com.reza.commbank.CommBankApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by reza on 30/11/17.
 */
@Module
class CommBankModule(val app: CommBankApp) {

    @Provides
    @Singleton
    fun provideApp() = app

    @Provides
    @Singleton
    fun provideContext(): Context {
        return app
    }
}