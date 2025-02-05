package bista.shiddarth.expensemate.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import bista.shiddarth.expensemate.R
import bista.shiddarth.expensemate.model.Friend
import bista.shiddarth.expensemate.viewModel.FriendViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFriend(
    navController: NavHostController,
    friendViewModel: FriendViewModel = viewModel()
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isFirstNameError by remember { mutableStateOf(false) }
    var isLastNameError by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val firstNameFocusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        firstNameFocusRequester.requestFocus()
    }

    fun validateFields(): Boolean {
        isFirstNameError = firstName.isBlank()
        isLastNameError = lastName.isBlank()
        isEmailError = email.isBlank()
        return !isFirstNameError && !isLastNameError && !isEmailError
    }

    val isFieldsValid = !isFirstNameError && !isLastNameError && !isEmailError

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Add A Friend") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            if (validateFields()) {
                                friendViewModel.addNewFriend(
                                    Friend(
                                        firstName = firstName,
                                        lastName = lastName,
                                        email = email
                                    )
                                )
                                navController.popBackStack()
                            }
                        },
                        enabled = isFieldsValid
                    ) {
                        Icon(Icons.Filled.Done, "Done",
                            tint = if (isFieldsValid) Color.Black else Color.Gray)
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
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = firstName,
                onValueChange = { it ->
                    firstName =
                        it.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                },
                label = { Text(stringResource(id = R.string.first_name)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(firstNameFocusRequester),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {
                    if (firstName.isBlank()) {
                        isFirstNameError = true
                    } else {
                        isFirstNameError = false
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                }
                ),
                isError = isFirstNameError,
                supportingText = { if (isFirstNameError) Text(text = "First name cannot be empty") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = lastName,
                onValueChange = { it ->
                    lastName =
                        it.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                },
                label = { Text(stringResource(id = R.string.last_name)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {
                    if (lastName.isBlank()) {
                        isLastNameError = true
                    } else {
                        isLastNameError = false
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                }),
                isError = isLastNameError,
                supportingText = { if (isLastNameError) Text(text = "Last name cannot be empty") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(stringResource(id = R.string.email)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    if (email.isBlank()) {
                        isEmailError = true
                    } else {
                        isEmailError = false
                        friendViewModel.addNewFriend(
                            Friend(
                                firstName = firstName,
                                lastName = lastName,
                                email = email
                            )
                        )
                        navController.popBackStack()
                    }
                }),
                isError = isEmailError,
                supportingText = { if (isEmailError) Text(text = "Email Address cannot be empty") }
            )
        }
    }
}