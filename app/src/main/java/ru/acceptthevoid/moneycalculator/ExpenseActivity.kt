package ru.acceptthevoid.moneycalculator

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.acceptthevoid.moneycalculator.adapters.TransactionAdapter
import ru.acceptthevoid.moneycalculator.common.showAddTransactionDialog
import ru.acceptthevoid.moneycalculator.models.*
import ru.acceptthevoid.moneycalculator.views.*

class ExpenseActivity : AppCompatActivity() {
    private val viewModel: TransactionViewModel by viewModels()

    private lateinit var transactionRecyclerView: RecyclerView
    private lateinit var expenseAdapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        transactionRecyclerView = findViewById(R.id.expenseRecyclerView)
        transactionRecyclerView.layoutManager = LinearLayoutManager(this)

        expenseAdapter = TransactionAdapter(emptyList())
        transactionRecyclerView.adapter = expenseAdapter

        findViewById<FloatingActionButton>(R.id.addExpenseButton).setOnClickListener {
            showAddTransactionDialog(this, viewModel, Tag.EXPENSE)
        }

        viewModel.transactions.observe(this) { transactions ->
            val expenseTransactions = transactions.filter { it.tag == Tag.EXPENSE }
            expenseAdapter.updateTransactions(expenseTransactions)
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