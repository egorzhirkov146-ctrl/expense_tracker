package com.example.expensetracker.presentation.ui
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.expensetracker.domain.model.ExpenseCategory
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseScreen(onSave: (Double, ExpenseCategory, String) -> Unit, onBack: () -> Unit) {
    var amount by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var category by remember { mutableStateOf(ExpenseCategory.FOOD) }
    Scaffold(topBar = { TopAppBar(title = { Text("����� ������") }, navigationIcon = { TextButton(onClick = onBack) { Text("�����") } }) }) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            OutlinedTextField(value = amount, onValueChange = { amount = it }, label = { Text("�����") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal))
            OutlinedTextField(value = note, onValueChange = { note = it }, label = { Text("�������") })
            Column(modifier = Modifier.selectableGroup()) {
                ExpenseCategory.entries.forEach { cat ->
                    Row(modifier = Modifier.fillMaxWidth().selectable(selected = category == cat, onClick = { category = cat }).padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = category == cat, onClick = null)
                        Spacer(Modifier.width(8.dp))
                        Text(cat.displayName)
                    }
                }
            }
            Button(onClick = { amount.toDoubleOrNull()?.let { onSave(it, category, note) } }, modifier = Modifier.fillMaxWidth()) { Text("���������") }
        }
    }
}
