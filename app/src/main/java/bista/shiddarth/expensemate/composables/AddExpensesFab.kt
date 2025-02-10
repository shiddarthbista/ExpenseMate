package bista.shiddarth.expensemate.composables

import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import bista.shiddarth.expensemate.R
import bista.shiddarth.expensemate.navigation.Screens
import bista.shiddarth.expensemate.ui.theme.kellyGreen

@Composable
fun AddExpensesFAB(expandedFab : Boolean, navController: NavHostController) {
    ExtendedFloatingActionButton(
        onClick = { navController.navigate(Screens.ExpenseScreen.route) },
        expanded = expandedFab,
        containerColor = kellyGreen,
        contentColor = Color.White,
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_money),
                contentDescription = stringResource(id = R.string.add_expenses),
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.add_expenses),
                fontWeight = FontWeight.ExtraBold
            )
        },
    )
}
