package by.slizh.cryptobankapp.presentation.viewModels.addTransaction

sealed class AddTransactionEvent {
    data class SelectCoin(val coinName: String) : AddTransactionEvent()
    data class EnterAmount(val value: String) : AddTransactionEvent()
    data class EnterPrice(val value: String) : AddTransactionEvent()
    object AddTransaction : AddTransactionEvent()
    object ValidateFields : AddTransactionEvent()
}