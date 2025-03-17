package by.slizh.cryptobankapp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import by.slizh.cryptobankapp.ui.theme.BlueDefault
import by.slizh.cryptobankapp.ui.theme.Gray

@Composable
fun AddTransactionTextField(
    value: String,
    onValueChange: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = 52.sp,
                lineHeight = 52.sp,
                letterSpacing = 0.sp,
                color = Gray,
                textAlign = TextAlign.Center
            ),
            singleLine = true,
            cursorBrush = SolidColor(BlueDefault),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = "0",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 52.sp,
                            lineHeight = 52.sp,
                            letterSpacing = 0.sp,
                            color = Gray,
                            textAlign = TextAlign.Center
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}
