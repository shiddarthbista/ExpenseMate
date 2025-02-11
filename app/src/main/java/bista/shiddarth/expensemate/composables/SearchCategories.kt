package bista.shiddarth.expensemate.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import bista.shiddarth.expensemate.R
import bista.shiddarth.expensemate.model.Category
import bista.shiddarth.expensemate.model.CategorySection
import bista.shiddarth.expensemate.ui.theme.kellyGreen
import bista.shiddarth.expensemate.ui.theme.surfaceGray
import bista.shiddarth.expensemate.viewModel.ExpenseViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SearchCategories(expenseViewModel: ExpenseViewModel, navController: NavController) {
    var searchQuery by remember { mutableStateOf("")}
    val focusRequester = remember { FocusRequester() }
    val categories = remember { getCategorySections() }

    val filteredCategories = if (searchQuery.isEmpty()){
        categories
    } else {
        categories.map {
                section -> section.copy(categoryList =  section.categoryList.filter { it.name.contains(searchQuery,ignoreCase = true) })
        }.filter { it.categoryList.isNotEmpty() }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TextField(
                        value = searchQuery,
                        placeholder = { Text(text = "Search for a category")},
                        onValueChange = { searchQuery = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .focusRequester(focusRequester),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(cursorColor = kellyGreen, focusedIndicatorColor = kellyGreen)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        modifier = Modifier.background(surfaceGray)
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(10.dp)
                .background(surfaceGray)
        ) {
            filteredCategories.forEach { categorySection ->
                stickyHeader(key = categorySection.title) {
                    CategoryHeader(categorySection.title)
                }
                items(categorySection.categoryList) { category ->
                    CategoryItemRow(category, expenseViewModel, navController = navController)
                }
            }
        }
    }
}

private fun getCategorySections() = listOf(
    CategorySection(
        "Entertainment",
        listOf(
            Category("Games", R.drawable.ic_games, Color(0xFF7ECFDC)),
            Category("Movies", R.drawable.ic_movies, Color(0xFF7ECFDC)),
            Category("Sports", R.drawable.ic_sports, Color(0xFF7ECFDC)),
            Category("Music", R.drawable.ic_music, Color(0xFF7ECFDC)),
        )
    ),
    CategorySection(
        "Food and Drink",
        listOf(
            Category("Dining Out", R.drawable.ic_dining_out, Color(0xFFFFD2CC)),
            Category("Groceries", R.drawable.ic_grocery, Color(0xFFFFD2CC)),
            Category("Liquor", R.drawable.ic_liquor, Color(0xFFFFD2CC))
        )
    ),
    CategorySection(
        "Uncategorized",
        listOf(
            Category("General", R.drawable.ic_general, Color(0xFFF8F0E3))
        )
    ),
    CategorySection(
        "Home",
        listOf(
            Category("Electronics", R.drawable.ic_electronics, Color(0xFFA9ED78)),
            Category("Furniture", R.drawable.ic_furniture, Color(0xFFA9ED78)),
            Category("Household supplies", R.drawable.ic_household_supplies, Color(0xFFA9ED78)),
            Category("Mortgage/Rent", R.drawable.ic_mortgage, Color(0xFFA9ED78)),
            Category("Pets", R.drawable.ic_pets, Color(0xFFA9ED78))
        )
    ),
    CategorySection(
        "Life",
        listOf(
            Category("Clothing", R.drawable.ic_clothes, Color(0xFF8CBAB4)),
            Category("Education", R.drawable.ic_bookshelf, Color(0xFF8CBAB4)),
            Category("Gifts", R.drawable.ic_gifts, Color(0xFF8CBAB4)),
            Category("Medical", R.drawable.ic_medical, Color(0xFF8CBAB4)),
        )
    ),
    CategorySection(
        "Transportation",
        listOf(
            Category("Gas", R.drawable.ic_gas, Color(0xFF8B8BE9)),
            Category("Bus/Train", R.drawable.ic_train, Color(0xFF8B8BE9)),
            Category("Parking", R.drawable.ic_parking, Color(0xFF8B8BE9)),
            Category("Uber", R.drawable.ic_uber, Color(0xFF8B8BE9))
        )
    )
)

@Composable
fun CategoryHeader(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(surfaceGray)
            .padding(vertical = 8.dp)
    ) {
        Text(text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
            )
    }
}

@Composable
fun CategoryItemRow(category: Category, expenseViewModel: ExpenseViewModel,navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                expenseViewModel.updateCategory(category)
                navController.popBackStack()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier
            .size(45.dp)
            .background(category.backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Image(painter = painterResource(id = category.categoryImage), contentDescription = category.name, modifier = Modifier.size(40.dp))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = category.name, fontSize = 16.sp, color = Color.White)
    }
}
