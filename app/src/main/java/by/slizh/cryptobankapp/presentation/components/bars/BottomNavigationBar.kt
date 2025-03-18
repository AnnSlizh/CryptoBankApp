package by.slizh.cryptobankapp.presentation.components.bars

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import by.slizh.cryptobankapp.R
import by.slizh.cryptobankapp.presentation.navigation.Screen
import by.slizh.cryptobankapp.ui.theme.Black
import by.slizh.cryptobankapp.ui.theme.LightGray

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        Screen.HomeScreen to stringResource(id = R.string.home),
        Screen.SettingsScreen to stringResource(id = R.string.settings)
    )

    val currentRoute = currentRoute(navController)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(start = 50.dp, end = 50.dp, bottom = 12.dp)
            .height(40.dp)
            .background(LightGray, shape = RoundedCornerShape(18.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { (screen, title) ->
            val isSelected = currentRoute == screen.route

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(4.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(if (isSelected) Color.White else Color.Transparent)
                    .clickable {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (isSelected) {
                        Icon(
                            painter = painterResource(
                                id = when (screen) {
                                    Screen.HomeScreen -> R.drawable.home_icon
                                    Screen.SettingsScreen -> R.drawable.settings_icon
                                    else -> throw IllegalStateException(stringResource(id = R.string.navigation_error))
                                }
                            ),
                            contentDescription = screen.route,
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }

                    Text(
                        text = title,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        lineHeight = 14.sp,
                        letterSpacing = 0.sp,
                        color = Black
                    )
                }
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}