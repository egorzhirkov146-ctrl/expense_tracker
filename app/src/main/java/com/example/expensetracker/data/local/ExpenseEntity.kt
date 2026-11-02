package com.example.expensetracker.data.local
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expensetracker.domain.model.Expense
import com.example.expensetracker.domain.model.ExpenseCategory
import java.time.LocalDate
@Entity(tableName = "expenses")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val amount: Double,
    val category: String,
    val date: Long,
    val note: String
)
fun ExpenseEntity.toDomain() = Expense(
    id = id, amount = amount, category = ExpenseCategory.valueOf(category), date = LocalDate.ofEpochDay(date), note = note
)
fun Expense.toEntity() = ExpenseEntity(
    id = id, amount = amount, category = category.name, date = date.toEpochDay(), note = note
)
