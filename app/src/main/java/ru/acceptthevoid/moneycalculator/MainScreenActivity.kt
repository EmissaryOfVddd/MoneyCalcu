package ru.acceptthevoid.moneycalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import androidx.appcompat.widget.Toolbar

class MainScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        val goToExpensesButton: Button = findViewById(R.id.goToExpensesButton)
        val goToIncomeButton: Button = findViewById(R.id.goToIncomeButton)
        val goToRecurringButton: Button = findViewById(R.id.goToRecurringButton)

        goToExpensesButton.setOnClickListener {
            startActivity(Intent(this, ExpenseActivity::class.java))
        }

        goToIncomeButton.setOnClickListener {
            startActivity(Intent(this, IncomeActivity::class.java))
        }

        goToRecurringButton.setOnClickListener {
            startActivity(Intent(this, RecurringTransactionsScreen::class.java))
        }
    }
}