package ru.acceptthevoid.moneycalculator

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.acceptthevoid.moneycalculator.adapters.RecTransactionsAdapter
import ru.acceptthevoid.moneycalculator.adapters.TransactionAdapter
import ru.acceptthevoid.moneycalculator.common.showAddTransactionDialog
import ru.acceptthevoid.moneycalculator.dialog.AddRecurringPaymentDialog
import ru.acceptthevoid.moneycalculator.models.Tag
import ru.acceptthevoid.moneycalculator.views.RecTransactionViewModel

class RecurringTransactionsScreen : AppCompatActivity() {
    private val viewModel: RecTransactionViewModel by viewModels()
    private lateinit var recTransactionsRecyclerView: RecyclerView
    private lateinit var transactionAdapter: RecTransactionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recurring_transactions_screen)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recTransactionsRecyclerView = findViewById(R.id.recTransactionsRecyclerView)
        recTransactionsRecyclerView.layoutManager = LinearLayoutManager(this)

        transactionAdapter = RecTransactionsAdapter(emptyList())
        recTransactionsRecyclerView.adapter = transactionAdapter

        findViewById<FloatingActionButton>(R.id.addRecButton).setOnClickListener {
            val dialog = AddRecurringPaymentDialog(viewModel)
            dialog.show(supportFragmentManager, "AddRecurringPaymentDialog")
        }

        viewModel.transactions.observe(this) { transactions ->
            val incomeTransactions = transactions.filter { it.tag == Tag.INCOME }
            transactionAdapter.updateTransactions(incomeTransactions)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            val intent = Intent(this, MainScreenActivity::class.java)
            startActivity(intent)
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}