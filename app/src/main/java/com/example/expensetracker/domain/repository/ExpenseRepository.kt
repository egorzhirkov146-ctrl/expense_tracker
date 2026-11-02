package com.example.expensetracker.domain.repository
import com.example.expensetracker.domain.model.Expense
import kotlinx.coroutines.flow.Flow
interface ExpenseRepository {
    fun observeExpenses(): Flow<List<Expense>>
    suspend fun addExpense(expense: Expense): Long
    suspend fun deleteExpense(id: Long)
}
