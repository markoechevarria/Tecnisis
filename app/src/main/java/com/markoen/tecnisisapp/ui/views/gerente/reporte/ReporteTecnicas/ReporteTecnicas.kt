package com.markoen.tecnisisapp.ui.views.gerente.reporte.ReporteTecnicas

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import android.graphics.Color
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.markoen.tecnisisapp.R
import com.github.mikephil.charting.data.Entry
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import androidx.compose.ui.graphics.Color as ComposeColor
@Composable
fun PantallaReporteTecnicas(
    id: Int,
    id_perfil: Int,
    reporteTecnicasViewModel: ReporteTecnicasViewModel = hiltViewModel(),
    navegarAtras: () -> Unit
) {

    val reporteTecnicasUiState by reporteTecnicasViewModel.uiState.collectAsState()
    reporteTecnicasViewModel.asignarIds(id, id_perfil)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.image_fx_redondeada) ,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )
                Text(
                    text = "TECNISIS",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .clickable {}
        ) {
            IconButton(onClick = { navegarAtras() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("Reportes Ventas totales", style = MaterialTheme.typography.titleMedium)
        }

        Spacer(modifier = Modifier.height(24.dp))

        val entries = reporteTecnicasUiState.tecnicasLista.map { tecnica ->
            PieEntry(tecnica.numero_obras.toFloat(), tecnica.nombre_tecnica)
        }


        Card(
            modifier = Modifier.fillMaxWidth().padding(24.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            PieChartCompose(
                dataPoints = entries,
                modifier = Modifier.fillMaxWidth().height(500.dp).padding(10.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "© 2025 Todos los derechos reservados",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }
    }
}

@Composable
fun PieChartCompose(
    dataPoints: List<PieEntry>,
    modifier: Modifier = Modifier
) {
    AndroidView(
        factory = { context ->
            PieChart(context).apply {
                description.isEnabled = false
                animateY(1400, Easing.EaseInOutQuad)

                val dataSet = PieDataSet(dataPoints, "Distribución de técnicas").apply {
                    colors = listOf(
                        Color.rgb(255, 99, 132),
                        Color.rgb(54, 162, 235),
                        Color.rgb(255, 206, 86),
                        Color.rgb(75, 192, 192)
                    )
                    valueTextColor = Color.WHITE
                    valueTextSize = 14f
                    sliceSpace = 3f
                    selectionShift = 5f
                }

                data = PieData(dataSet)

                legend.apply {
                    textColor = Color.BLACK
                    textSize = 12f
                    orientation = Legend.LegendOrientation.HORIZONTAL
                    verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
                    horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                    setDrawInside(false)
                    isWordWrapEnabled = true
                    legend.yEntrySpace = 10f
                }
            }
        },
        update = { chart ->
            val dataSet = PieDataSet(dataPoints, "Distribución de técnicas").apply {
                colors = listOf(
                    Color.rgb(255, 99, 132),
                    Color.rgb(54, 162, 235),
                    Color.rgb(255, 206, 86),
                    Color.rgb(75, 192, 192),
                    Color.rgb(153, 102, 255)
                )
                valueTextColor = Color.WHITE
                valueTextSize = 14f
                sliceSpace = 3f
                selectionShift = 5f
            }

            chart.data = PieData(dataSet)
            chart.notifyDataSetChanged()
            chart.invalidate()
        },
        modifier = modifier
    )
}
