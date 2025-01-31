package bista.shiddarth.expensemate.navigation

import bista.shiddarth.expensemate.R


sealed class Screens(val route: String,val label:String, val icon: Int) {
    data object GroupScreen : Screens("group_screen","Groups", R.drawable.ic_groups)
    data object FriendsScreen : Screens("friends_screen","Friends", R.drawable.ic_user)
    data object ActivityScreen : Screens("activity_screen", "Activity",R.drawable.ic_activity)
    data object AccountScreen : Screens("account_screen", "Account", R.drawable.ic_account)
    data object CreateGroup : Screens("create_group_screen", "Create_Group", R.drawable.ic_groups)
}