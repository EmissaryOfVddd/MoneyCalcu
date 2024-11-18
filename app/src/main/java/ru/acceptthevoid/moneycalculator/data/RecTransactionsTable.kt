package ru.acceptthevoid.moneycalculator.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.acceptthevoid.moneycalculator.models.Tag
import java.util.Date

@Entity(tableName = "recurring_transactions")
data class RecTransactionsTable(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val amount: Double,
    val type: String,
    val tag: Tag,
    val day: Int,
    val dateOfCreation: Date,
)