package com.example.expensetracker.presentation.ui
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.expensetracker.domain.model.Expense
import java.time.format.DateTimeFormatter
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseListScreen(viewModel: ExpenseViewModel, onAddClick: () -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    Scaffold(
        topBar = { TopAppBar(title = { Text("�������") }) },
        floatingActionButton = { FloatingActionButton(onClick = onAddClick) { Icon(Icons.Default.Add, null) } }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("��������� �� �����")
                    Text("${"%.2f".format(uiState.totalForMonth)} ?", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn { items(uiState.expenses, key = { it.id }) { expense -> 
                ListItem(
                    headlineContent = { Text(expense.category.displayName) },
                    supportingContent = { Text(expense.date.format(dateFormatter)) },
                    trailingContent = { 
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("${expense.amount} ?", fontWeight = FontWeight.Bold)
                            IconButton(onClick = { viewModel.deleteExpense(expense.id) }) { Icon(Icons.Default.Delete, null) }
                        }
                    }
                )
            } }
        }
    }
}
