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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
fun FeedDashboardItemLarge(fdi: FeedDashboardItems) = Column(
    modifier=Modifier.fillMaxWidth()
) {
    Box(
        modifier=Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .clip(RoundedCornerShape(10.dp))
            .paint(painter = painterResource(id = fdi.image!!), contentScale = ContentScale.Crop)
    ){
        if(!fdi.spottext.isNullOrEmpty()) {
            Box(
                modifier=Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = fdi.spottext,
                    style = feeddashboardLargeItemSpotText,
                    modifier=Modifier
                        .fillMaxSize(.5f)
                        .fillMaxHeight()
                        .padding(top=30.dp)
                )
            }
        }
    }
    FeedDashBoardItemButton()
    Text(text = fdi.title, style = feeddashboardLargeItemTitle)
    if (!fdi.content.isNullOrEmpty())
        Text(text = fdi.content, style = feeddashboardLargeItemContent)
    Text(text = fdi.date, style = feeddashboardItemDate)
}

@Composable
fun FeedDashboardItemMedium(fdi: FeedDashboardItems) = Card() {
    Row() {
        Image(
            painter = painterResource(id = fdi.image!!),
            contentDescription = null,
            modifier = Modifier.clip(RoundedCornerShape(8.dp))
        )
    }
    Row() {
        Text(text = fdi.title, style = feeddashboardLargeItemTitle)
        if (!fdi.content.isNullOrEmpty())
            Text(text = fdi.content, style = feeddashboardLargeItemContent)
        Text(text = fdi.date, style = feeddashboardItemDate)
        FeedDashBoardItemButton()
    }
}

@Composable
fun FeedDashboardItemNoImage(fdi: FeedDashboardItems) = Column() {
    Text(text = fdi.title, style = feeddashboardLargeItemTitle)
    if (!fdi.content.isNullOrEmpty())
        Text(text = fdi.content, style = feeddashboardLargeItemContent)
    Text(text = fdi.date, style = feeddashboardItemDate)
    FeedDashBoardItemButton()
}
@Composable
fun FeedDashBoardItemButton()=Row() {
    Row(
        modifier = Modifier
            .clickable {  }
            .background(AppColors.grey_127)
            .fillMaxWidth(.76f)
            .clip(RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Teklif Talebi Oluştur",
            style =btnCreateQuoteRequestButton,
            modifier = Modifier
                .fillMaxSize(.50f)
                .wrapContentHeight(align = Alignment.CenterVertically)

        )
    }
    Spacer(modifier = Modifier.width(10.dp))
    IconButton(
        onClick = {},
        modifier = Modifier
            .background(AppColors.grey_127)
            .clip(RoundedCornerShape(5.dp, 0.dp, 0.dp, 5.dp))
    ) {
        Icon(
            painter = painterResource(id = R.drawable.outline_favorite_border_24),
            contentDescription = null
        )
    }
    IconButton(
        onClick = {},
        modifier = Modifier
            .background(AppColors.grey_127)
            .clip(RoundedCornerShape(0.dp, 5.dp, 5.dp, 0.dp))
    ) {
        Icon(
            painter = painterResource(id = R.drawable.outline_favorite_border_24),
            contentDescription = null
        )
    }
}