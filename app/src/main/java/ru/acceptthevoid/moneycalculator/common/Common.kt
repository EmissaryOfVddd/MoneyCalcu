package ru.acceptthevoid.moneycalculator.common

import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import ru.acceptthevoid.moneycalculator.R
import ru.acceptthevoid.moneycalculator.models.Tag
import ru.acceptthevoid.moneycalculator.models.Transaction
import ru.acceptthevoid.moneycalculator.models.Type
import ru.acceptthevoid.moneycalculator.views.TransactionViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun showAddTransactionDialog(ctx: Context, viewModel: TransactionViewModel, tag: Tag) {
    val dialogView = LayoutInflater.from(ctx).inflate(R.layout.dialog_add_transaction, null)

    val amountEditText: EditText = dialogView.findViewById(R.id.amountEditText)
    val titleEditText: EditText = dialogView.findViewById(R.id.titleEditText)
    val categoryEditText: EditText = dialogView.findViewById(R.id.categoryEditText)
    val recurringTransactionCheckbox: CheckBox = dialogView.findViewById(R.id.recurringTransactionCheckbox)
    val dateTextView: TextView = dialogView.findViewById(R.id.dateTextView)
    val selectDateButton: Button = dialogView.findViewById(R.id.selectDateButton)

    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    dateTextView.text = dateFormat.format(calendar.time)

    selectDateButton.setOnClickListener {
        DatePickerDialog(ctx, { _, year, month, day ->
            calendar.set(year, month, day)
            dateTextView.text = dateFormat.format(calendar.time)
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
    }

    AlertDialog.Builder(ctx)
        .setTitle("Добавить транзакцию")
        .setView(dialogView)
        .setPositiveButton("Добавить") { _, _ ->
            val amount = amountEditText.text.toString().toDoubleOrNull() ?: 0.0
            val title = titleEditText.text.toString()
            val category = categoryEditText.text.toString()
            val timeTag: Type = if (recurringTransactionCheckbox.isChecked) {
                Type.MONTHLY
            } else {
                Type.ONE_TIME
            }

            val date = calendar.time

            if (title.isNotEmpty() && category.isNotEmpty() && amount > 0) {
                viewModel.addTransaction(Transaction(0, amount, tag, timeTag, category, date))
            }
        }
        .setNegativeButton("Отменить", null)
        .show()
}