package ru.acceptthevoid.moneycalculator.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import ru.acceptthevoid.moneycalculator.models.*

class TransactionViewModel : ViewModel() {
    private val _transactions = MutableLiveData<List<Transaction>>(emptyList())
    val transactions: LiveData<List<Transaction>> get() = _transactions

    fun addTransaction(transaction: Transaction) {
        _transactions.value = _transactions.value?.plus(transaction)
    }
}