package by.slizh.cryptobankapp.presentation.viewModels.addTransaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.slizh.cryptobankapp.domain.model.Transaction
import by.slizh.cryptobankapp.domain.repository.TransactionRepository
import by.slizh.cryptobankapp.domain.CoinEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AddTransactionState())
    val state: StateFlow<AddTransactionState> = _state.asStateFlow()

    fun onEvent(event: AddTransactionEvent) {
        when (event) {
            is AddTransactionEvent.SelectCoin -> {
                val selectedCoin = CoinEnum.entries.find { it.coinName == event.coinName }
                _state.update {
                    it.copy(
                        coinName = event.coinName,
                        constPrice = selectedCoin?.constPrice ?: "0.0"
                    )
                }
                validateForm()
            }

            is AddTransactionEvent.EnterAmount -> {
                val amount = event.value.replace(',', '.')
                _state.update { it.copy(amount = amount) }
                validateForm()
            }

            is AddTransactionEvent.EnterPrice -> {
                val price = event.value.replace(',', '.')
                _state.update { it.copy(priceCoin = price) }
                validateForm()
            }

            is AddTransactionEvent.ValidateFields -> {
                val isAmountValid = _state.value.amount.toDoubleOrNull() != null
                val isPriceValid = _state.value.priceCoin.toDoubleOrNull() != null
                _state.update {
                    it.copy(
                        amountError = it.amount.isBlank(),
                        priceCoinError = it.priceCoin.isBlank(),
                        amountFormatError = !isAmountValid,
                        priceFormatError = !isPriceValid
                    )
                }
                validateForm()
            }

            is AddTransactionEvent.AddTransaction -> {
                if (!_state.value.isButtonEnabled) return

                val transaction = Transaction(
                    id = 0,
                    coinName = _state.value.coinName,
                    amount = _state.value.amount.toDouble(),
                    priceCoin = _state.value.priceCoin.toDouble(),
                    date = Date(),
                    profit = (_state.value.constPrice.toDouble() - _state.value.priceCoin.toDouble()) * _state.value.amount.toDouble()
                )

                viewModelScope.launch {
                    transactionRepository.addTransaction(transaction)
                    _state.update { AddTransactionState() }
                }
            }
        }
    }

    private fun validateForm() {
        val isAmountValid = _state.value.amount.toDoubleOrNull() != null
        val isPriceValid = _state.value.priceCoin.toDoubleOrNull() != null
        val isButtonEnabled = _state.value.coinName.isNotBlank() &&
                _state.value.amount.isNotBlank() &&
                _state.value.priceCoin.isNotBlank() &&
                isAmountValid &&
                isPriceValid

        _state.update { it.copy(isButtonEnabled = isButtonEnabled) }
    }
}