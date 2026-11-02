package com.example.expensetracker.data.repository
import com.example.expensetracker.data.local.ExpenseDao
import com.example.expensetracker.data.local.toDomain
import com.example.expensetracker.data.local.toEntity
import com.example.expensetracker.domain.model.Expense
import com.example.expensetracker.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
class ExpenseRepositoryImpl(private val dao: ExpenseDao) : ExpenseRepository {
    override fun observeExpenses(): Flow<List<Expense>> {
        return dao.observeAll().map { list -> list.map { it.toDomain() } }
    }
    override suspend fun addExpense(expense: Expense): Long {
        return dao.insert(expense.toEntity())
    }
    override suspend fun deleteExpense(id: Long) {
        dao.deleteById(id)
    }
}
