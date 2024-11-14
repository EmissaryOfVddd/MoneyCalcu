package ru.acceptthevoid.moneycalculator

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.acceptthevoid.moneycalculator.adapters.TransactionAdapter
import ru.acceptthevoid.moneycalculator.models.*
import ru.acceptthevoid.moneycalculator.views.*
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private val viewModel: TransactionViewModel by viewModels()

    private lateinit var transactionRecyclerView: RecyclerView
    private lateinit var transactionAdapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transactionRecyclerView = findViewById(R.id.transactionRecyclerView)
        transactionRecyclerView.layoutManager = LinearLayoutManager(this)

        transactionAdapter = TransactionAdapter(emptyList())
        transactionRecyclerView.adapter = transactionAdapter

        findViewById<FloatingActionButton>(R.id.addIncomeButton).setOnClickListener {
            showAddTransactionDialog()
        }

        viewModel.transactions.observe(this, Observer { transactions ->
            transactionAdapter.updateTransactions(transactions)
        })
    }

    private fun showAddTransactionDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_transaction, null)

        val amountEditText: EditText = dialogView.findViewById(R.id.amountEditText)
        val titleEditText: EditText = dialogView.findViewById(R.id.titleEditText)
        val categoryEditText: EditText = dialogView.findViewById(R.id.categoryEditText)
        val dateTextView: TextView = dialogView.findViewById(R.id.dateTextView)
        val selectDateButton: Button = dialogView.findViewById(R.id.selectDateButton)

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        dateTextView.text = dateFormat.format(calendar.time)

        selectDateButton.setOnClickListener {
            DatePickerDialog(this, { _, year, month, day ->
                calendar.set(year, month, day)
                dateTextView.text = dateFormat.format(calendar.time)
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        AlertDialog.Builder(this)
            .setTitle("Добавить транзакцию")
            .setView(dialogView)
            .setPositiveButton("Добавить") { _, _ ->
                val amount = amountEditText.text.toString().toDoubleOrNull() ?: 0.0
                val title = titleEditText.text.toString()
                val category = categoryEditText.text.toString()
                val date = calendar.time

                if (title.isNotEmpty() && category.isNotEmpty() && amount > 0) {
                    viewModel.addTransaction(Transaction(0, amount, category, date))
                }
            }
            .setNegativeButton("Отменить", null)
            .show()
    }
}