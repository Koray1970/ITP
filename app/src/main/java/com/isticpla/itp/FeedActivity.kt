package com.isticpla.itp

import android.graphics.BlurMaskFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ChipColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.isticpla.itp.dummydata.BusinessTypeItem
import com.isticpla.itp.dummydata.FeedDashboardItems
import com.isticpla.itp.feed.*
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.home.homeSubSectionTitle
import com.isticpla.itp.ui.theme.ITPTheme
import com.isticpla.itp.uimodules.AppColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import java.util.Locale

class FeedActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ITPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigate()
                }
            }
        }
    }
}

private val TAG = "FeedActivity"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedDashboard(
    navController: NavController,
    homeViewMode: HomeViewMode = hiltViewModel(),
) {
    val context = LocalContext.current.applicationContext
    val gson = Gson()
    var sectorlist = remember { mutableListOf<BusinessTypeItem>() }
    var sectorListState =
        homeViewMode.sectorList.collectAsState(initial = mutableListOf<BusinessTypeItem>())
    LaunchedEffect(Unit) {
        homeViewMode.sectorList.collect { bt ->
            sectorlist = bt
            Log.v(TAG, "${gson.toJson(sectorListState.value)}")
        }
    }


    val listofdashboarditem =
        homeViewMode.feedDashboardItems.collectAsState(initial = emptyList<FeedDashboardItems>())
    Scaffold(
        containerColor = Color.White,
        topBar = {
            MediumTopAppBar(
                colors = TopAppBarColors(
                    containerColor = Color.White, scrolledContainerColor = Color.White,
                    navigationIconContentColor = AppColors.primaryGrey,
                    titleContentColor = AppColors.primaryGrey,
                    actionIconContentColor = AppColors.primaryGrey,
                ),
                title = { Text("Sektörler", style = homeSubSectionTitle) },
                actions = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_home_24),
                            contentDescription = null
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_left),
                            contentDescription = null
                        )
                    }
                }
            )
        }) { innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .padding(horizontal = 10.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .horizontalScroll(rememberScrollState())
            ) {
                sectorListState.value.forEach { b ->
                    var toggleButtonState by remember { mutableStateOf(b.isSelected) }
                    Card(
                        onClick = {
                            homeViewMode.UpdateSectorListSelection(b.id)
                            /*sectorlist.forEach { h ->
                                h.isSelected = h.id != b.id
                            }*/
                            Log.v(TAG, "${gson.toJson(sectorListState.value)}")
                            toggleButtonState = !toggleButtonState
                        },
                        enabled = b.isSelected,
                        shape = RoundedCornerShape(5.dp),
                        colors = CardColors(
                            containerColor = AppColors.grey_133,
                            contentColor = Color.Black,
                            disabledContainerColor = AppColors.primaryGrey,
                            disabledContentColor = Color.White
                        ),
                        modifier = Modifier
                            .size(100.dp, 44.dp)
                            .padding(0.dp),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = b.icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(28.dp)
                                    .padding(0.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = b.label.uppercase(Locale.ROOT),
                                style = TextStyle(
                                    fontFamily = poppinFamily,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            FeedSeachBar()
            Spacer(modifier = Modifier.height(20.dp))
            listofdashboarditem.value.forEach { b ->
                when (b.type) {
                    1 -> {
                        FeedDashboardItemLarge(fdi = b, navController)
                    }

                    2 -> {
                        FeedDashboardItemMedium(fdi = b, navController)
                    }

                    else -> {
                        FeedDashboardItemNoImage(fdi = b, navController)
                    }
                }
                Spacer(modifier = Modifier.height(42.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedProductDetail(
    navController: NavController,
    homeViewMode: HomeViewMode = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current.applicationContext
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    val contentDetailsState =
        homeViewMode.feedDashboardItems.collectAsState(initial = emptyList<FeedDashboardItems>())
    LaunchedEffect(Unit) {
        delay(200)
        val sValue = contentDetailsState.value.first()
        title = sValue.title
        if (!sValue.content.isNullOrEmpty())
            content = sValue.content
        date = sValue.date
    }
    var roundShape =
        RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp, bottomStart = 0.dp, bottomEnd = 0.dp)

    val color: Color = AppColors.grey_dark.copy(.2f)
    val borderRadius: Dp = 14.dp
    val blurRadius: Dp = 8.dp
    val offsetY: Dp = 0.dp
    val offsetX: Dp = 0.dp
    val spread: Dp = 7f.dp

    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(id = R.mipmap.fm02),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = Color.Transparent, scrolledContainerColor = Color.Transparent,
                    navigationIconContentColor = AppColors.primaryGrey,
                    titleContentColor = AppColors.primaryGrey,
                    actionIconContentColor = AppColors.primaryGrey,
                ),
                title = { Text("", style = homeSubSectionTitle) },
                navigationIcon = {
                    IconButton(
                        colors = IconButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = AppColors.primaryGrey,
                            disabledContainerColor = Color.Transparent,
                            disabledContentColor = AppColors.primaryGrey
                        ),
                        modifier = Modifier
                            .background(Color.White.copy(.6f), RoundedCornerShape(8.dp)),
                        onClick = { navController.navigate("feed") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_left),
                            contentDescription = null
                        )
                    }
                }
            )
        }) { innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerpadding)
                .padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(.3f))
            Card(
                colors = CardColors(
                    containerColor = Color.White,
                    contentColor = AppColors.grey_dark,
                    disabledContainerColor = Color.White,
                    disabledContentColor = AppColors.grey_dark
                ),
                shape = roundShape,
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .drawBehind {
                        this.drawIntoCanvas {
                            val paint = Paint()
                            val frameworkPaint = paint.asFrameworkPaint()
                            val spreadPixel = spread.toPx()
                            val leftPixel = (0f - spreadPixel) + offsetX.toPx()
                            val topPixel = (0f - spreadPixel) + offsetY.toPx()
                            val rightPixel = (this.size.width + spreadPixel)
                            val bottomPixel =
                                (this.size.height - 26f)//(this.size.height + spreadPixel)

                            if (blurRadius != 0.dp) {
                                frameworkPaint.maskFilter =
                                    (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
                            }

                            frameworkPaint.color = color.toArgb()
                            it.drawRoundRect(
                                left = leftPixel,
                                top = topPixel,
                                right = rightPixel,
                                bottom = bottomPixel,
                                radiusX = borderRadius.toPx(),
                                radiusY = borderRadius.toPx(),
                                paint
                            )
                        }
                    }
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = title, style = feedDetailTitle, modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = content,
                        style = feedDetailContent,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = date, style = feedDetailDate, modifier = Modifier.fillMaxWidth())
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            FeedDetailButton(navController)
            Spacer(modifier = Modifier.height(30.dp))
            ListItem(
                colors = ListItemColors(
                    containerColor = Color.Transparent,
                    headlineColor = AppColors.blue_100,
                    leadingIconColor = Color.Transparent,
                    overlineColor = Color.Transparent,
                    supportingTextColor = Color.Transparent,
                    trailingIconColor = AppColors.grey_dark,
                    disabledHeadlineColor = Color.Transparent,
                    disabledLeadingIconColor = Color.Transparent,
                    disabledTrailingIconColor = Color.Transparent
                ),
                modifier = Modifier
                    .clickable { },
                headlineContent = {
                    Column() {
                        SuggestionChip(
                            onClick = { },
                            border = SuggestionChipDefaults.suggestionChipBorder(
                                borderWidth = 0.dp,
                                borderColor = Color.Transparent,
                                disabledBorderColor = Color.Transparent
                            ),
                            colors = ChipColors(
                                containerColor = AppColors.yellow_103,
                                labelColor = Color.White,
                                leadingIconContentColor = AppColors.yellow_103,
                                trailingIconContentColor = AppColors.yellow_103,
                                disabledContainerColor = AppColors.yellow_103,
                                disabledLabelColor = Color.White,
                                disabledLeadingIconContentColor = AppColors.yellow_103,
                                disabledTrailingIconContentColor = AppColors.yellow_103
                            ),
                            label = { Text("Premium") })
                        Text(
                            text = "Derin Ürün Analizi Yap",
                            style = btnFeedDetailDeepAnalyzerTitle
                        )
                        Text(
                            text = "Derin Ürün Analizini yaparak ürüne ait fiyat aralıklarını, eksilerini ve artılarını tespit edebilirsiniz..",
                            style = btnFeedDetailDeepAnalyzerSpotText
                        )
                    }
                },
                leadingContent = {
                    Image(
                        painter = painterResource(id = R.mipmap.productdeepanalyzer),
                        contentDescription = null,
                        modifier = Modifier.size(120.dp)
                    )
                },
                trailingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                        contentDescription = null
                    )
                }
            )
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ITPTheme {
        Greeting("Android")
    }
}*/
