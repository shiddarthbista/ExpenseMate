package bista.shiddarth.expensemate.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import bista.shiddarth.expensemate.R
import bista.shiddarth.expensemate.model.Category
import bista.shiddarth.expensemate.model.Friend

class ExpenseViewModel: ViewModel() {
    var selectedFriend by mutableStateOf<Friend?>(null)
        private set

    var selectedCategory by mutableStateOf(
        Category("General", R.drawable.ic_general, Color(0xFFFFFFFF))
    )
        private set

    fun updateSelectedFriend(friend: Friend) {
        selectedFriend = friend
    }

    fun resetSelectedFriend() {
        selectedFriend = null
    }

    fun updateCategory(category: Category) {
        selectedCategory = category
    }


}