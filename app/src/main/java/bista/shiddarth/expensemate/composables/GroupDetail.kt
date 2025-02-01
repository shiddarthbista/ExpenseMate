package bista.shiddarth.expensemate.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun GroupDetail(groupName: String, navController: NavHostController){
    Text("Group name: $groupName")
}