package by.slizh.cryptobankapp

import androidx.annotation.DrawableRes

enum class Coin(
    val coinName: String,
    val coinPrice: String,
    @DrawableRes val photoCoinResId: Int
) {
    BITCOIN("Bitcoin", "0,10184712 BTC", R.drawable.bitcoin),
    ADA("ADA", "25,107 ADA", R.drawable.ada),
    TONCOIN("Toncoin", "0,10184712 TON", R.drawable.toncoin);
}