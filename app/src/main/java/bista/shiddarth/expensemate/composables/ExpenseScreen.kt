package bista.shiddarth.expensemate.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import bista.shiddarth.expensemate.R
import bista.shiddarth.expensemate.model.Expense
import bista.shiddarth.expensemate.model.Friend
import bista.shiddarth.expensemate.navigation.Screens
import bista.shiddarth.expensemate.screens.InitialAvatar
import bista.shiddarth.expensemate.ui.theme.kellyGreen
import bista.shiddarth.expensemate.ui.theme.surfaceGray
import bista.shiddarth.expensemate.viewModel.ExpenseViewModel
import bista.shiddarth.expensemate.viewModel.FriendViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddExpenseScreen(
    navController: NavHostController,
    expenseViewModel: ExpenseViewModel,
    friendViewModel: FriendViewModel
) {
    val selectedFriend = expenseViewModel.selectedFriend
    var expenseName by rememberSaveable { mutableStateOf("") }
    var amount by rememberSaveable { mutableStateOf("") }
    var percentage by remember { mutableFloatStateOf(50f) }
    val submitButtonEnabled = selectedFriend != null && expenseName.isNotBlank() && amount.isNotBlank()
    var payer by remember { mutableStateOf("You") }

    val focusManager = LocalFocusManager.current
    val expenseNameFocus = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        if (selectedFriend != null) {
            expenseNameFocus.requestFocus()
        }
    }

    Scaffold(
        topBar = { ExpenseTopBar(navController) },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Button(
                    onClick = {
                        val myShare = (amount.toDouble() * (percentage / 100)).let {
                            if (payer != "You") -it else it
                        }

                        val currentDate = LocalDate.now()
                        val month = currentDate.format(DateTimeFormatter.ofPattern("MMM"))
                        val date = currentDate.dayOfMonth.toString()

                        if (selectedFriend != null) {
                            friendViewModel.addExpense(
                                selectedFriend.id.toString(), Expense(
                                    month = month,
                                    date = date,
                                    category = expenseViewModel.selectedCategory,
                                    name = expenseName,
                                    price = myShare
                                )
                            )
                            navController.navigate("friendDetail/${selectedFriend.id}")
                        }
                    },
                    enabled = submitButtonEnabled,
                    colors = ButtonDefaults.buttonColors(
                        disabledContainerColor = Color.LightGray,
                        disabledContentColor = Color.Gray,
                        containerColor = kellyGreen,
                    )
                ) {
                    Text("Submit", color = Color.White)
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(start = 25.dp, end = 25.dp)
        ) {
            WithYouSection(navController, selectedFriend, expenseViewModel)
            Spacer(modifier = Modifier.padding(5.dp))

            HorizontalDivider(thickness = 1.dp, color = kellyGreen)
            Spacer(modifier = Modifier.padding(30.dp))

            if (selectedFriend != null) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CategoryIcon(expenseViewModel) { navController.navigate(Screens.SearchCategories.route) }
                    Spacer(modifier = Modifier.width(10.dp))

                    TextField(
                        value = expenseName,
                        onValueChange = { expenseName = it },
                        label = { Text("Expense Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(expenseNameFocus),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = {
                            if (expenseName.isNotBlank()) {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        }),
                        maxLines = 1,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFF0E1415),
                            focusedContainerColor = Color(0xFF0E1415),
                            disabledContainerColor = Color(0xFF0E1415),
                            cursorColor = kellyGreen,
                            focusedIndicatorColor = kellyGreen
                        )
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    DollarIcon()

                    Spacer(modifier = Modifier.width(10.dp))

                    TextField(
                        value = amount,
                        placeholder = { Text(text = "0.00") },
                        onValueChange = { amount = it },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            focusManager.clearFocus()
                        }),
                        maxLines = 1,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = surfaceGray,
                            focusedContainerColor = surfaceGray,
                            disabledContainerColor = surfaceGray,
                            cursorColor = kellyGreen,
                            focusedIndicatorColor = kellyGreen
                        )
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                if (expenseName.isNotBlank() && amount.isNotBlank()) {

                    PaymentSection(selectedFriend) { payer = it }
                    AmountSlider(
                        totalAmount = amount.toFloat(),
                        selectedPercentage = percentage,
                        onPercentageChanged = { percentage = it })
                }
            }
        }
    }
}

@Composable
private fun PaymentSection(
    selectedFriend: Friend,
    onPayerChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Paid By: ")
        Spacer(modifier = Modifier.width(10.dp))
        SingleChoiceSegmentedButton(
            selectedFriend = selectedFriend.firstName,
            onSelectionChanged = onPayerChange
        )
    }
}

@Composable
private fun DollarIcon() {
    Box(
        modifier = Modifier
            .size(60.dp)
            .border(
                width = 2.dp,
                color = kellyGreen,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFF8F0E3)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_money),
            tint = kellyGreen,
            contentDescription = "Dollar Sign",
            modifier = Modifier
                .size(40.dp)
        )
    }
}

@Composable
private fun CategoryIcon(
    expenseViewModel: ExpenseViewModel,
    onCategorySelect: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .border(
                width = 2.dp,
                color = kellyGreen,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .background(expenseViewModel.selectedCategory.backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = expenseViewModel.selectedCategory.categoryImage),
            contentDescription = expenseViewModel.selectedCategory.name,
            modifier = Modifier
                .size(40.dp)
                .background(expenseViewModel.selectedCategory.backgroundColor)
                .clickable { onCategorySelect() },
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ExpenseTopBar(navController: NavHostController) {
    TopAppBar(
        title = { Text("Add Expense", fontSize = 28.sp) },
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.statusBars)
            .offset(y = (-50).dp),
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@Composable
fun WithYouSection(
    navController: NavHostController,
    selectedFriend: Friend?,
    expenseViewModel: ExpenseViewModel
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
    ) {
        Text("With you and:", fontSize = 16.sp)
        Spacer(modifier = Modifier.width(8.dp))
        if (selectedFriend == null) {
            AssistChip(
                onClick = { navController.navigate(Screens.SearchFriendScreen.route) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = "Choose a friend",
                        tint = Color.Black
                    )
                },
                label = {
                    Text(
                        text = "Choose a friend",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                },
                border = BorderStroke(2.dp, Color.Yellow),
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = kellyGreen
                ),
                modifier = Modifier.height(50.dp)
            )
        } else {
            Chip(
                selectedFriend.firstName
            ) { expenseViewModel.resetSelectedFriend() }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    expenseViewModel: ExpenseViewModel,
    friendList: List<Friend>
) {
    var filteredFriends by remember { mutableStateOf(friendList) }
    var searchQuery by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TextField(
                        value = searchQuery,
                        placeholder = { Text(text = "Search friend") },
                        onValueChange = { userSearchQuery ->
                            searchQuery = userSearchQuery
                            filteredFriends = if (userSearchQuery.isBlank()) {
                                friendList
                            } else {
                                friendList.filter { friend ->
                                    listOf(friend.firstName, friend.lastName).any {
                                        it.contains(
                                            searchQuery,
                                            ignoreCase = true
                                        )
                                    }
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .focusRequester(focusRequester),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(cursorColor = kellyGreen)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(12.dp)
        ) {

            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(filteredFriends) { friend ->
                    SearchScreenFriendDetails(
                        friend,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                expenseViewModel.updateSelectedFriend(friend)
                                navController.popBackStack()
                                searchQuery = ""
                                filteredFriends = friendList
                            }
                            .padding(5.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SearchScreenFriendDetails(
    friend: Friend,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)

            ) {
                InitialAvatar(firstName = friend.firstName, lastName = friend.lastName, 15.sp)
            }

            Spacer(Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${friend.firstName} ${friend.lastName}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun Chip(name: String, onRemove: () -> Unit) {
    Surface(
        color = kellyGreen,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(4.dp),
        border = BorderStroke(2.dp, Color.White)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            Text(name)
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                Icons.Default.Close,
                contentDescription = "Remove",
                modifier = Modifier.clickable { onRemove() })
        }
    }
}

@Preview
@Composable
fun ChipPreview() {
    Chip(name = "Shiddarth Bista") {

    }
}

@Composable
fun SingleChoiceSegmentedButton(
    selectedFriend: String, onSelectionChanged: (String) -> Unit
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = listOf("You", selectedFriend)

    SingleChoiceSegmentedButtonRow {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = kellyGreen
                ),
                onClick = {
                    selectedIndex = index
                    onSelectionChanged(label)
                },
                selected = index == selectedIndex,
                label = { Text(label) }
            )
        }
    }
}

@Composable
fun AmountSlider(
    totalAmount: Float,
    selectedPercentage: Float,
    onPercentageChanged: (Float) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val selectedAmount = totalAmount * (selectedPercentage / 100f)

        Text(
            text = "You pay: $${"%.2f".format(selectedAmount)} (${selectedPercentage.toInt()}%)",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Slider(
            value = selectedPercentage,
            onValueChange = onPercentageChanged,
            valueRange = 0f..100f,
            steps = 100,
            modifier = Modifier.padding(horizontal = 16.dp),
            colors = SliderDefaults.colors(
                thumbColor = kellyGreen,
                activeTrackColor = kellyGreen,
                activeTickColor = kellyGreen
            )
        )
    }
}