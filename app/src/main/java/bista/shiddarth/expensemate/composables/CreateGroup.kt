package bista.shiddarth.expensemate.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import bista.shiddarth.expensemate.R
import bista.shiddarth.expensemate.model.Group
import bista.shiddarth.expensemate.viewModel.GroupViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGroup(
    navController: NavHostController,
    groupViewModel: GroupViewModel = viewModel()
) {
    var groupName by remember { mutableStateOf("") }
    val groupNameFocusRequester = remember { FocusRequester() }
    var isGroupFieldBlank by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        groupNameFocusRequester.requestFocus()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Create a group") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (groupName.isBlank()){
                            isGroupFieldBlank = true
                        } else {
                            isGroupFieldBlank = false
                            groupViewModel.addNewGroup(
                                Group(
                                    groupName,
                                    R.drawable.background6,
                                    "New group created"
                                )
                            )
                            navController.popBackStack()
                        }
                    }) {
                        Icon(Icons.Filled.Done, "Done")
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
                value = groupName,
                onValueChange = { it ->
                    groupName = it.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                },
                label = { Text("Group Name") },
                modifier = Modifier.fillMaxWidth()
                    .focusRequester(groupNameFocusRequester),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    if (groupName.isBlank()){
                        isGroupFieldBlank = true
                    } else {
                        groupViewModel.addNewGroup(
                            Group(
                                groupName,
                                R.drawable.background6,
                                "New group created"
                            )
                        )
                        navController.popBackStack()
                    }
                }),
                isError = isGroupFieldBlank,
                supportingText = { if (isGroupFieldBlank) Text(text = "Group name cannot be empty") }
            )
        }
    }
}