package by.slizh.cryptobankapp.presentation.screens

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import by.slizh.cryptobankapp.R
import by.slizh.cryptobankapp.presentation.components.cards.CoinHomeCard
import by.slizh.cryptobankapp.presentation.navigation.Screen
import by.slizh.cryptobankapp.presentation.viewModels.home.HomeViewModel
import by.slizh.cryptobankapp.ui.theme.Black
import by.slizh.cryptobankapp.ui.theme.Gray
import by.slizh.cryptobankapp.ui.theme.Green
import by.slizh.cryptobankapp.ui.theme.Red
import by.slizh.cryptobankapp.util.DoubleFormat
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {
    val context = LocalContext.current

    val state by homeViewModel.state.collectAsState()
    val profitTotal by homeViewModel.profitTotal.collectAsState()
    val profitPercentage by homeViewModel.profitPercentage.collectAsState()

    val totalPrice = state.sumOf { it.totalPrice.toDoubleOrNull() ?: 0.0 }

    val gifEnabledLoader = remember {
        ImageLoader.Builder(context)
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
    }

    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxSize(),
        //    verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 53.dp),
            text = "Total",
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.sp,
            color = Gray
        )
        Spacer(modifier = Modifier.height(8.dp))


        Text(
            text = "$totalPrice USD",
            fontWeight = FontWeight.SemiBold,
            fontSize = 32.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp,
            color = Black
        )

        if (state.isEmpty()) {
            Spacer(modifier = Modifier.height(97.dp))

            AsyncImage(
                model = R.drawable.banana,
                contentDescription = "",
                modifier = Modifier.size(128.dp),
                imageLoader = gifEnabledLoader
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.null_transaction),
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.sp,
                color = Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
        } else {
            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Text(
                    text = "${if (profitTotal >= 0) "+" else ""}${DoubleFormat.formatDouble(profitTotal)} $ " +
                            "(${if (profitPercentage >= 0) "+" else ""}${DoubleFormat.formatDouble(profitPercentage)}%)"
                    ,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                    letterSpacing = 0.sp,
                    color = if (profitTotal >= 0) Green else Red
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = stringResource(id = R.string.all_time),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                    letterSpacing = 0.sp,
                    color = Gray
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .clickable { navController.navigate(Screen.AddTransactionScreen.route) }
        ) {
            Image(
                painter = painterResource(id = R.drawable.add_transaction_btn),
                contentDescription = stringResource(id = R.string.add_transaction)
            )
        }
        Spacer(modifier = Modifier.height(32.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state) { coin ->
                CoinHomeCard(
                    coin = coin,
                    onClick = {
                        navController.navigate(
                            route = Screen.CoinDetailScreen.createRoute(coinName = coin.coinName)
                        )
                    }
                )
            }
        }
    }
}