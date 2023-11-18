package com.isticpla.itp.feed

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.FeedDashboardItems
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.home.homeSubSectionTitle
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.Bg
import com.isticpla.itp.uimodules.DeepAnalyzerButton
import com.isticpla.itp.uimodules.defaultmenuItemState
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedProductDetail(
    navController: NavController,
    homeViewMode: HomeViewMode = hiltViewModel()
) {
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
        bottomBar = { Bg(navController, defaultmenuItemState) },
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
                        onClick = { navController.popBackStack() }) {
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
            DeepAnalyzerButton()
        }
    }
}