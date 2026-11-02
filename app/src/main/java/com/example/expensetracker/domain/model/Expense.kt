package com.example.expensetracker.domain.model
import java.time.LocalDate
data class Expense(
    val id: Long = 0,
    val amount: Double,
    val category: ExpenseCategory,
    val date: LocalDate,
    val note: String = ""
)
enum class ExpenseCategory(val displayName: String) {
    FOOD("���"), TRANSPORT("���������"), HOUSING("�����"), ENTERTAINMENT("�����������"), OTHER("������")
}
