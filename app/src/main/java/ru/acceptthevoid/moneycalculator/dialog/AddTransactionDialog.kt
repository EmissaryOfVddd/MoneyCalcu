package ru.acceptthevoid.moneycalculator.dialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import ru.acceptthevoid.moneycalculator.R
import ru.acceptthevoid.moneycalculator.models.Tag
import ru.acceptthevoid.moneycalculator.models.Transaction
import ru.acceptthevoid.moneycalculator.models.Type
import ru.acceptthevoid.moneycalculator.views.TransactionViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddTransactionDialog(
    private val viewModel: TransactionViewModel,
    private val tag: Tag
): DialogFragment() {
    private lateinit var amountEditText: EditText
    private lateinit var categoryEditText: EditText
    private lateinit var recurringTransactionCheckbox: CheckBox
    private lateinit var dateTextView: TextView
    private lateinit var selectDateButton: Button

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_add_transaction, null)

        amountEditText = view.findViewById(R.id.amountEditText)
        categoryEditText = view.findViewById(R.id.categoryEditText)
        recurringTransactionCheckbox = view.findViewById(R.id.recurringTransactionCheckbox)
        dateTextView = view.findViewById(R.id.dateTextView)
        selectDateButton = view.findViewById(R.id.selectDateButton)

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        dateTextView.text = dateFormat.format(calendar.time)

        selectDateButton.setOnClickListener {
            DatePickerDialog(requireContext(), { _, year, month, day ->
                calendar.set(year, month, day)
                dateTextView.text = dateFormat.format(calendar.time)
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        val title = if(tag == Tag.INCOME) {
            "Добавить доход"
        } else {
            "Добавить расход"
        }

        builder.setView(view)
            .setTitle(title)
            .setPositiveButton("Сохранить") { _, _, -> savePayment(calendar) }
            .setNegativeButton("Отмена") { dialog, _ -> dialog.dismiss() }

        return builder.create()
    }

    private fun savePayment(calendar: Calendar) {
        //TODO: валидацию бы....

        val amount = amountEditText.text.toString().toDoubleOrNull()
        if(amount == null) {
            amountEditText.error = "Введите корректную сумму"
            return
        }

        val category = categoryEditText.text.toString()
        val timeTag: Type = if (recurringTransactionCheckbox.isChecked) {
            Type.MONTHLY
        } else {
            Type.ONE_TIME
        }

        val date = calendar.time
        viewModel.addTransaction(Transaction(0, amount, tag, timeTag, category, date))
    }
}