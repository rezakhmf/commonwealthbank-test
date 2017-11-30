package com.reza.commbank.account.module

import android.app.Fragment
import com.reza.commbank.account.presenter.AccountPresenter
import com.reza.commbank.account.presenter.IAccountEndpoint
import com.reza.commbank.account.presenter.IAccountPresenter
import com.reza.commbank.account.view.AccountFragment
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by reza on 30/11/17.
 */
@Module
class AccountModule(val fragment: AccountFragment) {

    @Provides
    @FragmentScope
    fun provideAccountFragment(): AccountFragment {
        return fragment
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

    //TODO provide adaptoer
//    @Provides
//    @FragmentScope
//    fun provideAccountAdaptor(): AccountAdapter {
//        return M
//    }
}