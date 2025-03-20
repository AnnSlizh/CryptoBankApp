package by.slizh.cryptobankapp.di

import android.app.Application
import androidx.room.Room
import by.slizh.cryptobankapp.data.TransactionsDatabase
import by.slizh.cryptobankapp.data.repository.TransactionRepositoryImpl
import by.slizh.cryptobankapp.domain.repository.TransactionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTransactionsDatabase(application: Application): TransactionsDatabase {
        return Room.databaseBuilder(
            application,
            TransactionsDatabase::class.java,
            TransactionsDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(database: TransactionsDatabase): TransactionRepository {
        return TransactionRepositoryImpl(database.transactionDao)
    }
}