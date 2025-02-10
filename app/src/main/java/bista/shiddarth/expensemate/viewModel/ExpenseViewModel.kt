package bista.shiddarth.expensemate.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ExpenseViewModel: ViewModel() {
    var selectedFriend by mutableStateOf<String?>(null)
        private set

    fun updateSelectedFriend(friend: String) {
        selectedFriend = friend
    }

    fun resetSelectedFriend() {
        selectedFriend = null
    }
}