package ru.acceptthevoid.moneycalculator.models

import java.time.LocalDate

data class RecurringTransaction(
    val id: Int,
    val amount: Double,
    val day: Int,
    val tag: Tag,
    val type: String,
    val dayOfCreation: LocalDate,
)