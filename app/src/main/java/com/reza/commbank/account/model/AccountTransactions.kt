package com.reza.commbank.account.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by reza on 30/11/17.
 */
class AccountTransactions {

    var account: Account? = null
    var transactions: List<Transaction>? = null
    var pending: List<Pending>? = null
    var atms: List<Atm>? = null
}