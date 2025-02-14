package bista.shiddarth.expensemate.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import bista.shiddarth.expensemate.ui.theme.kellyGreen
import bista.shiddarth.expensemate.viewModel.FriendViewModel
import com.himanshoe.charty.bar.BarChart
import com.himanshoe.charty.bar.config.BarChartConfig
import com.himanshoe.charty.bar.model.BarData
import com.himanshoe.charty.common.LabelConfig
import com.himanshoe.charty.common.asSolidChartColor

@Composable
fun BarGraphComposable(friendViewModel: FriendViewModel) {
    val barData = friendViewModel.getExpenseByMonth()

    BarChart(
        data = {
            generateBarChartData(barData)
        },
        barChartConfig = BarChartConfig(
            showAxisLines = true,
            showGridLines = true,
            drawNegativeValueChart = true,
            showCurvedBar = true,
            minimumBarCount = 1
        ),

        labelConfig = LabelConfig(
            Color.White.asSolidChartColor(), showXLabel = true,showYLabel = true
        ),
    )
}

fun generateBarChartData(monthlyTotals: Map<String, Double>): List<BarData> {
    val allMonths = listOf(
        "Jan", "Feb", "Mar", "Apr", "May", "Jun",
        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    )
    return allMonths.map { month ->
        val categoryTotal = monthlyTotals[month] ?: 0.0

        BarData(
            yValue = categoryTotal.toFloat(),
            xValue = month,
            barColor = if (categoryTotal > 0 ) Color.Red.asSolidChartColor() else kellyGreen.asSolidChartColor(),
            barBackgroundColor = Color.Transparent.asSolidChartColor()
        )
    }
}
