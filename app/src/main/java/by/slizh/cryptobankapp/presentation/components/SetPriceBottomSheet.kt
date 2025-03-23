package by.slizh.cryptobankapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.slizh.cryptobankapp.R
import by.slizh.cryptobankapp.ui.theme.Black
import by.slizh.cryptobankapp.ui.theme.BlueClick
import by.slizh.cryptobankapp.ui.theme.BlueDefault
import by.slizh.cryptobankapp.ui.theme.Gray
import by.slizh.cryptobankapp.ui.theme.LightGray
import by.slizh.cryptobankapp.ui.theme.Red
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetPriceBottomSheet(
    onDismiss: () -> Unit,
    onSetPrice: (String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var priceInput by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    ModalBottomSheet(
        modifier = Modifier.fillMaxSize(),
        containerColor = White,
        sheetState = sheetState,
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = priceInput,
                onValueChange = {
                    priceInput = it
                    isError = false
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.price),
                        fontWeight = FontWeight.Normal,
                        fontSize = 17.sp,
                        lineHeight = 22.sp,
                        letterSpacing = (-0.4).sp,
                        color = Gray
                    )
                },
                isError = isError, // Подключаем ошибку
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                textStyle = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 17.sp,
                    lineHeight = 22.sp,
                    letterSpacing = (-0.4).sp
                ),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Black,
                    unfocusedTextColor = DarkGray,
                    focusedContainerColor = LightGray,
                    unfocusedContainerColor = LightGray,
                    focusedBorderColor =  LightGray,
                    unfocusedBorderColor =  LightGray,
                    cursorColor = BlueDefault,
                    disabledContainerColor = LightGray.copy(alpha = 0.35f)
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            CustomButton(
                text = stringResource(id = R.string.set_price),
                containerColor = BlueDefault,
                contentColor = BlueClick,
                disabledContainerColor = BlueDefault.copy(alpha = 0.35f),
                textColor = White,
                modifier = Modifier.fillMaxWidth(),
                enabled = true,
                onClick = {
                    if (priceInput.isBlank()) {
                        isError = true
                    } else {
                        onSetPrice(priceInput)
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                onDismiss()
                            }
                        }
                    }
                }
            )
        }
    }
}
