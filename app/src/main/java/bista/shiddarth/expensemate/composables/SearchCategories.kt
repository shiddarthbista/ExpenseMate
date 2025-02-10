package bista.shiddarth.expensemate.composables

import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
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

    val categories = listOf(
        CategorySection(
            "Entertainment",
            listOf(
                Category("Movie", R.drawable.ic_money, Color.Red),
                Category("Music", R.drawable.ic_user, Color.Red),
                Category("Movie", R.drawable.ic_money, Color.Red),
                Category("Music", R.drawable.ic_user, Color.Red),
            )
        ),
        CategorySection(
            "Food and Drink",
            listOf(
                Category("Food", R.drawable.ic_money, Color.Red),
                Category("Drink", R.drawable.ic_user, Color.Red),
            )
        ),
        CategorySection(
            "Hom3e",
            listOf(
                Category("Chair", R.drawable.ic_account, Color.Yellow),
                Category("Table", R.drawable.ic_activity, Color.Green),
            )
        ),
        CategorySection(
            "Home",
            listOf(
                Category("Chair", R.drawable.ic_account, Color.Yellow),
                Category("Table", R.drawable.ic_activity, Color.Green),
                Category("Chair", R.drawable.ic_account, Color.Yellow),
                Category("Table", R.drawable.ic_activity, Color.Green),
            )
        ),
        CategorySection(
            "Home2",
            listOf(
                Category("Chair", R.drawable.ic_account, Color.Yellow),
                Category("Table", R.drawable.ic_activity, Color.Green),
            )
        )
    )

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
                        colors = TextFieldDefaults.colors(cursorColor = kellyGreen)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                    }) {
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
            modifier = Modifier.padding(start = 16.dp),
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
