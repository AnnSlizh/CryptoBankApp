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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.slizh.cryptobankapp.R
import by.slizh.cryptobankapp.ui.theme.Black
import by.slizh.cryptobankapp.ui.theme.Gray
import by.slizh.cryptobankapp.ui.theme.Green
import by.slizh.cryptobankapp.ui.theme.LightGray

@Composable
fun ReplenishmentCard(onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = LightGray),
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.plus_icon), contentDescription = "",
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
                    text = stringResource(id = R.string.replenishment),
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    lineHeight = 18.sp,
                    letterSpacing = 0.sp,
                    color = Black
                )
                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "11.02.2025",
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                    letterSpacing = 0.sp,
                    color = Gray
                )
            }

            Text(
                text = "+152 \$",
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                lineHeight = 18.sp,
                letterSpacing = 0.sp,
                color = Green
            )
        }
    }
}
