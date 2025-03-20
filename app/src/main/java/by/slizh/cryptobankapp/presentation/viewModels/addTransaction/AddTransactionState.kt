package by.slizh.cryptobankapp.presentation.viewModels.addTransaction

data class AddTransactionState(
    val coinName: String = "",
    val amount: String = "",
    val priceCoin: String = "",
    val constPrice: String = "",
    val amountError: Boolean = false,
    val priceCoinError: Boolean = false,
    val amountFormatError: Boolean = false,
    val priceFormatError: Boolean = false
)