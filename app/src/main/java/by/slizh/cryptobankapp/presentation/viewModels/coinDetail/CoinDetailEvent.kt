package by.slizh.cryptobankapp.presentation.viewModels.coinDetail

sealed class CoinDetailEvent {
    data class LoadTransactions(val coinName: String) : CoinDetailEvent()
}