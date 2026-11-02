package com.example.expensetracker.data.local
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface ExpenseDao {
    @Query("SELECT * FROM expenses ORDER BY date DESC, id DESC")
    fun observeAll(): Flow<List<ExpenseEntity>>
    @Insert
    suspend fun insert(entity: ExpenseEntity): Long
    @Query("DELETE FROM expenses WHERE id = :id")
    suspend fun deleteById(id: Long)
}
