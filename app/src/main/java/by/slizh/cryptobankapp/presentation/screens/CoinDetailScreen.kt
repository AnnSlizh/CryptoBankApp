package by.slizh.cryptobankapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.slizh.cryptobankapp.Coin
import by.slizh.cryptobankapp.R
import by.slizh.cryptobankapp.presentation.components.bars.CustomTopAppBar
import by.slizh.cryptobankapp.presentation.components.PriceBox
import by.slizh.cryptobankapp.presentation.components.cards.ReplenishmentCard
import by.slizh.cryptobankapp.ui.theme.Black
import by.slizh.cryptobankapp.ui.theme.Gray
import by.slizh.cryptobankapp.ui.theme.Green

@Composable
fun CoinDetailScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTopAppBar(Coin.BITCOIN.coinName)

        Spacer(modifier = Modifier.height(32.dp))

        Image(
            painter = painterResource(id = Coin.BITCOIN.photoCoinResId), contentDescription = "",
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "24 715 \$",
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.sp,
            color = Black
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "+341,14 \$ (+24,1 %)",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            lineHeight = 14.sp,
            letterSpacing = 0.sp,
            color = Green
        )
        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            PriceBox(
                title = stringResource(id = R.string.average_purchase_price),
                price = "79 129,13",
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            PriceBox(
                title = stringResource(id = R.string.current_price),
                price = "97 673,84",
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(id = R.string.transaction),
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.sp,
            color = Gray,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(3) {
                ReplenishmentCard(
                    onClick = { }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CoinDetailScreenPreview() {
    CoinDetailScreen()
}