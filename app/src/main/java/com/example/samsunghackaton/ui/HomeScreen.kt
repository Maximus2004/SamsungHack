package com.example.samsunghackaton.ui

import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.samsunghackaton.R
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollSpec
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.component.textComponent
import com.patrykandpatrick.vico.compose.style.currentChartStyle
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.axis.vertical.VerticalAxis
import com.patrykandpatrick.vico.core.chart.decoration.ThresholdLine
import com.patrykandpatrick.vico.core.chart.layout.HorizontalLayout
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.context.DrawContext
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.core.marker.Marker
import com.patrykandpatrick.vico.core.marker.MarkerVisibilityChangeListener
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.launch
import java.time.LocalDate
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var score by remember { mutableIntStateOf(2) }
    var address by remember { mutableStateOf("г. Москва, ул. Варварка") }
    var producer by remember { mutableStateOf(ChartEntryModelProducer(getRandomEntries(0))) }
    val sheetState = rememberStandardBottomSheetState(initialValue = SheetValue.PartiallyExpanded)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val placemarkTapListener = MapObjectTapListener { _, point ->
        producer = ChartEntryModelProducer(getRandomEntries(0))
        address = getStreet()
        score = getRandomScore()
        coroutineScope.launch { sheetState.expand() }
        true
    }
    val scanLauncher = rememberLauncherForActivityResult(ScanContract()) { result ->
        coroutineScope.launch {
            if (result.contents != null) {
                Toast.makeText(context, "QR-код успешно отсканирован", Toast.LENGTH_SHORT).show()
                coroutineScope.launch { sheetState.expand() }
            } else {
                Toast.makeText(context, "Не удалось отсканировать QR-код", Toast.LENGTH_SHORT).show()
            }
        }
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(510.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(bottom = 25.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFE1EDFF)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = address,
                            modifier = Modifier.padding(vertical = 11.dp, horizontal = 30.dp),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(bottom = 17.dp)
                            .padding(horizontal = 10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFE1EDFF))
                                .weight(0.56f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Статистика",
                                modifier = Modifier.padding(vertical = 11.dp, horizontal = 10.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Spacer(Modifier.width(10.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFE1EDFF))
                                .weight(0.44f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Загрязнение",
                                modifier = Modifier.padding(vertical = 11.dp, horizontal = 10.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .padding(bottom = 17.dp)
                            .padding(horizontal = 10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFF4F4F4))
                                .weight(0.56f)
                                .border(
                                    width = 2.dp,
                                    shape = RoundedCornerShape(12.dp),
                                    color = Color(0xFFB4B3B3)
                                ),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Column(
                                modifier = Modifier.padding(13.dp),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 6.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "PM 2.5:",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Text(text = "9.2 µg", style = MaterialTheme.typography.bodySmall)
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 6.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "PM 10:",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Text(text = "5.3 µg", style = MaterialTheme.typography.bodySmall)
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 9.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Давление:",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Text(text = "155 Па", style = MaterialTheme.typography.bodySmall)
                                }
                                Text(
                                    "28.04.2024",
                                    style = MaterialTheme.typography.titleSmall,
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                        .align(Alignment.CenterHorizontally)
                                )
                            }
                        }
                        Spacer(Modifier.width(10.dp))
                        BatteryWidget(modifier = Modifier.weight(0.44f), score = score)
                    }
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(vertical = 15.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFE1EDFF)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Диаграмма загрязнения",
                            modifier = Modifier.padding(vertical = 13.dp, horizontal = 30.dp),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    Text(
                        text = "5 очков, 28 апреля 2024",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                    Text(
                        text = "22 апреля - 28 апреля, 2024",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(start = 10.dp, bottom = 13.dp)
                    )

                    ShowColumnChart(
                        modelProducerColumn = producer,
                        onDataChanged = { _, _ -> },
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
            }
        },
        sheetPeekHeight = 120.dp,
        sheetContainerColor = Color.White
    ) {
        Box() {
            AndroidView(
                factory = { context ->
                    val view = LayoutInflater.from(context).inflate(R.layout.map_activity, null, false)

                    val mapView = view.findViewById<MapView>(R.id.mapview)

                    val map = mapView.mapWindow.map
                    map.move(CameraPosition(Point(55.751280, 37.629720), 17.0f, 150.0f, 30.0f))

                    val imageProvider = ImageProvider.fromResource(context, R.drawable.point)
                    val arrayPoints = listOf(
                        Point(55.751280, 37.629720),
                        Point(55.805584, 37.645483),
                        Point(55.755621, 37.629736),
                        Point(55.753625, 37.625882),
                        Point(55.741206, 37.614267)
                    )
                    val placemarkObject1 = map.mapObjects.addPlacemark().apply {
                        geometry = arrayPoints[0]
                        setIcon(imageProvider)
                    }
                    placemarkObject1.addTapListener(placemarkTapListener)

                    val placemarkObject2 = map.mapObjects.addPlacemark().apply {
                        geometry = arrayPoints[1]
                        setIcon(imageProvider)
                    }
                    placemarkObject2.addTapListener(placemarkTapListener)

                    val placemarkObject3 = map.mapObjects.addPlacemark().apply {
                        geometry = arrayPoints[2]
                        setIcon(imageProvider)
                    }
                    placemarkObject3.addTapListener(placemarkTapListener)

                    val placemarkObject4 = map.mapObjects.addPlacemark().apply {
                        geometry = arrayPoints[3]
                        setIcon(imageProvider)
                    }
                    placemarkObject4.addTapListener(placemarkTapListener)

                    val placemarkObject5 = map.mapObjects.addPlacemark().apply {
                        geometry = arrayPoints[4]
                        setIcon(imageProvider)
                    }
                    placemarkObject5.addTapListener(placemarkTapListener)
                    view
                },
                update = { view ->

                }
            )
            Box(
                modifier = Modifier
                    .padding(top = 9.dp)
                    .align(Alignment.TopEnd)
                    .width(48.dp)
                    .height(54.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 12.dp,
                            bottomStart = 12.dp,
                            bottomEnd = 0.dp,
                            topEnd = 0.dp
                        )
                    )
                    .background(color = Color(0xFF6BA6FF))
                    .clickable { scanLauncher.launch(ScanOptions().setPrompt("")) },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.qr_code),
                    tint = Color.White,
                    modifier = Modifier.align(Alignment.Center),
                    contentDescription = null
                )
            }
            Box(
                modifier = Modifier
                    .padding(top = 9.dp)
                    .align(Alignment.TopStart)
                    .width(48.dp)
                    .height(54.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomEnd = 12.dp,
                            topEnd = 12.dp,
                            topStart = 0.dp,
                            bottomStart = 0.dp
                        )
                    )
                    .background(color = Color(0xFF6BA6FF))
                    .clickable { }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.profile),
                    tint = Color.White,
                    modifier = Modifier.align(Alignment.Center),
                    contentDescription = null
                )
            }
            Box(
                modifier = Modifier
                    .padding(bottom = 130.dp)
                    .align(Alignment.BottomEnd)
                    .width(48.dp)
                    .height(54.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 12.dp,
                            bottomStart = 12.dp,
                            bottomEnd = 0.dp,
                            topEnd = 0.dp
                        )
                    )
                    .background(color = Color(0xFF6BA6FF))
                    .clickable { }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.near_me),
                    tint = Color.White,
                    modifier = Modifier.align(Alignment.Center),
                    contentDescription = null
                )
            }
        }
    }
}

fun getRandomEntries(id: Int): List<FloatEntry> {
    return when(Random.nextInt() % 3) {
        0 -> listOf(FloatEntry(1f, 2f), FloatEntry(2f, 1f), FloatEntry(3f, 3f), FloatEntry(4f, 4f), FloatEntry(5f, 5f), FloatEntry(6f, 5f), FloatEntry(7f, 5f))
        1 -> listOf(FloatEntry(1f, 4f), FloatEntry(2f, 1f), FloatEntry(3f, 5f), FloatEntry(4f, 3f), FloatEntry(5f, 3f), FloatEntry(6f, 1f), FloatEntry(7f, 2f))
        2 -> listOf(FloatEntry(1f, 3f), FloatEntry(2f, 2f), FloatEntry(3f, 2f), FloatEntry(4f, 2f), FloatEntry(5f, 2f), FloatEntry(6f, 2f), FloatEntry(7f, 2f))
        else -> listOf(FloatEntry(1f, 5f), FloatEntry(2f, 2f), FloatEntry(3f, 3f), FloatEntry(4f, 1f), FloatEntry(5f, 5f), FloatEntry(6f, 5f), FloatEntry(7f, 4f))
    }
}

fun getRandomScore(): Int {
    var id = Random.nextInt() % 4 + 1
    while (id < 1) {
        id = Random.nextInt() % 4 + 1
    }
    return id
}

fun getStreet(): String {
    var id = Random.nextInt() % 4 + 1
    while (id < 1) {
        id = Random.nextInt() % 4 + 1
    }
    return when(id % 4) {
        0 -> "г. Москва, ул. Ильинка"
        1 -> "г. Москва, ул. Арбат"
        2 -> "г. Москва, пер. Рыбный"
        3 -> "г. Москва, ул. Варварка"
        else -> "г. Москва, ул. Ильинка"
    }
}
@Composable
fun ShowColumnChart(
    modifier: Modifier = Modifier,
    modelProducerColumn: ChartEntryModelProducer,
    onDataChanged: (Int, Int) -> Unit
) {
    val defaultColumns = currentChartStyle.columnChart.columns
    val thresholdLineNullLevel = rememberNullLevel()
    Chart(
        modifier = modifier.padding(bottom = 30.dp),
        chart = columnChart(
            columns = remember(defaultColumns) {
                defaultColumns.map { _ ->
                    object : LineComponent(
                        color = Color(0xFF6BA6FF).toArgb(),
                        thicknessDp = 16f,
                        shape = Shapes.roundedCornerShape(
                            topLeftPercent = 40,
                            topRightPercent = 40,
                            bottomLeftPercent = 0,
                            bottomRightPercent = 0
                        )
                    ) {
                        override fun drawVertical(
                            context: DrawContext,
                            top: Float,
                            bottom: Float,
                            centerX: Float,
                            thicknessScale: Float,
                            opacity: Float,
                        ) {
                            super.drawVertical(
                                context,
                                top,
                                bottom,
                                centerX,
                                1f,
                                opacity
                            )
                        }
                    }
                }
            },
            decorations = remember(thresholdLineNullLevel) {
                listOf(thresholdLineNullLevel)
            },
        ),
        chartModelProducer = modelProducerColumn,
        isZoomEnabled = false,
        chartScrollSpec = rememberChartScrollSpec(isScrollEnabled = false),
        startAxis = rememberStartAxis(
            label = textComponent(
                color = Color.Transparent,
                textSize = 12.sp
            ),
            valueFormatter = { value, _ -> value.toInt().toString() },
            tickLength = 0.dp,
            tick = LineComponent(color = Color.Transparent.toArgb()),
            axis = LineComponent(color = Color.Transparent.toArgb()),
            verticalLabelPosition = VerticalAxis.VerticalLabelPosition.Top,
            horizontalLabelPosition = VerticalAxis.HorizontalLabelPosition.Inside,
            itemPlacer = AxisItemPlacer.Vertical.default(maxItemCount = 6),
            guideline = LineComponent(
                strokeColor = Color(0xFF6BA6FF).toArgb(),
                color = Color(0xFF6BA6FF).toArgb(),
                thicknessDp = 1f
            ),
        ),
        marker = rememberColumnMarker(),
        markerVisibilityChangeListener = object : MarkerVisibilityChangeListener {
            // update data when on marker moved
            override fun onMarkerMoved(
                marker: Marker,
                markerEntryModels: List<Marker.EntryModel>,
            ) {
                super.onMarkerMoved(marker, markerEntryModels)
                onDataChanged(
                    markerEntryModels.first().entry.x.toInt(),
                    markerEntryModels.first().entry.y.toInt()
                )
            }

            // update data on marker shown
            override fun onMarkerShown(
                marker: Marker,
                markerEntryModels: List<Marker.EntryModel>
            ) {
                super.onMarkerShown(marker, markerEntryModels)
                onDataChanged(
                    markerEntryModels.first().entry.x.toInt(),
                    markerEntryModels.first().entry.y.toInt()
                )
            }
        }
    )
}

@Composable
private fun rememberNullLevel(): ThresholdLine {
    val line = shapeComponent(color = Color.Transparent, strokeColor = Color.Transparent)
    val label = textComponent(
        color = Color.Transparent,
        textSize = 12.sp
    )
    return remember(line, label) {
        ThresholdLine(
            thresholdValue = 0f,
            lineComponent = line,
            labelComponent = label,
            labelHorizontalPosition = ThresholdLine.LabelHorizontalPosition.Start,
            labelVerticalPosition = ThresholdLine.LabelVerticalPosition.Bottom
        )
    }
}

@Composable
fun BatteryWidget(score: Int, modifier: Modifier = Modifier) {
    val arrayColors = listOf(
        Color(0xFF00BB9A),
        Color(0xFF9ED209),
        Color(0xFFF3BE00),
        Color(0xFFFF9921),
        Color(0xFFFC3B69)
    )
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(65.dp)
                .background(arrayColors[score - 1]),
            contentAlignment = Alignment.Center
        ) {
            Text(text = score.toString(), style = MaterialTheme.typography.titleLarge)
        }
        LazyRow(modifier = Modifier.padding(top = 4.dp)) {
            itemsIndexed(arrayColors) { index, color ->
                Box(
                    modifier = Modifier
                        .size(height = 64.dp, width = 18.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(color = if (index < score) color else Color(0xFFD9D9D9))
                )
                if (index < arrayColors.size - 1) {
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
        }
    }
}