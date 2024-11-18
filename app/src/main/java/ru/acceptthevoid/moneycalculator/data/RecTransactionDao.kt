package ru.acceptthevoid.moneycalculator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.acceptthevoid.moneycalculator.models.RecurringTransaction

@Dao
interface RecTransactionDao {
    @Insert
    suspend fun insert(payment: RecurringTransaction): Long

    @Query("SELECT * FROM recurring_transactions")
    suspend fun getAllTransactions(): List<RecurringTransaction>
}