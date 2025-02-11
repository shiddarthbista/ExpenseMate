package bista.shiddarth.expensemate.model

import java.util.UUID

data class Friend(
    val id: UUID = UUID.randomUUID(),
    val firstName: String,
    val lastName: String,
    val expenses: MutableList<Expense> = mutableListOf()
)
