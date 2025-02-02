package bista.shiddarth.expensemate.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import bista.shiddarth.expensemate.R
import bista.shiddarth.expensemate.model.Group

class GroupViewModel : ViewModel() {

    val groups = mutableStateListOf<Group>()

    init {
        groups.addAll(
            listOf(
                Group("Group 1", R.drawable.background2, "Group 1"),
                Group("Group 2", R.drawable.background1, "Group 2"),
                Group("Group 3", R.drawable.background3, "Group 3"),
                Group("Group 4", R.drawable.background1, "Group 4")
            )
        )
    }

    fun addNewGroup(group: Group) {
        groups.add(group)
    }

    fun findGroup(groupName: String): Group {
        val selectedGroup =  groups.find { it.name == groupName } ?: Group("1",R.drawable.ic_groups,"NO group found")
        println(selectedGroup)
        return selectedGroup
    }
}