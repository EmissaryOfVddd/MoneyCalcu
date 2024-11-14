package ru.acceptthevoid.moneycalculator.models

data class Transaction(
    val id: Int,
    val amount: Double,
    val type: String // TODO: нормальные типы придумать было бы хорошоы
)