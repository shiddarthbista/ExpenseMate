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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import bista.shiddarth.expensemate.composables.AddFriend
import bista.shiddarth.expensemate.composables.CreateGroup
import bista.shiddarth.expensemate.composables.FriendDetail
import bista.shiddarth.expensemate.composables.GroupDetail
import bista.shiddarth.expensemate.navigation.Screens
import bista.shiddarth.expensemate.screens.FriendsScreen
import bista.shiddarth.expensemate.screens.GroupScreen
import bista.shiddarth.expensemate.ui.theme.expenseMateGray
import bista.shiddarth.expensemate.ui.theme.kellyGreen
import bista.shiddarth.expensemate.viewModel.FriendViewModel
import bista.shiddarth.expensemate.viewModel.GroupViewModel

@Composable
fun ExpenseMateApp() {
    val groupViewModel: GroupViewModel = viewModel()
    val friendViewModel: FriendViewModel = viewModel()
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination
    var selectedScreen by remember { mutableIntStateOf(0) }

    val screens = listOf(
        Screens.GroupScreen,
        Screens.FriendsScreen,
        Screens.ActivityScreen,
        Screens.AccountScreen,
    )

    Scaffold(
        bottomBar = {
            if (currentDestination?.route != Screens.CreateGroup.route || currentDestination.route != Screens.AddFriend.route) {
                NavigationBar(
                    containerColor = expenseMateGray
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
                GroupScreen(groupViewModel.groups, navController, onAddExpenseClick = {})
            }
            composable(Screens.FriendsScreen.route) {
                FriendsScreen(friendViewModel.friends, navController, onAddExpenseClick = {})
            }
            composable(Screens.ActivityScreen.route) {
                ActivityScreen()
            }
            composable(Screens.AccountScreen.route) {
                AccountScreen()
            }
            composable(
                route = Screens.GroupDetail.route,
                arguments = listOf(navArgument("groupName") { type = NavType.StringType })
            ) { backStackEntry ->
                val groupName = backStackEntry.arguments?.getString("groupName").orEmpty()
                val selectedGroup = groupViewModel.findGroup(groupName)
                GroupDetail(selectedGroup, navController, {},{},{},{})
            }
            composable(
                route = Screens.FriendDetail.route,
                arguments = listOf(navArgument("friendId") { type = NavType.StringType })
            ) { backStackEntry ->
                val friendId = backStackEntry.arguments?.getString("friendId").orEmpty()
                val selectedFriend = friendViewModel.findFriend(friendId)
                FriendDetail(selectedFriend, navController)
            }
            composable(Screens.CreateGroup.route) {
                CreateGroup(navController,groupViewModel)
            }
            composable(Screens.AddFriend.route) {
                AddFriend(navController, friendViewModel)
            }


        }
    }

}

@Composable
fun ActivityScreen() {
    Text(text = "Activity Screen")
}

@Preview
@Composable
fun AccountScreen() {
    Text(text = "Account Screen")
}