package by.slizh.cryptobankapp.domain.repository

import by.slizh.cryptobankapp.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun addTransaction(transaction: Transaction)
    fun getTransactionsByName(coinName: String): Flow<List<Transaction>>
    fun getAllTransactions(): Flow<List<Transaction>>
}