package bista.shiddarth.expensemate.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import bista.shiddarth.expensemate.R
import bista.shiddarth.expensemate.model.Category
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
                Friend(UUID.randomUUID(),"Gary", "Whittaker", "gwhittaker@gmail.com", mutableListOf()),
                Friend(UUID.randomUUID(),"Tim", "Horton", "timhortins@hotmail.com", mutableListOf(
                    Expense(month = "Feb", date = "20", category = Category("Mini Gold", R.drawable.ic_games, Color(0xFF7ECFDC)),
                        name = "Dinner at The Bistro", price = 25.50))
                ),
                Friend(UUID.randomUUID(),"Alice", "Wonderland","awonderland123@gmail.com", mutableListOf(
                    Expense(month = "Feb", date = "20", category = Category("Netflix", R.drawable.ic_movies, Color(0xFF7ECFDC)), name = "Gladiator 2", price = -25.50),
                    Expense(month = "Feb", date = "27", category =Category("Uber", R.drawable.ic_uber,Color(0xFFFFD2CC)), name = "Netflix", price = -25.50))
                ),
                Friend(UUID.randomUUID(),"Alice", "Borderland", "borderlands@yahoo.com", mutableListOf())
            ),
        )
    }

    fun addNewFriend(friend: Friend) {
        friends.add(friend)
    }

    fun removeFriend(friend: Friend) {
        friends.remove(friend)
    }

    fun findFriend(friendId: String): Friend{
        return friends.first { it.id.toString() == friendId }
    }

    fun addExpense(friendId: String,expense: Expense){
        val friendToBeUpdated = findFriend(friendId)

        val currentExpense = friendToBeUpdated.expenses
        currentExpense.add(expense)
    }
}