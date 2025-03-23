package by.slizh.cryptobankapp.presentation.viewModels.coinDetail

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.slizh.cryptobankapp.domain.model.Transaction
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
class CoinDetailViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CoinDetailState())
    val state: StateFlow<CoinDetailState> = _state.asStateFlow()

    fun onEvent(event: CoinDetailEvent) {
        when (event) {
            is CoinDetailEvent.LoadTransactions -> {
                loadTransactions(event.coinName)
            }
        }
    }

    private fun loadTransactions(coinName: String) {
        viewModelScope.launch {
            transactionRepository.getTransactionsByName(coinName).collect { transactions ->
                _state.value = _state.value.copy(transactions = transactions)
                calculatePrices(coinName, transactions)
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private fun calculatePrices(coinName: String, transactions: List<Transaction>) {
        if (transactions.isEmpty()) return

        val totalAmount = transactions.sumOf { it.amount }
        val totalPrice = transactions.sumOf { it.amount * it.priceCoin }
        val averagePurchasePrice = if (totalAmount > 0) totalPrice / totalAmount else 0.0

        val selectedCoin = CoinEnum.entries.find { it.coinName == coinName }
        val constPrice = selectedCoin?.constPrice?.toDouble() ?: 0.0
        val currentPrice = totalAmount * constPrice

        val profitTotal = currentPrice - totalPrice
        val profitPercentage = if (totalPrice > 0) (profitTotal / totalPrice) * 100 else 0.0

        _state.update {
            it.copy(
                averagePurchasePrice = DoubleFormat.formatDouble(averagePurchasePrice),
                currentPrice = DoubleFormat.formatDouble(currentPrice),
                constPrice = DoubleFormat.formatDouble(constPrice),
                profitTotal = "${if (profitTotal >= 0) "+" else ""}${DoubleFormat.formatDouble(profitTotal)}",
                profitPercentage = "${if (profitPercentage >= 0) "+" else ""}${DoubleFormat.formatDouble(profitPercentage)}"
            )
        }
    }
}