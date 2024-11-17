package ru.acceptthevoid.moneycalculator.models

import java.time.MonthDay

data class RecurringTransaction(
    val id: Int,
    val amount: Double,
    val tag: Tag,
    val type: String,
    val monthDay: MonthDay,
)