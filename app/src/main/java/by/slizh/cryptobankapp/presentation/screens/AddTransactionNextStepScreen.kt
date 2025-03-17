package by.slizh.cryptobankapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.slizh.cryptobankapp.Coin
import by.slizh.cryptobankapp.R
import by.slizh.cryptobankapp.presentation.components.AddTransactionTextField
import by.slizh.cryptobankapp.presentation.components.CustomButton
import by.slizh.cryptobankapp.presentation.components.SetPriceBottomSheet
import by.slizh.cryptobankapp.ui.theme.Black
import by.slizh.cryptobankapp.ui.theme.BlueClick
import by.slizh.cryptobankapp.ui.theme.BlueDefault
import by.slizh.cryptobankapp.ui.theme.Gray
import by.slizh.cryptobankapp.ui.theme.GrayClick
import by.slizh.cryptobankapp.ui.theme.LightGray

@Composable
fun AddTransactionNextStepScreen(modifier: Modifier = Modifier) {

    var value by remember { mutableStateOf("") }
    var showBottomSheet by remember { mutableStateOf(false) }
    var priceValue by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 24.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.add_coin_transaction, Coin.BITCOIN.coinName),
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.sp,
            color = Gray
        )
        Spacer(modifier = Modifier.height(117.dp))

        AddTransactionTextField(
            value = value,
            onValueChange = { value = it }
        )
        Spacer(modifier = Modifier.height(89.dp))

        Button(
            modifier = Modifier.height(33.dp),
            onClick = { showBottomSheet = true },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LightGray,
                contentColor = GrayClick
            ),
            contentPadding = PaddingValues(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
        ) {
            Text(
                text = priceValue.ifEmpty { stringResource(id = R.string.price_coin) },
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 14.sp,
                letterSpacing = 0.sp,
                color = Black
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            CustomButton(
                text = stringResource(id = R.string.cansel),
                containerColor = LightGray,
                contentColor = GrayClick,
                disabledContainerColor = LightGray.copy(alpha = 0.35f),
                textColor = BlueDefault,
                modifier = Modifier.weight(1f),
                onClick = {}
            )
            Spacer(modifier = Modifier.width(12.dp))

            CustomButton(
                text = stringResource(id = R.string.add),
                containerColor = BlueDefault,
                contentColor = BlueClick,
                disabledContainerColor = BlueDefault.copy(alpha = 0.35f),
                textColor = Color.White,
                modifier = Modifier.weight(1f),
                onClick = {}
            )
        }

        if (showBottomSheet) {
            SetPriceBottomSheet(
                onDismiss = { showBottomSheet = false },
                onSetPrice = { newPrice ->
                    priceValue = newPrice
                    showBottomSheet = false
                }
            )
        }
    }
}
