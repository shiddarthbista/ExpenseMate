package bista.shiddarth.expensemate.composables

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import bista.shiddarth.expensemate.R
import bista.shiddarth.expensemate.ui.theme.kellyGreen

@Composable
fun AddExpensesFAB(expandedFab : Boolean) {
    ExtendedFloatingActionButton(
        onClick = { /* do something */ },
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
