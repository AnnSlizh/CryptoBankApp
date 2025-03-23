package by.slizh.cryptobankapp.presentation.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.slizh.cryptobankapp.presentation.viewModels.home.HomeState
import by.slizh.cryptobankapp.ui.theme.Black
import by.slizh.cryptobankapp.ui.theme.Gray
import by.slizh.cryptobankapp.ui.theme.GrayClick
import by.slizh.cryptobankapp.ui.theme.Green
import by.slizh.cryptobankapp.ui.theme.LightGray
import by.slizh.cryptobankapp.ui.theme.Red
import by.slizh.cryptobankapp.util.DoubleFormat

@Composable
fun CoinHomeCard(coin: HomeState, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = LightGray, contentColor = GrayClick),
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Image(
                painter = painterResource(id = coin.photoCoinResId), contentDescription = "",
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp)
                    .weight(1f)
            ) {
                Text(
                    text = coin.coinName,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    lineHeight = 18.sp,
                    letterSpacing = 0.sp,
                    color = Black
                )
                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = coin.totalAmount.toString() + " " + coin.abbreviation,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                    letterSpacing = 0.sp,
                    color = Gray
                )
            }

            Column(
                modifier = Modifier.padding(start = 8.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = coin.constPrice + " $",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    lineHeight = 18.sp,
                    letterSpacing = 0.sp,
                    color = Black
                )
                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "${if (coin.totalProfit.toDouble() >= 0) "+" else ""}${
                        DoubleFormat.formatDouble(
                            coin.totalProfit.toDouble()
                        )
                    } $",
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                    letterSpacing = 0.sp,
                    color = if (coin.totalProfit.toDouble() >= 0) Green else Red
                )
            }
        }
    }
}
