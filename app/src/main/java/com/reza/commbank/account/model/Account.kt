package com.reza.commbank.account.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by reza on 30/11/17.
 */
class Account  {

    var accountName: String? = null
    var accountNumber: String? = null
    var available: Double? = null
    var balance: Double? = null
}