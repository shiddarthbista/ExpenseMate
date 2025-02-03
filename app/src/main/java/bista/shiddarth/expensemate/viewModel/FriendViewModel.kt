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
                Friend(UUID.randomUUID(),"Gary", "Whittaker", "gwhittaker@gmail.com",0.0),
                Friend(UUID.randomUUID(),"Tim", "Horton", "timhortins@hotmail.com", 10.0),
                Friend(UUID.randomUUID(),"Alice", "Wonderland","awonderland123@gmail.com", -10.0),
                Friend(UUID.randomUUID(),"Alice", "Borderland", "borderlands@yahoo.com", 0.0)
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