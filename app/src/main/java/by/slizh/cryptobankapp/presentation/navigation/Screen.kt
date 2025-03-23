package by.slizh.cryptobankapp.presentation.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen(route = "home_screen")
    object AddTransactionScreen : Screen(route = "add_transaction_screen")
    object SettingsScreen : Screen(route = "settings_screen")
    object CoinDetailScreen : Screen(route = "coin_detail_screen/{coinName}") {
        fun createRoute(coinName: String) =
            "coin_detail_screen/$coinName"
    }

    object AddTransactionNextStepScreen :
        Screen(route = "add_transaction_next_step_screen/{coinName}") {
        fun createRoute(coinName: String) =
            "add_transaction_next_step_screen/$coinName"
    }
}