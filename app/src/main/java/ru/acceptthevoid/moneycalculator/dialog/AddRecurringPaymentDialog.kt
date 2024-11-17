package ru.acceptthevoid.moneycalculator.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import ru.acceptthevoid.moneycalculator.R
import ru.acceptthevoid.moneycalculator.models.RecurringTransaction
import ru.acceptthevoid.moneycalculator.models.Tag
import ru.acceptthevoid.moneycalculator.views.RecTransactionViewModel

class AddRecurringPaymentDialog(private val viewModel: RecTransactionViewModel) : DialogFragment() {
    private lateinit var amountEditText: EditText
    private lateinit var dayPicker: NumberPicker
    private lateinit var typeEditText: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_add_recurrent, null)

        amountEditText = view.findViewById(R.id.amountEditText)
        dayPicker = view.findViewById(R.id.dayPicker)
        typeEditText = view.findViewById(R.id.typeEditText)

        dayPicker.minValue = 1
        dayPicker.maxValue = 31

        builder.setView(view)
            .setTitle("Добавить ежемесячный платеж")
            .setPositiveButton("Сохранить") { _, _ -> savePayment() }
            .setNegativeButton("Отмена") { dialog, _ -> dialog.dismiss() }

        return builder.create()
    }

    private fun savePayment() {
        val amount = amountEditText.text.toString().toDoubleOrNull()
        val day = dayPicker.value
        val type = typeEditText.text.toString()

        if (amount == null) {
            amountEditText.error = "Введите корректную сумму"
            return
        }

        viewModel.addTransaction(RecurringTransaction(
            0, amount, day, Tag.INCOME, type
        ))

        dismiss()
    }
}