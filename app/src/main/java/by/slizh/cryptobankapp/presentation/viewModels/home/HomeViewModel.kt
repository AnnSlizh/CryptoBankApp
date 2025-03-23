package by.slizh.cryptobankapp.presentation.viewModels.home

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.slizh.cryptobankapp.R
import by.slizh.cryptobankapp.domain.repository.TransactionRepository
import by.slizh.cryptobankapp.domain.CoinEnum
import by.slizh.cryptobankapp.util.DoubleFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    private val _state = MutableStateFlow<List<HomeState>>(emptyList())
    val state: StateFlow<List<HomeState>> = _state.asStateFlow()

    private val _profitTotal = MutableStateFlow(0.0)
    val profitTotal: StateFlow<Double> = _profitTotal.asStateFlow()

    private val _profitPercentage = MutableStateFlow(0.0)
    val profitPercentage: StateFlow<Double> = _profitPercentage.asStateFlow()

    init {
        loadCoins()
    }

    @SuppressLint("DefaultLocale")
    private fun loadCoins() {
        viewModelScope.launch {
            transactionRepository.getAllTransactions().collect { transactions ->
                val allTransactions = transactions
                    .groupBy { it.coinName }
                    .map { (coinName, transactions) ->
                        val totalAmount = transactions.sumOf { it.amount }
                        val totalPrice = transactions.sumOf { it.amount * it.priceCoin }
                        val averagePurchasePrice =
                            if (totalAmount > 0) totalPrice / totalAmount else 0.0

                        val selectedCoin = CoinEnum.entries.find { it.coinName == coinName }
                        val constPrice = selectedCoin?.constPrice?.toDouble() ?: 0.0
                        val currentPrice = totalAmount * constPrice
                        val totalProfit = (currentPrice - averagePurchasePrice) * totalAmount

                        HomeState(
                            coinName = coinName,
                            constPrice = DoubleFormat.formatDouble(constPrice),
                            abbreviation = selectedCoin?.abbreviation ?: "",
                            totalPrice = DoubleFormat.formatDouble(totalPrice),
                            totalAmount = totalAmount,
                            totalProfit = DoubleFormat.formatDouble(totalProfit),
                            photoCoinResId = selectedCoin?.photoCoinResId ?: R.drawable.bitcoin
                        )
                    }

                _state.update { allTransactions }

                val total = allTransactions.sumOf {
                    it.totalAmount * (it.constPrice.toDoubleOrNull() ?: 0.0)
                }
                val totalPurchase = allTransactions.sumOf { it.totalPrice.toDoubleOrNull() ?: 0.0 }
                val profitTotal = total - totalPurchase
                val profitPercentage =
                    if (totalPurchase > 0) (profitTotal / totalPurchase) * 100 else 0.0

                _profitTotal.update { profitTotal }
                _profitPercentage.update { profitPercentage }
            }
        }
    }
}
