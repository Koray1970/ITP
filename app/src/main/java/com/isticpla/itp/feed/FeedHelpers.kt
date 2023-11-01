package com.isticpla.itp.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.FeedDashboardItems
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedSeachBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Card(
            colors = CardColors(
                containerColor = Color.Transparent,
                contentColor = AppColors.grey_130,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = AppColors.grey_130
            ),
            modifier = Modifier
                .fillMaxWidth(.86f)
                .requiredHeight(63.dp)
                .border(1.dp, AppColors.grey_130, RoundedCornerShape(5.dp))
        ) {
            Text(
                text = "Kelime Giriniz",
                style = TextStyle(
                    fontFamily = poppinFamily,
                    color = AppColors.grey_118,
                    fontSize = 12.sp
                ),
                modifier = Modifier.padding(start = 10.dp, top = 10.dp)
            )
            Row() {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_search_24),
                        contentDescription = null
                    )
                }
                Column(

                ) {

                }
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Card(
            onClick = {},
            colors = CardColors(
                containerColor = AppColors.grey_133,
                contentColor = Color.Black,
                disabledContainerColor = AppColors.grey_133,
                disabledContentColor = Color.Black
            ),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .border(1.dp, AppColors.grey_130, RoundedCornerShape(5.dp))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_favorite_border_24),
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
fun FeedDashboardItemLarge(fdi: FeedDashboardItems,navController: NavController) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .clickable { navController.navigate("feed/productdetail") }
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .clip(RoundedCornerShape(10.dp))
            .paint(painter = painterResource(id = fdi.image!!), contentScale = ContentScale.Crop)
    ) {
        if (!fdi.spottext.isNullOrEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = fdi.spottext,
                    style = feeddashboardLargeItemSpotText,
                    modifier = Modifier
                        .fillMaxSize(.5f)
                        .fillMaxHeight()
                        .padding(top = 30.dp)
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    FeedDashBoardItemButton(null,navController)
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = fdi.title, style = feeddashboardLargeItemTitle)
    if (!fdi.content.isNullOrEmpty())
        Text(text = fdi.content, style = feeddashboardLargeItemContent)
    Spacer(modifier = Modifier.height(6.dp))
    Text(text = fdi.date, style = feeddashboardItemDate)
}

@Composable
fun FeedDashboardItemMedium(fdi: FeedDashboardItems,navController: NavController) = Row(
    modifier=Modifier
        .clickable { navController.navigate("feed/productdetail") }
) {
    Column(
        modifier = Modifier
            .requiredSize(100.dp)
    ) {
        Image(
            painter = painterResource(id = fdi.image!!),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(RoundedCornerShape(8.dp))
        )
    }
    Column(
        modifier = Modifier.padding(start = 16.dp)
    ) {
        Text(text = fdi.title, style = feeddashboardLargeItemTitle)
        if (!fdi.content.isNullOrEmpty())
            Text(text = fdi.content, style = feeddashboardLargeItemContent)
        Text(text = fdi.date, style = feeddashboardItemDate)
        Spacer(modifier = Modifier.height(10.dp))
        FeedDashBoardItemButton(2,navController)
    }
}

@Composable
fun FeedDashboardItemNoImage(fdi: FeedDashboardItems,navController: NavController) = Column(
    modifier=Modifier
        .clickable { navController.navigate("feed/productdetail") }
) {
    Text(text = fdi.title, style = feeddashboardLargeItemTitle)
    if (!fdi.content.isNullOrEmpty())
        Text(text = fdi.content, style = feeddashboardLargeItemContent)
    Text(text = fdi.date, style = feeddashboardItemDate)
    Spacer(modifier = Modifier.height(10.dp))
    FeedDashBoardItemButton(null,navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedDashBoardItemButton(contentType: Int?,navController: NavController) = Row(
    modifier = Modifier
        .fillMaxSize()
        .wrapContentHeight(Alignment.CenterVertically),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Start
) {
    Card(
        onClick = {},
        shape = RoundedCornerShape(6.dp),
        colors = feedDashboardButtonColors,
        modifier = Modifier
            .weight(
                when (contentType) {
                    2 -> 1.9f
                    else -> 3.2f
                }
            )
            .requiredHeight(38.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Teklif Talebi Oluştur",
                style = btnCreateQuoteRequestButton,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(align = Alignment.CenterVertically)

            )
        }
    }
    Row(
        modifier = Modifier.weight(1f),
        horizontalArrangement = Arrangement.End
    ) {
        Card(
            onClick = {},
            shape = RoundedCornerShape(6.dp, 0.dp, 0.dp, 6.dp),
            colors = feedDashboardButtonColors,
            modifier = Modifier
                .requiredSize(44.dp, 38.dp)
                .fillMaxWidth(1f)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_favorite_border_24),
                    contentDescription = null
                )
            }
        }
        Card(
            onClick = {},
            shape = RoundedCornerShape(0.dp, 6.dp, 6.dp, 0.dp),
            colors = feedDashboardButtonColors,
            modifier = Modifier
                .requiredSize(44.dp, 38.dp)
                .fillMaxWidth(1f)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_share_24),
                    contentDescription = null
                )
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedDetailButton(navController: NavController) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(Alignment.CenterVertically),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Start
) {
    Card(
        onClick = {},
        shape = RoundedCornerShape(6.dp),
        colors = feedDetailButtonColors,
        modifier = Modifier
            .weight(3.2f)
            .requiredHeight(38.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Teklif Talebi Oluştur",
                style = btnFeedDetailCreateQuoteRequest,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(align = Alignment.CenterVertically)

            )
        }
    }
    Row(
        modifier = Modifier.weight(1f),
        horizontalArrangement = Arrangement.End
    ) {
        Card(
            onClick = {},
            shape = RoundedCornerShape(6.dp, 0.dp, 0.dp, 6.dp),
            colors = feedDashboardButtonColors,
            modifier = Modifier
                .requiredSize(44.dp, 38.dp)
                .fillMaxWidth(1f)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_favorite_border_24),
                    contentDescription = null
                )
            }
        }
        Card(
            onClick = {},
            shape = RoundedCornerShape(0.dp, 6.dp, 6.dp, 0.dp),
            colors = feedDashboardButtonColors,
            modifier = Modifier
                .requiredSize(44.dp, 38.dp)
                .fillMaxWidth(1f)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_share_24),
                    contentDescription = null
                )
            }
        }
    }
}