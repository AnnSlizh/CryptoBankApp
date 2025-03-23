package by.slizh.cryptobankapp.presentation.viewModels.coinDetail

import by.slizh.cryptobankapp.domain.model.Transaction

data class CoinDetailState(
    val coinName: String = "",
    val averagePurchasePrice: String = "0.0",
    val currentPrice: String = "0.0",
    val constPrice: String = "0.0",
    val profitTotal: String = "0.0",
    val profitPercentage: String = "0.0",
    val transactions: List<Transaction> = emptyList()
)