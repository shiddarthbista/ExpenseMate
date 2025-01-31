package bista.shiddarth.expensemate

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import bista.shiddarth.expensemate.navigation.Screens

@Composable
fun ExpenseMateApp() {
    val navController = rememberNavController()
    var selectedScreen by remember { mutableIntStateOf(0) }


    val screens = listOf(
        Screens.GroupScreen,
        Screens.FriendsScreen,
        Screens.ActivityScreen,
        Screens.AccountScreen,
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF202124)
            ) {
                screens.forEachIndexed { index, screens ->
                    NavigationBarItem(
                        colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
                        selected = selectedScreen == index,
                        onClick = {
                            navController.navigate(screens.route)
                            selectedScreen = index
                        },
                        label = { Text(text = screens.label)},
                        icon = {
                            Icon(
                                painter = painterResource(id = screens.icon),
                                contentDescription = screens.route,
                                tint = if (selectedScreen == index) {
                                    Color(0xFF1EC29F)
                                } else {
                                    Color.White
                                }
                            )
                        })
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.GroupScreen.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screens.GroupScreen.route) {
                GroupScreen()
            }
            composable(Screens.FriendsScreen.route) {
                FriendsScreen()
            }
            composable(Screens.ActivityScreen.route) {
                ActivityScreen()
            }
            composable(Screens.AccountScreen.route) {
                AccountScreen()
            }
        }

    }

}

@Composable
fun GroupScreen() {
    Text(text = "Group Screen")
}

@Composable
fun FriendsScreen() {
    Text(text = "Friends Screen")
}

@Composable
fun ActivityScreen() {
    Text(text = "Activity Screen")
}

@Composable
fun AccountScreen() {
    Text(text = "Account Screen")
}