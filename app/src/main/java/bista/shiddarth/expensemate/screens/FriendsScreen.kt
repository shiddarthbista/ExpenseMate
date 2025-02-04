package bista.shiddarth.expensemate.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import bista.shiddarth.expensemate.R
import bista.shiddarth.expensemate.composables.AddExpensesFAB
import bista.shiddarth.expensemate.model.Friend
import bista.shiddarth.expensemate.navigation.Screens
import bista.shiddarth.expensemate.ui.theme.kellyGreen
import kotlin.math.absoluteValue


@Composable
fun FriendsScreen(
    friendList: MutableList<Friend>,
    navController: NavHostController,
    onAddExpenseClick: () -> Unit
) {
    val listState = rememberLazyListState()
    val expandedFab by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }
    Scaffold(
        floatingActionButton = { AddExpensesFAB(expandedFab) },
        floatingActionButtonPosition = FabPosition.End,
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding),

                ) {
                itemsIndexed( friendList.sortedWith(compareBy<Friend> { it.firstName }.thenBy { it.lastName })) { _, friend ->
                    FriendDetails(
                        friend = friend,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .clickable {
                                navController.navigate("friendDetail/${friend.id}")
                            }
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter,
            ) {
                OutlinedButton(
                    onClick = {
                        navController.navigate(Screens.CreateGroup.route)
                    },
                    colors = ButtonColors(
                        contentColor = kellyGreen,
                        containerColor = Color(0xFF202124),
                        disabledContentColor = Color.Transparent,
                        disabledContainerColor = Color.Red
                    ),
                    border = BorderStroke(width = 1.0.dp, color = kellyGreen)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painterResource(id = R.drawable.group_add), "add",
                            Modifier
                                .size(24.dp)
                                .padding(end = 5.dp)
                        )
                        Text(stringResource(id = R.string.add_more_friends))
                    }
                }
            }
        }
    }
}

@Composable
fun FriendDetails(
    friend: Friend,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)

            ) {
                InitialAvatar(firstName = friend.firstName, lastName = friend.lastName,18.sp)
            }

            Spacer(Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${friend.firstName} ${friend.lastName}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = friend.email,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Clip
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 15.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                val totalBalance = friend.expenses.sumOf { it.price }

                BalanceText(balance = totalBalance)
            }
        }
    }
}


@Composable
fun InitialAvatar(firstName: String, lastName: String, fontSize: TextUnit) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(kellyGreen),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${firstName.first()}${lastName.first()}",
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            fontSize = fontSize
        )
    }
}

@Composable
fun BalanceText(balance: Double) {
    if (balance != 0.0) {
        val formattedBalance = "$%.2f".format(balance.absoluteValue)
        Text(
            text = if (balance > 0) formattedBalance else formattedBalance,
            style = MaterialTheme.typography.bodyLarge,
            color = if (balance > 0) Color(0xFF4CAF50) else Color(0xFFF44336),
            fontWeight = FontWeight.Bold
        )
    }
}