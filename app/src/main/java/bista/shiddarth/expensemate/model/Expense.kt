package bista.shiddarth.expensemate.model

import java.util.UUID

data class Expense(
    val id: UUID = UUID.randomUUID(),
    val month: String,
    val date: String,
    val category: String,
    val categoryImage: Int,
    val name: String,
    val price: Double
)