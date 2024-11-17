package ru.acceptthevoid.moneycalculator.models

import java.util.Date

enum class Tag {
    INCOME, EXPENSE
}

enum class Type {
    MONTHLY, ONE_TIME
}

data class Transaction(
    val id: Int,
    val amount: Double,
    val tag: Tag,
    val timeType: Type,
    val type: String, // TODO: нормальные типы придумать было бы хорошо
    val date: Date,
)