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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import by.slizh.cryptobankapp.presentation.components.bars.BottomNavigationBar
import by.slizh.cryptobankapp.presentation.navigation.Screen
import by.slizh.cryptobankapp.presentation.screens.AddTransactionNextStepScreen
import by.slizh.cryptobankapp.presentation.screens.AddTransactionScreen
import by.slizh.cryptobankapp.presentation.screens.CoinDetailScreen
import by.slizh.cryptobankapp.presentation.screens.HomeScreen
import by.slizh.cryptobankapp.presentation.screens.SettingsScreen
import by.slizh.cryptobankapp.ui.theme.CryptoBankAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoBankAppTheme {
                val navController = rememberNavController()
                val currentRoute =
                    navController.currentBackStackEntryAsState().value?.destination?.route

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.White,
                    bottomBar = {
                        when (currentRoute) {
                            Screen.HomeScreen.route -> BottomNavigationBar(navController = navController)
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
                        composable(
                            route = Screen.CoinDetailScreen.route,
                            arguments = listOf(
                                navArgument("coinName") { type = NavType.StringType }
                            )
                        ) {backStackEntry ->
                            val coinName = backStackEntry.arguments?.getString("coinName") ?: ""
                            CoinDetailScreen(navController, coinName)
                        }
                        composable(Screen.AddTransactionScreen.route) {
                            AddTransactionScreen(
                                navController
                            )
                        }
                        composable(
                            route = Screen.AddTransactionNextStepScreen.route,
                            arguments = listOf(
                                navArgument("coinName") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val coinName = backStackEntry.arguments?.getString("coinName") ?: ""
                            AddTransactionNextStepScreen(navController, coinName)
                        }
                        composable(Screen.SettingsScreen.route) { SettingsScreen(navController) }
                    }
                }
            }
        }
    }
}

