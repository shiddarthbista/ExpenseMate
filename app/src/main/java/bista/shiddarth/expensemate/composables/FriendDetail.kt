package bista.shiddarth.expensemate.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import bista.shiddarth.expensemate.model.Expense
import bista.shiddarth.expensemate.model.Friend
import bista.shiddarth.expensemate.screens.BalanceText
import bista.shiddarth.expensemate.screens.InitialAvatar
import bista.shiddarth.expensemate.ui.theme.kellyGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendDetail(
    friend: Friend,
    navController: NavHostController,
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val lazyListState = rememberLazyListState()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Column(horizontalAlignment = Alignment.Start) {

                MediumTopAppBar(
                    title = {
                        // collapsed state
                        if (scrollBehavior.state.collapsedFraction == 1f) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "${friend.firstName} ${friend.lastName}",
                                    color = Color.White
                                )
                            }
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior,
                    colors = TopAppBarColors(
                        containerColor = Color(0xFF008080), Color(0xFF008080), Color.Yellow,
                        Color.Black, Color.Blue
                    )
                )
                //expanded state
                if (scrollBehavior.state.collapsedFraction < 1f) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .offset(y = (-40).dp)
                            .padding(start = 50.dp)
                    ) {
                        InitialAvatar(
                            firstName = friend.firstName,
                            lastName = friend.lastName,
                            40.sp
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "${friend.firstName} ${friend.lastName}",
                            fontSize = 18.sp,
                            color = Color.White
                        )

                    }
                }
            }
        }
    ) { paddingValues ->
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LazyColumn(
                state = lazyListState,
                contentPadding = paddingValues,
                modifier = Modifier
                    .background(Color(0xFF0E1415))
                    .weight(1f)
            ) {
                items(
                    items = friend.expenses,
                    key = { expense -> expense.id }
                ) { expense ->
                    ExpenseRow(expense = expense)
                    if (friend.expenses.size > 1) {
                        HorizontalDivider(thickness = 1.dp, color = Color.Gray)
                    }
                }
            }

            val totalBalance = friend.expenses.sumOf { it.price }
            TotalBalanceDisplay(totalBalance)

        }
    }
}

@Composable
private fun TotalBalanceDisplay(totalBalance: Double) {
    Box(
        Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "Total Balance: $totalBalance",
            color = if (totalBalance > 0) kellyGreen else Color.Red
        )
    }
}


@Composable
fun ExpenseRow(expense: Expense) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = expense.month, style = MaterialTheme.typography.bodySmall)
            Text(text = expense.date, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.width(16.dp))

        Image(
            painter = painterResource(id = expense.category.categoryImage),
            contentDescription = expense.category.name,
            modifier = Modifier.size(40.dp)
                .background(expense.category.backgroundColor)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = expense.name,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        BalanceText(expense.price)
    }
}