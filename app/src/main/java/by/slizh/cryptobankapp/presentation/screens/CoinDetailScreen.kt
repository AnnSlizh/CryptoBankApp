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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import by.slizh.cryptobankapp.R
import by.slizh.cryptobankapp.presentation.components.bars.CustomTopAppBar
import by.slizh.cryptobankapp.presentation.components.PriceBox
import by.slizh.cryptobankapp.presentation.components.cards.ReplenishmentCard
import by.slizh.cryptobankapp.presentation.viewModels.coinDetail.CoinDetailEvent
import by.slizh.cryptobankapp.presentation.viewModels.coinDetail.CoinDetailViewModel
import by.slizh.cryptobankapp.ui.theme.Black
import by.slizh.cryptobankapp.ui.theme.Gray
import by.slizh.cryptobankapp.ui.theme.Green
import by.slizh.cryptobankapp.domain.CoinEnum
import by.slizh.cryptobankapp.ui.theme.Red
import by.slizh.cryptobankapp.util.DoubleFormat

@Composable
fun CoinDetailScreen(
    navController: NavController,
    coinName: String?,
    coinDetailViewModel: CoinDetailViewModel = hiltViewModel()
) {
    val state by coinDetailViewModel.state.collectAsState()
    val coinImageResId = CoinEnum.entries.find { it.coinName == coinName }?.photoCoinResId ?: R.drawable.bitcoin


    LaunchedEffect(coinName) {
        if (coinName != null) {
            coinDetailViewModel.onEvent(CoinDetailEvent.LoadTransactions(coinName))
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        coinName?.let { CustomTopAppBar(coinName = it, onClick = { navController.popBackStack() }) }

        Spacer(modifier = Modifier.height(32.dp))

        Image(
            painter = painterResource(id = coinImageResId),
            contentDescription = "",
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = state.constPrice + " $",
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.sp,
            color = Black
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${state.profitTotal} $ (${state.profitPercentage}%)",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            lineHeight = 14.sp,
            letterSpacing = 0.sp,
            color = if (state.profitTotal.toDouble() >= 0) Green else Red
        )
        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            PriceBox(
                title = stringResource(id = R.string.average_purchase_price),
                price = state.averagePurchasePrice,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            PriceBox(
                title = stringResource(id = R.string.current_price),
                price = state.currentPrice,
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
            items(state.transactions) { transaction ->
                ReplenishmentCard(transaction)
            }
        }
    }
}
