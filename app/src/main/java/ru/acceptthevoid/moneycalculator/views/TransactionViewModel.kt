package ru.acceptthevoid.moneycalculator.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import ru.acceptthevoid.moneycalculator.models.*
import java.util.Date

private val TEST_LIST: List<Transaction> = List(100) { id: Int ->
    val tag: Tag
    val type: String

    if(id % 2 == 0) {
        tag = Tag.INCOME
        type = "Аренда"
    } else {
        tag = Tag.EXPENSE
        type = "Кредит"
    }

    Transaction(id, id.toDouble(), tag, Type.ONE_TIME, type, Date(10000000000000))
}

class TransactionViewModel : ViewModel() {
    private val _transactions = MutableLiveData<List<Transaction>>(TEST_LIST)
    val transactions: LiveData<List<Transaction>> get() = _transactions

    fun addTransaction(transaction: Transaction) {
        _transactions.value = _transactions.value?.plus(transaction)
    }
}