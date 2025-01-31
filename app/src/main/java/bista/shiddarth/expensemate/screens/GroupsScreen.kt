package bista.shiddarth.expensemate.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import bista.shiddarth.expensemate.R
import bista.shiddarth.expensemate.ui.theme.kellyGreen

@Composable
fun GroupScreen(
    onAddExpenseClick: () -> Unit,    // Handle Add Expense action

) {
    val listState = rememberLazyListState()
    val expandedFab by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { /* do something */ },
                expanded = expandedFab,
                containerColor = kellyGreen,
                contentColor = Color.White,
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_money),
                        contentDescription = "Add expenses",
                    )
                },
                text = {
                    Text(
                        text = "Add Expenses",
                        fontWeight = FontWeight.ExtraBold
                    )
                },
            )
        },
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
                    .padding(innerPadding)
            ) {
                for (index in 0 until 60) {
                    item { Text(text = "List item - $index", modifier = Modifier.padding(24.dp)) }
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter,
            ) {
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    colors = ButtonColors(
                        contentColor = kellyGreen,
                        containerColor = Color(0xFF202124),
                        disabledContentColor = Color.Transparent,
                        disabledContainerColor = Color.Red
                    ),
                    border = BorderStroke(width = 1.0.dp, color = kellyGreen)
                ) {
                    Text(text = "Create a new group")
                }
            }
        }

    }

}

@Preview
@Composable
fun GroupDetails() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(80.dp),
    ) {
        Box(modifier = Modifier.size(72.dp)
            .clip(RoundedCornerShape(8.dp)))
        Image(
            painterResource(id = R.drawable.flowers), contentDescription = "test",
            modifier = Modifier.clip(RoundedCornerShape(8.dp))
        )

        Spacer(Modifier.width(16.dp))


        Column {
            Text(text = "Group Name")
            Text(text = "settled")
        }
    }

}

