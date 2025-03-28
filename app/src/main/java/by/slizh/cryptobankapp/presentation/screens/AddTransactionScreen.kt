package by.slizh.cryptobankapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import by.slizh.cryptobankapp.domain.CoinEnum
import by.slizh.cryptobankapp.R
import by.slizh.cryptobankapp.presentation.components.CustomButton
import by.slizh.cryptobankapp.presentation.components.cards.CoinCard
import by.slizh.cryptobankapp.presentation.navigation.Screen
import by.slizh.cryptobankapp.presentation.viewModels.addTransaction.AddTransactionEvent
import by.slizh.cryptobankapp.presentation.viewModels.addTransaction.AddTransactionViewModel
import by.slizh.cryptobankapp.ui.theme.BlueDefault
import by.slizh.cryptobankapp.ui.theme.Gray
import by.slizh.cryptobankapp.ui.theme.GrayClick
import by.slizh.cryptobankapp.ui.theme.LightGray

@Composable
fun AddTransactionScreen(
    navController: NavController,
    addTransactionViewModel: AddTransactionViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.add_transaction),
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.sp,
                color = Gray
            )
            Spacer(modifier = Modifier.height(32.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(CoinEnum.entries.toTypedArray()) { coin ->
                    CoinCard(
                        coin,
                        onClick = {
                            addTransactionViewModel.onEvent(AddTransactionEvent.SelectCoin(coin.coinName))
                            navController.navigate(
                                route = Screen.AddTransactionNextStepScreen.createRoute(
                                    coinName = coin.coinName
                                )
                            )
                        }
                    )
                }
            }
        }
        CustomButton(
            text = stringResource(id = R.string.cansel),
            containerColor = LightGray,
            contentColor = GrayClick,
            disabledContainerColor = LightGray.copy(alpha = 0.35f),
            textColor = BlueDefault,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            enabled = true,
            onClick = { navController.popBackStack() }
        )
    }
}
