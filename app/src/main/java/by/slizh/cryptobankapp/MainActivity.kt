package by.slizh.cryptobankapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import by.slizh.cryptobankapp.presentation.components.bars.BottomNavigationBar
import by.slizh.cryptobankapp.presentation.navigation.Screen
import by.slizh.cryptobankapp.presentation.screens.AddTransactionNextStepScreen
import by.slizh.cryptobankapp.presentation.screens.AddTransactionScreen
import by.slizh.cryptobankapp.presentation.screens.CoinDetailScreen
import by.slizh.cryptobankapp.presentation.screens.HomeScreen
import by.slizh.cryptobankapp.presentation.screens.SettingsScreen
import by.slizh.cryptobankapp.ui.theme.CryptoBankAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoBankAppTheme {
                val navController = rememberNavController()
                val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.White,
                    bottomBar = {
                        when (currentRoute) {
                            Screen.HomeScreen.route -> BottomNavigationBar(navController = navController)
                            Screen.CoinDetailScreen.route -> BottomNavigationBar(navController = navController)
                            Screen.SettingsScreen.route -> BottomNavigationBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.HomeScreen.route) { HomeScreen(navController) }
                        composable(Screen.CoinDetailScreen.route) { CoinDetailScreen(navController) }
                        composable(Screen.AddTransactionScreen.route) { AddTransactionScreen(navController) }
                        composable(Screen.AddTransactionNextStepScreen.route) { AddTransactionNextStepScreen(navController) }
                        composable(Screen.SettingsScreen.route) { SettingsScreen(navController) }
                    }
                }
            }
        }
    }
}

