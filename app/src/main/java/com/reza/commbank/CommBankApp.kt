package com.reza.commbank

import android.app.Application
import android.content.Context
import com.reza.commbank.commBank.component.CommBankComponent
import com.reza.commbank.commBank.component.DaggerCommBankComponent
import com.reza.commbank.commBank.module.CommBankModule
import com.reza.commbank.commBank.module.NetworkModule

/**
 * Created by reza on 30/11/17.
 */
class CommBankApp : Application() {

    companion object {
        fun get(context: Context) : CommBankApp {
            return context.applicationContext as CommBankApp
        }
    }

    val commBankComponent: CommBankComponent by lazy {
        DaggerCommBankComponent
                .builder()
                .commBankModule(CommBankModule(this))
                .networkModule(NetworkModule())
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        commBankComponent.inject(this)
    }
}