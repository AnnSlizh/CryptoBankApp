package by.slizh.cryptobankapp.data.repository

import by.slizh.cryptobankapp.data.dao.TransactionDao
import by.slizh.cryptobankapp.domain.model.Transaction
import by.slizh.cryptobankapp.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow

class TransactionRepositoryImpl(private val transactionDao: TransactionDao) :
    TransactionRepository {
    override suspend fun addTransaction(transaction: Transaction) =
        transactionDao.insertTransaction(transaction)

    override fun getAllTransactions(): Flow<List<Transaction>> = transactionDao.getAllTransactions()

    override fun getTransactionsByName(coinName: String): Flow<List<Transaction>> =
        transactionDao.getTransactionsByName(coinName)
}