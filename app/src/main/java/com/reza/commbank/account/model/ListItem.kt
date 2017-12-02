package com.reza.commbank.account.model

/**
 * Created by reza on 2/12/17.
 */
abstract class ListItem {

    abstract val type: Int

    companion object {

        val TYPE_HEADER = 0
        val TYPE_TRANSACTION = 1
    }

}