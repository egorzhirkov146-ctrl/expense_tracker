package com.example.expensetracker
import android.app.Application
import androidx.room.Room
import com.example.expensetracker.data.local.AppDatabase
import com.example.expensetracker.data.repository.ExpenseRepositoryImpl
import com.example.expensetracker.domain.repository.ExpenseRepository
class ExpenseApp : Application() {
    val database by lazy { Room.databaseBuilder(this, AppDatabase::class.java, "expense_db").build() }
    val repository: ExpenseRepository by lazy { ExpenseRepositoryImpl(database.expenseDao()) }
}
