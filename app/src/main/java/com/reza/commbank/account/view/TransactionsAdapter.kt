package com.reza.commbank.account.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.reza.commbank.R
import com.reza.commbank.account.model.HeaderItem
import com.reza.commbank.account.model.ListItem
import com.reza.commbank.account.model.Transaction
import com.reza.commbank.account.model.TransactionItem
import com.reza.commbank.util.DateProvider
import kotlinx.android.synthetic.main.fragment_transactions_date_item.view.*
import kotlinx.android.synthetic.main.fragment_transactions_info_item.view.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * Created by reza on 2/12/17.
 */
class TransactionsAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var mcallback: Callback? = null
    private var context: Context? = null
    private var items = Collections.emptyList<ListItem>()

    init {
       // mcallback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        context = parent?.context
        val layoutInflater = LayoutInflater.from(parent?.context)

        when (viewType) {
            ListItem.TYPE_HEADER -> {
                val itemView = layoutInflater.inflate(R.layout.fragment_transactions_date_item, parent, false)
                return HeaderViewHolder(itemView)
            }
            ListItem.TYPE_TRANSACTION -> {
                val itemView = layoutInflater.inflate(R.layout.fragment_transactions_info_item, parent, false)
                return TransactionsViewHolder(itemView)
            }
            else -> throw IllegalStateException("unsupported item type")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val viewType = getItemViewType(position)
        when (viewType) {
            ListItem.TYPE_HEADER -> {

                val header = items[position] as HeaderItem
                val holder = holder as HeaderViewHolder

                holder.mtransactionDate.text = DateProvider.dateWithMonthLabel(header.date)

//                val dateStr = "2/3/2017"
//                val sdf = SimpleDateFormat("dd/MM/yyyy")
//                val dddate = sdf.parse(header.date)
//                val diff = Date().getTime() - dddate.getTime()
//                TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS).toString()

                holder.mtransactionDays.text = DateProvider.dayDiffWithLable(header.date)
            }
            ListItem.TYPE_TRANSACTION -> {
                val transactionItem = items[position] as TransactionItem
                val holder = holder as TransactionsViewHolder
                holder.mtransactionAmount.text = transactionItem.transaction.amount.toString()
                holder.mtransactionInfo.text = transactionItem.transaction.description
            }
            else -> throw IllegalStateException("unsupported item type")
        }
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type
    }

    fun updateTransactions(groupedTransactionMap: ArrayList<ListItem>?) {
        this.items = groupedTransactionMap
    }

    private class HeaderViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {


            var mtransactionDate = itemView.transactionDate

            var mtransactionDays = itemView.transactionDays

    }

    class TransactionsViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var mtransactionAmount = itemView.transactionAmount
            var mtransactionInfo = itemView.transactionInfo
    }



    interface Callback {
        fun onMatchClicked(transaction: Transaction)
    }

}