package bista.shiddarth.expensemate.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import bista.shiddarth.expensemate.R
import bista.shiddarth.expensemate.model.Friend
import java.util.UUID

class FriendViewModel : ViewModel() {

    val friends = mutableStateListOf<Friend>()

    init {
        loadInitialFriends()
    }

    private fun loadInitialFriends() {
        friends.addAll(
            listOf(
                Friend(UUID.randomUUID(),"Gary", "Whittaker", R.drawable.background3, "avc@gmail.com"),
                Friend(UUID.randomUUID(),"Tim", "Horton", R.drawable.ic_user, "avc@gmail.com"),
                Friend(UUID.randomUUID(),"Alice", "Wonderland", R.drawable.background3, "avc@gmail.com")
            ),
        )
    }

    fun addFriend(friend: Friend) {
        friends.add(friend)
    }

    fun removeFriend(friend: Friend) {
        friends.remove(friend)
    }

    fun findFriend(friendId: String): Friend? {
        return friends.find { it.id.toString() == friendId }
    }
}