package by.slizh.cryptobankapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val coinName: String,
    val amount: Double,
    val priceCoin: Double ,
    val date: Date,
    val profit: Double
)