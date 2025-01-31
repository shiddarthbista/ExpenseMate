package bista.shiddarth.expensemate.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import bista.shiddarth.expensemate.R
import bista.shiddarth.expensemate.model.Group
import bista.shiddarth.expensemate.ui.theme.kellyGreen

@Composable
fun GroupScreen(
    groupList: List<Group>,
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
                itemsIndexed(groupList) { _, group ->
                    GroupDetails(
                        group = group,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
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
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painterResource(id = R.drawable.group_add), "add",
                            Modifier
                                .size(24.dp)
                                .padding(end = 5.dp)
                        )
                        Text(stringResource(id = R.string.create_new_group))
                    }
                }
            }
        }
    }

}

@Composable
fun GroupDetails(
    group: Group,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp))

            ) {
                Image(
                    painter = painterResource(id = group.image),
                    contentDescription = null,
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.FillBounds
                )
            }

            Spacer(Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = group.name,
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = group.description,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}