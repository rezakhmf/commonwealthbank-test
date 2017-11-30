package com.reza.commbank.account.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by reza on 30/11/17.
 */
class Transaction {

    var id: String? = null
    var effectiveDate: String? = null
    var description: String? = null
    var amount: Double? = null
    var atmId: String? = null
}