package com.reza.commbank.account.model

/**
 * Created by reza on 2/12/17.
 */
class HeaderItem(val date: String) : ListItem() {

    override val type: Int
        get() = ListItem.TYPE_HEADER
}