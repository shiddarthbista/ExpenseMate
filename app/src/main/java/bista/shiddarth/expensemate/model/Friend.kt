package bista.shiddarth.expensemate.model

import bista.shiddarth.expensemate.R
import java.util.UUID

data class Friend(
    val id: UUID = UUID.randomUUID(),
    val firstName: String,
    val lastName: String,
    val email: String,
    val expenses: MutableList<Expense> = mutableListOf()
)
