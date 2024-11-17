package ru.acceptthevoid.moneycalculator.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.acceptthevoid.moneycalculator.models.RecurringTransaction

class RecTransactionViewModel: ViewModel() {
    private val _recTransactions = MutableLiveData<List<RecurringTransaction>>(emptyList())
    val transactions: LiveData<List<RecurringTransaction>> get() = _recTransactions

    fun addTransaction(transaction: RecurringTransaction) {
        _recTransactions.value = _recTransactions.value?.plus(transaction)

    }
}