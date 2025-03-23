package by.slizh.cryptobankapp.presentation.viewModels.home

data class HomeState (
    val coinName: String,
    val constPrice: String,
    val abbreviation: String,
    val totalPrice: String,
    val totalAmount: Double,
    val totalProfit: String,
    val photoCoinResId: Int
)