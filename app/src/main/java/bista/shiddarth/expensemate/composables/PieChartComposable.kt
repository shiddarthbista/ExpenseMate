package bista.shiddarth.expensemate.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import bista.shiddarth.expensemate.viewModel.FriendViewModel
import com.himanshoe.charty.common.asGradientChartColor
import com.himanshoe.charty.pie.PieChart
import com.himanshoe.charty.pie.model.PieChartData
import kotlin.random.Random

@Composable
fun PieChartComposable(friendViewModel: FriendViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        PieChart(
            data = { generatePieChartData(friendViewModel.getExpenseByCategory()) },
            modifier = Modifier
                .size(300.dp)
                .padding(4.dp)
        )
    }
}

fun generatePieChartData(categoryTotals: Map<String, Double>): List<PieChartData> {
    val totalExpenses = categoryTotals.values.sum().toFloat()
    val gradientColors = generateGradientColors(categoryTotals.size)

    return categoryTotals.entries.mapIndexed { index, entry ->
        val categoryName = entry.key
        val categoryTotal = entry.value
        val percentage = (categoryTotal / totalExpenses) * 100f

        PieChartData(
            percentage.toFloat(),
            gradientColors[index].asGradientChartColor(),
            label = "$categoryName ${percentage.toInt()}%"
        )
    }
}

fun generateGradientColors(count: Int): List<List<Color>> {
    val colors = mutableListOf<List<Color>>()
    val random = Random(System.currentTimeMillis())

    val popularColors = listOf(
        Color(0xFFE63946),
        Color(0xFFF1FAEE),
        Color(0xFF000814),
        Color(0xFF457B9D),
        Color(0xFF1D3557),
        Color(0xFF2A9D8F),
        Color(0xFFE9C46A),
        Color(0xFFF4A261),
        Color(0xFFE76F51),
        Color(0xFF283618),
        Color(0xFFbc6c25),
        Color(0xFF606c38),
        Color(0xFFd4a373),
        Color(0xFF9d4edd),
        Color(0xFF0077b6),
        Color(0xFF00b4d8),
        Color(0xFF283618),
        Color(0xFF231942),
        Color(0xFFfb6f92),
    )

    val availableColors = popularColors.shuffled(random)
    val numColorsToUse = minOf(count * 2, availableColors.size)

    for (i in 0 until count) {
        val color1 = availableColors[i % numColorsToUse]
        val color2 = availableColors[(i + 1) % numColorsToUse]
        colors.add(listOf(color1, color2))
    }

    return colors
}