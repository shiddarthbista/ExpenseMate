package bista.shiddarth.expensemate.navigation

sealed class Screens(val route: String) {
    object GroupScreen : Screens("group_screen")
    object FriendsScreen : Screens("friends_screen")
    object ActivityScreen : Screens("activity_screen")
    object AccountScreen : Screens("account_screen")
}