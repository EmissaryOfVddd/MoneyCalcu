package ru.acceptthevoid.moneycalculator.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.acceptthevoid.moneycalculator.models.Transaction
import java.text.SimpleDateFormat
import java.util.*

class TransactionAdapter(private var transactions: List<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_2, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.bind(transaction)
    }

    override fun getItemCount(): Int = transactions.size

    fun updateTransactions(newTransactions: List<Transaction>) {
        transactions = newTransactions
        notifyDataSetChanged()
    }

    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val amountTextView: TextView = itemView.findViewById(android.R.id.text1)
        private val dateTextView: TextView = itemView.findViewById(android.R.id.text2)

        fun bind(transaction: Transaction) {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            amountTextView.text = "${transaction.amount} ₽ (${transaction.type})"
            dateTextView.text = dateFormat.format(transaction.date)
        }
    }
}