package bista.shiddarth.expensemate.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import bista.shiddarth.expensemate.R
import bista.shiddarth.expensemate.model.Group

@Composable
fun GroupDetail(
    group: Group,
    navController: NavHostController,
    onGroupNameChange: (String) -> Unit, // TODO callback to handle group name change
    onAddFriend: (String) -> Unit, //TODO  callback to add a friend
    onLeaveGroup: () -> Unit, // TODO callback to leave the group
    onDeleteGroup: () -> Unit // TODO callback to delete the group
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        val groupMembers = listOf("Annie","Banana","Charlie")
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }

        Image(
            painter = painterResource(id = group.image),
            contentDescription = "Group Image",
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )


        var editedGroupName by remember { mutableStateOf(group.name) }
        OutlinedTextField(
            value = editedGroupName,
            onValueChange = {
                editedGroupName = it
                onGroupNameChange(it)
            },
            label = { Text("Group Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text("Members:", style = MaterialTheme.typography.bodySmall)
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(groupMembers) { member ->
                Text(member, modifier = Modifier.padding(4.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        //TODO("ADD FRIEND FUNCTIONALITY")
        Button(
            onClick = { onAddFriend("New Friend") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Friend to Group")
        }

        Spacer(modifier = Modifier.height(16.dp))

        //TODO("LEAVE GROUP FUNCTIONALITY")
        Button(
            onClick = onLeaveGroup,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Leave Group")
        }

        Spacer(modifier = Modifier.height(16.dp))

        //TODO("DELETE GROUP FUNCTIONALITY")

        Button(
            onClick = onDeleteGroup,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text("Delete Group", color = Color.White)
        }
    }
}