package ru.acceptthevoid.moneycalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import ru.acceptthevoid.moneycalculator.models.*
import ru.acceptthevoid.moneycalculator.views.*

class MainActivity : AppCompatActivity() {
    private val viewModel: TransactionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val incomeList: TextView = findViewById(R.id.incomeList)
        findViewById<Button>(R.id.addIncomeButton).setOnClickListener {
            viewModel.addTransaction(Transaction(1, 100.0, "income"))
        }

        viewModel.transactions.observe(this, Observer { transactions ->
            incomeList.text = transactions.filter { it.type == "income" }
                .joinToString("\n") { "${it.amount} ₽" }
        })
    }
}