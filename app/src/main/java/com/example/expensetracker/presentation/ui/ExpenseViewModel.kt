package com.example.expensetracker.presentation.ui
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.domain.model.Expense
import com.example.expensetracker.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
data class ExpenseUiState(
    val expenses: List<Expense> = emptyList(),
    val totalForMonth: Double = 0.0
)
class ExpenseViewModel(private val repository: ExpenseRepository) : ViewModel() {
    val uiState: StateFlow<ExpenseUiState> = repository.observeExpenses()
        .map { list ->
            val now = LocalDate.now()
            val monthTotal = list.filter { it.date.year == now.year && it.date.month == now.month }.sumOf { it.amount }
            ExpenseUiState(expenses = list, totalForMonth = monthTotal)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ExpenseUiState())
    fun addExpense(amount: Double, category: com.example.expensetracker.domain.model.ExpenseCategory, note: String) {
        viewModelScope.launch {
            repository.addExpense(Expense(amount = amount, category = category, date = LocalDate.now(), note = note))
        }
    }
    fun deleteExpense(id: Long) {
        viewModelScope.launch { repository.deleteExpense(id) }
    }
}
class ExpenseViewModelFactory(private val repository: ExpenseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExpenseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExpenseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
