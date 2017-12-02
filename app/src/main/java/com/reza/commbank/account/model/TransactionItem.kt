package com.reza.commbank.account.model

/**
 * Created by reza on 2/12/17.
 */
class TransactionItem(val transaction: Transaction) : ListItem() {
    override val type: Int
        get() = TYPE_TRANSACTION
}