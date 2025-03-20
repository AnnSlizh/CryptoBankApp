package by.slizh.cryptobankapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.slizh.cryptobankapp.data.dao.TransactionDao
import by.slizh.cryptobankapp.domain.model.Transaction

@Database(entities = [Transaction::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class TransactionsDatabase : RoomDatabase() {

    abstract val transactionDao: TransactionDao

    companion object {
        const val DATABASE_NAME = "transactions_db"
    }
}