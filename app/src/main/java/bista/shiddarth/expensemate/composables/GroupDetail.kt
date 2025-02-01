package bista.shiddarth.expensemate.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import bista.shiddarth.expensemate.R
import bista.shiddarth.expensemate.model.Group

@Composable
fun GroupDetail(group: Group, navController: NavHostController) {
    Column {
        Text("Group name: ${group.name}")
        Image(painter = painterResource(id = group.image), contentDescription = "test")
        Text("Group description: ${group.description}")
    }
}