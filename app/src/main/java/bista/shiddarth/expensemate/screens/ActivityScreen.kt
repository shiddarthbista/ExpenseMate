package bista.shiddarth.expensemate.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import bista.shiddarth.expensemate.composables.PieChartComposable
import bista.shiddarth.expensemate.ui.theme.kellyGreen
import bista.shiddarth.expensemate.viewModel.FriendViewModel
import com.himanshoe.charty.bar.BarChart
import com.himanshoe.charty.bar.config.BarChartConfig
import com.himanshoe.charty.bar.model.BarData
import com.himanshoe.charty.common.LabelConfig
import com.himanshoe.charty.common.asSolidChartColor

@Composable
fun ActivityScreen(friendViewModel: FriendViewModel) {

    var selectedChart by remember { mutableStateOf(ChartType.CATEGORY) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(250.dp)
                .height(40.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.LightGray)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(125.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(kellyGreen)
                    .align(if (selectedChart == ChartType.CATEGORY) Alignment.CenterStart else Alignment.CenterEnd)
                    .animateContentSize()
            )

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "By Category",
                    color = if (selectedChart == ChartType.CATEGORY) Color.White else Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { selectedChart = ChartType.CATEGORY }
                )
                Text(
                    text = "By Month",
                    color = if (selectedChart == ChartType.MONTH) Color.White else Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { selectedChart = ChartType.MONTH }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (selectedChart) {
            ChartType.CATEGORY -> PieChartComposable(friendViewModel)
            ChartType.MONTH -> BarGraphComposable(friendViewModel)
        }
    }
}

enum class ChartType { CATEGORY, MONTH }

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
