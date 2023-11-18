package com.isticpla.itp.home

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.SectorNewsItem
import com.isticpla.itp.feed.feedDetailContent
import com.isticpla.itp.feed.feedDetailDate
import com.isticpla.itp.feed.feedDetailTitle
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.Bg
import com.isticpla.itp.uimodules.defaultmenuItemState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListDetail(
    navController: NavController,
    homeViewMode: HomeViewMode = hiltViewModel(),
) {
    val listofSectorNews =
        homeViewMode.sectorNewsList.collectAsState(initial = emptyList<SectorNewsItem>())
    var title by remember { mutableStateOf("Türkiye - Çin Ticaret Köprüsü Başlıyor") }
    var content by remember {
        mutableStateOf(
            "Ticaret Bakanlığından paylaşılan bir açıklama da, Pekin Büyükelçiliği Ticaret Müşavirliği tarafından Türkiye plastik üreticileri ile karşılıklı bir anlaşma imzalanmıştır.\n" +
                    "\n" +
                    "Anlaşma kapsamında Türkiye plastik üreticileri hammadde ihtiyaçlarının karşılanması hedeflenmiştir.\n" +
                    "\n" +
                    "Aynı zamanda tedarik süreçlerinin de kolaylaştırılması hedefi ile karşılıklı gümrük birliği benzeri bir anlaşma çalışmaları da başlatılmıştır."
        )
    }
    var date by remember { mutableStateOf("27.04.2023") }

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
                actions = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_home_24),
                            contentDescription = null
                        )
                    }
                },
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
                        onClick = { navController.navigate("newlist") }) {
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
                .padding(horizontal = 10.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.requiredHeightIn(min = 180.dp, max = 220.dp))
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
                    Spacer(modifier = Modifier.height(26.dp))
                    Row(
                        modifier= Modifier.fillMaxWidth(),
                        verticalAlignment= Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        IconButton(onClick = { }) {
                            Icon(
                                painter = painterResource(id = R.drawable.outline_favorite_border_24),
                                contentDescription = null
                            )
                        }
                        Spacer(modifier = Modifier.width(3.dp))
                        IconButton(onClick = { }) {
                            Icon(
                                painter = painterResource(id = R.drawable.outline_share_24),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Benzer Haberler",
                style = TextStyle(
                    fontFamily = poppinFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AppColors.primaryGrey
                ), modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            listofSectorNews.value.forEach { b ->
                ListItem(
                    modifier = Modifier.clickable {
                        navController.navigate("newslist/detail")
                    },
                    colors = ListItemColors(
                        containerColor = Color.Transparent,
                        headlineColor = AppColors.primaryGrey,
                        leadingIconColor = Color.Transparent,
                        overlineColor = Color.Transparent,
                        supportingTextColor = AppColors.primaryGrey,
                        trailingIconColor = AppColors.primaryGrey,
                        disabledHeadlineColor = AppColors.primaryGrey,
                        disabledLeadingIconColor = Color.Transparent,
                        disabledTrailingIconColor = AppColors.primaryGrey,
                    ),
                    headlineContent = {
                        Text(
                            b.title,
                            modifier = Modifier.fillMaxWidth(),
                            style = homeSectorNewsTitle
                        )
                    },
                    supportingContent = {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                b.content,
                                modifier = Modifier.fillMaxWidth(),
                                style = homeSectorNewsContent
                            )
                            Text(b.date, style = homeSectorNewsDate)
                        }
                    },
                    leadingContent = {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(6.dp)),
                        ) {
                            Image(
                                painter = painterResource(id = b.icon),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }
                    },
                    trailingContent = {
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                                contentDescription = null,

                                )
                        }
                    }
                )
                HorizontalDivider(thickness = 1.dp, color = AppColors.primaryGreyDisabled)
            }

        }
    }
}