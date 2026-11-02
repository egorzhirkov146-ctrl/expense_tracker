package com.example.expensetracker
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.presentation.ui.*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val app = application as ExpenseApp
                    val factory = ExpenseViewModelFactory(app.repository)
                    val viewModel: ExpenseViewModel = viewModel(factory = factory)
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "list") {
                        composable("list") { ExpenseListScreen(viewModel) { navController.navigate("add") } }
                        composable("add") { AddExpenseScreen({ a, c, n -> viewModel.addExpense(a, c, n); navController.popBackStack() }) { navController.popBackStack() } }
                    }
                }
            }
        }
    }
}
