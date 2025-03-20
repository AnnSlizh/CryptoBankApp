package by.slizh.cryptobankapp.domain

import androidx.annotation.DrawableRes
import by.slizh.cryptobankapp.R

enum class CoinEnum(
    val coinName: String,
    val abbreviation: String,
    val constPrice: String,
    @DrawableRes val photoCoinResId: Int
) {
    BITCOIN("Bitcoin", "BTC", "24715.63", R.drawable.bitcoin),
    ADA("ADA", "ADA", "25.0", R.drawable.ada),
    TONCOIN("Toncoin", "TON", "41.12", R.drawable.toncoin);
}