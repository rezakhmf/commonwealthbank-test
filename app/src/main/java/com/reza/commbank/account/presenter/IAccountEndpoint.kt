package com.reza.commbank.account.presenter

import com.reza.commbank.account.model.Account
import com.reza.commbank.account.model.AccountTransactions
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by reza on 30/11/17.
 */
interface IAccountEndpoint {

    @GET
    fun fetchAccount(@Url url: String) : Observable<AccountTransactions>
}