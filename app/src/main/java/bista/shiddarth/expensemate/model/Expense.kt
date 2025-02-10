package bista.shiddarth.expensemate.model

import androidx.compose.ui.graphics.Color
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

data class Category(
    val name: String,
    val categoryImage: Int,
    val backgroundColor: Color
)

data class CategorySection(
    val title: String,
    val categoryList: List<Category>
)