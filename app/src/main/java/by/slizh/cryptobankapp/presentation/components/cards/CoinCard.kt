package by.slizh.cryptobankapp.presentation.components.cards

import androidx.compose.foundation.Image
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.slizh.cryptobankapp.domain.CoinEnum
import by.slizh.cryptobankapp.ui.theme.Black
import by.slizh.cryptobankapp.ui.theme.GrayClick
import by.slizh.cryptobankapp.ui.theme.LightGray

@Composable
fun CoinCard(coin: CoinEnum, onClick: () -> Unit) {
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
                .padding(start = 12.dp, top = 10.5.dp, end = 12.dp, bottom = 10.5.dp),
        ) {
            Image(
                painter = painterResource(id = coin.photoCoinResId), contentDescription = "",
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = coin.coinName,
                modifier = Modifier.padding(top = 5.5.dp, bottom = 5.5.dp),
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                lineHeight = 18.sp,
                letterSpacing = 0.sp,
                color = Black
            )
        }
    }
}
