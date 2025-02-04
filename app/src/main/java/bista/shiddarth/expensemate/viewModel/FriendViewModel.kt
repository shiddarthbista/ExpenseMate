package bista.shiddarth.expensemate.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import bista.shiddarth.expensemate.R
import bista.shiddarth.expensemate.model.Expense
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
                Friend(UUID.randomUUID(),"Gary", "Whittaker", "gwhittaker@gmail.com", emptyList()),
                Friend(UUID.randomUUID(),"Tim", "Horton", "timhortins@hotmail.com", listOf(
                    Expense(month = "Feb", date = "20", category = "Food", categoryImage = R.drawable.flowers, name = "Dinner at The Bistro", price = 25.50)
                )),
                Friend(UUID.randomUUID(),"Alice", "Wonderland","awonderland123@gmail.com", listOf(
                    Expense(month = "Feb", date = "20", category = "Food", categoryImage = R.drawable.flowers, name = "Dinner at The Bistro", price = -25.50),
                    Expense(month = "Feb", date = "27", category = "Netflix", categoryImage = R.drawable.background3, name = "Netflix", price = -25.50)
                )),
                Friend(UUID.randomUUID(),"Alice", "Borderland", "borderlands@yahoo.com", emptyList())
            ),
        )
    }

    fun addFriend(friend: Friend) {
        friends.add(friend)
    }

    fun removeFriend(friend: Friend) {
        friends.remove(friend)
    }

    fun findFriend(friendId: String): Friend{
        return friends.first { it.id.toString() == friendId }
    }
}