package bista.shiddarth.expensemate.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import bista.shiddarth.expensemate.R
import bista.shiddarth.expensemate.model.Category
import bista.shiddarth.expensemate.model.Expense
import bista.shiddarth.expensemate.model.Friend
import java.util.UUID
import kotlin.math.abs

class FriendViewModel : ViewModel() {

    val friends = mutableStateListOf<Friend>()

    init {
        loadInitialFriends()
    }

    private fun loadInitialFriends() {
        friends.addAll(
            listOf(
                Friend(
                    UUID.randomUUID(),
                    "Gary",
                    "Whittaker",
                    "gwhittaker@gmail.com",
                    mutableListOf()
                ),
                Friend(
                    UUID.randomUUID(), "Tim", "Horton", "timhortins@hotmail.com", mutableListOf(
                        Expense(
                            month = "Apr",
                            date = "20",
                            category = Category(
                                "Mortgage",
                                R.drawable.ic_mortgage,
                                Color(0xFF7ECFDC)
                            ),
                            name = "Dinner at The Bistro",
                            price = 250.50
                        )
                    )
                ),
                Friend(
                    UUID.randomUUID(),
                    "Alice",
                    "Wonderland",
                    "awonderland123@gmail.com",
                    mutableListOf(
                        Expense(
                            month = "Feb",
                            date = "20",
                            category = Category("Netflix", R.drawable.ic_movies, Color(0xFF7ECFDC)),
                            name = "Gladiator 2",
                            price = -25.50
                        ),
                        Expense(
                            month = "Feb",
                            date = "27",
                            category = Category("Uber", R.drawable.ic_uber, Color(0xFFFFD2CC)),
                            name = "Uber",
                            price = -25.50
                        ),
                        Expense(
                            month = "Oct",
                            date = "13",
                            category = Category("Dog food", R.drawable.ic_pets, Color(0xFFFFD2CC)),
                            name = "Dog Bones",
                            price = -225.50
                        )
                    )
                ),
                Friend(
                    UUID.randomUUID(),
                    "Alice",
                    "Borderland",
                    "borderlands@yahoo.com",
                    mutableListOf()
                ),
                Friend(
                    UUID.randomUUID(), "Venkata ", "Satish", "vsatish@hotmail.com", mutableListOf(
                        Expense(
                            month = "Jan",
                            date = "10",
                            category = Category(
                                "Grocery",
                                R.drawable.ic_grocery,
                                Color(0xFF7ECFDC)
                            ),
                            name = "Costco",
                            price = 200.00
                        )
                    )
                ),
                Friend(
                    UUID.randomUUID(), "Zhenjun ", "cui", "zcuih@hotmail.com", mutableListOf(
                        Expense(
                            month = "Mar",
                            date = "21",
                            category = Category(
                                "Electronics",
                                R.drawable.ic_electronics,
                                Color(0xFF7ECFDC)
                            ),
                            name = "TV",
                            price = 399.00
                        )
                    )
                )

            ),
        )
    }

    fun addNewFriend(friend: Friend) {
        friends.add(friend)
    }

    fun removeFriend(friend: Friend) {
        friends.remove(friend)
    }

    fun findFriend(friendId: String): Friend {
        return friends.first { it.id.toString() == friendId }
    }

    fun addExpense(friendId: String, expense: Expense) {
        val friendToBeUpdated = findFriend(friendId)

        val currentExpense = friendToBeUpdated.expenses
        currentExpense.add(expense)
    }

    fun getExpenseByCategory(): Map<String, Double> {
        //For now doing it like this so that even negative prices will show up as positive
        // as total spend in the piechart.
        return friends.flatMap { it.expenses }
            .groupBy { it.category.name }
            .mapValues { entry -> entry.value.sumOf { abs(it.price) } }
    }

    fun getExpenseByMonth(): Map<String, Double> {
        return friends.flatMap { it.expenses }
            .groupBy { it.month }
            .mapValues { entry -> entry.value.sumOf { it.price } }
    }
}