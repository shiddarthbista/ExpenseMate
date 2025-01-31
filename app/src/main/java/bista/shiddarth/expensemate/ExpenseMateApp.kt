package bista.shiddarth.expensemate

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import bista.shiddarth.expensemate.composables.CreateGroup
import bista.shiddarth.expensemate.model.Group
import bista.shiddarth.expensemate.navigation.Screens
import bista.shiddarth.expensemate.screens.GroupScreen
import bista.shiddarth.expensemate.ui.theme.kellyGreen

@Composable
fun ExpenseMateApp() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination
    var selectedScreen by remember { mutableIntStateOf(0) }

    val groups = remember {
        mutableStateListOf(
            Group("Group 1", R.drawable.background2, "Group 1"),
            Group("Group 2", R.drawable.background1, "Group 2"),
            Group("Group 3", R.drawable.background3, "Group 3"),
            Group("Group 4", R.drawable.background1, "Group 4"),
            Group("Group 5", R.drawable.background1, "Group 5"),
            Group("Group 6", R.drawable.background1, "Group 6"),
            Group("Group 7", R.drawable.background1, "Group 7")
        )
    }

    val screens = listOf(
        Screens.GroupScreen,
        Screens.FriendsScreen,
        Screens.ActivityScreen,
        Screens.AccountScreen,
    )

    Scaffold(
        bottomBar = {
            if (currentDestination?.route != Screens.CreateGroup.route) {
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
                            label = { Text(text = screens.label) },
                            icon = {
                                Icon(
                                    painter = painterResource(id = screens.icon),
                                    contentDescription = screens.route,
                                    tint = if (selectedScreen == index) {
                                        kellyGreen
                                    } else {
                                        Color.White
                                    }
                                )
                            })
                    }
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
                GroupScreen(groups, navController, onGroupCreated = {
                    groups.add(it)
                }, onAddExpenseClick = {})
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
            composable(Screens.CreateGroup.route) {
                CreateGroup(navController, onGroupCreated = {
                    groups.add(it)
                })
            }
        }

    }

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