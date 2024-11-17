package ru.acceptthevoid.moneycalculator.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.acceptthevoid.moneycalculator.models.RecurringTransaction

class RecTransactionsAdapter(private var transactions: List<RecurringTransaction>) :
    RecyclerView.Adapter<RecTransactionsAdapter.RecTransactionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecTransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_2, parent, false)
        return RecTransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecTransactionViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.bind(transaction)
    }

    override fun getItemCount(): Int = transactions.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateTransactions(newTransactions: List<RecurringTransaction>) {
        transactions = newTransactions
        notifyDataSetChanged()
    }

    class RecTransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val amountTextView: TextView = itemView.findViewById(android.R.id.text1)
        private val dateTextView: TextView = itemView.findViewById(android.R.id.text2)

        fun bind(transaction: RecurringTransaction) {
            amountTextView.text = "${transaction.amount} ₽ (${transaction.type})"
            dateTextView.text = "Дата транзакции: ${transaction.day}"
        }
    }
}