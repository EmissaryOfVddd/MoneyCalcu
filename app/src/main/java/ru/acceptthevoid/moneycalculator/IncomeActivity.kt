package ru.acceptthevoid.moneycalculator

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.acceptthevoid.moneycalculator.adapters.TransactionAdapter
import ru.acceptthevoid.moneycalculator.dialog.AddTransactionDialog
import ru.acceptthevoid.moneycalculator.models.Tag
import ru.acceptthevoid.moneycalculator.views.TransactionViewModel

class IncomeActivity : AppCompatActivity() {
    private val viewModel: TransactionViewModel by viewModels()
    private lateinit var incomeRecyclerView: RecyclerView
    private lateinit var incomeAdapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        incomeRecyclerView = findViewById(R.id.incomeRecyclerView)
        incomeRecyclerView.layoutManager = LinearLayoutManager(this)

        incomeAdapter = TransactionAdapter(emptyList())
        incomeRecyclerView.adapter = incomeAdapter

        findViewById<FloatingActionButton>(R.id.addIncomeButton).setOnClickListener {
            val dialog = AddTransactionDialog(viewModel, Tag.INCOME)
            dialog.show(supportFragmentManager, "AddTransactionDialog::INCOME")
        }

        viewModel.transactions.observe(this) { transactions ->
            val incomeTransactions = transactions.filter { it.tag == Tag.INCOME }
            incomeAdapter.updateTransactions(incomeTransactions)
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