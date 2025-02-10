package bista.shiddarth.expensemate.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import bista.shiddarth.expensemate.model.Category

class ExpenseViewModel: ViewModel() {
    var selectedFriend by mutableStateOf<String?>(null)
        private set

    var selectedCategory by mutableStateOf<Category?>(null)
        private set

    fun updateSelectedFriend(friend: String) {
        selectedFriend = friend
    }

    fun resetSelectedFriend() {
        selectedFriend = null
    }

    fun updateCategory(category: Category) {
        selectedCategory = category
    }


}