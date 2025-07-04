package com.markoen.tecnisisapp.ui.views.gerente.reporte.ReporteArtistas

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
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

@Composable
fun PantallaReporteArtistas(
    id: Int,
    id_perfil: Int,
    reporteArtistasViewModel: ReporteArtistasViewModel = hiltViewModel()
) {

    val reportesUIState by reporteArtistasViewModel.uiState.collectAsState()
    reporteArtistasViewModel.asignarIds(id, id_perfil)

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
            IconButton(onClick = {}) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("Reportes Ventas totales", style = MaterialTheme.typography.titleMedium)
        }

        Spacer(modifier = Modifier.height(24.dp))

        val entries = reportesUIState.ArtistasLista.map { artista -> RadarEntry(artista.precio.toFloat()) }
        val labels = reportesUIState.ArtistasLista.map { it.nombre }

        Card(
            modifier = Modifier.fillMaxWidth().padding(24.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            RadarChartCompose(
                dataPoints = entries,
                labels = labels,
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
fun RadarChartCompose(
    dataPoints: List<RadarEntry>,
    labels: List<String>,
    modifier: Modifier = Modifier
) {
    AndroidView(
        factory = { context ->
            RadarChart(context).apply {
                description.isEnabled = false
                animateXY(1400, 1400, Easing.EaseInOutQuad)

                val dataSet = RadarDataSet(dataPoints, "Promedio de precio de venta de obras").apply {
                    color = Color.rgb(0, 191, 255)
                    fillColor = Color.rgb(0, 191, 255)
                    setDrawFilled(true)
                    lineWidth = 2f
                    valueTextColor = Color.BLACK
                    valueTextSize = 12f
                }

                data = RadarData(dataSet)

                xAxis.apply {
                    textColor = Color.BLACK
                    textSize = 12f
                    valueFormatter = IndexAxisValueFormatter(labels)
                }

                yAxis.apply {
                    textColor = Color.GRAY
                    axisMinimum = 0f
                    axisMaximum = (dataPoints.maxOfOrNull { it.value } ?: 100f) * 1.2f
                }

                legend.apply {
                    textColor = Color.BLACK
                    textSize = 12f
                }
            }
        },
        update = { chart ->
            val dataSet = RadarDataSet(dataPoints, "Promedio de precio de venta de obras").apply {
                color = Color.rgb(0, 191, 255)
                fillColor = Color.rgb(0, 191, 255)
                setDrawFilled(true)
                lineWidth = 2f
                valueTextColor = Color.BLACK
                valueTextSize = 12f
            }

            chart.data = RadarData(dataSet)
            chart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
            chart.yAxis.axisMinimum = 0f
            chart.yAxis.axisMaximum = (dataPoints.maxOfOrNull { it.value } ?: 100f) * 1.2f
            chart.notifyDataSetChanged()
            chart.invalidate()
        },
        modifier = modifier
    )
}