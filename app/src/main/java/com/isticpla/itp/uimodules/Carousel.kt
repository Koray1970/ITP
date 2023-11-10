package com.isticpla.itp.uimodules

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class CarouselRequest(
    val visuals: List<Int> = emptyList(),
    val carouselmodifier: Modifier = Modifier,
    val pagespacing: Dp = 20.dp,
    val visualitemmodifier: Modifier = Modifier,
    val pager: CarouselPagerRequest? = null
)

data class CarouselPagerRequest(
    val haspager: Boolean = true,
    val pagerlineheight: Dp = 18.dp,
    val pagerdefaultcolor: Color = AppColors.blue_105,
    val pagerselectedcolor: Color = AppColors.blue_104
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun Carousel(requests: CarouselRequest) {
    val visuals = requests.visuals
    val homeCarouselState = rememberPagerState(pageCount = { visuals.size })

    HorizontalPager(
        modifier = Modifier.then(requests.carouselmodifier),
        state = homeCarouselState,
        pageSpacing = requests.pagespacing
    ) { page ->
        val itm = visuals[page]
        CarouselItem(modifier = requests.visualitemmodifier, image = itm)
    }
    if (requests.pager != null) {
        var wsize = 16.dp
        var hsize = 16.dp
        var _shape = CircleShape
        var color = requests.pager.pagerdefaultcolor
        if (requests.pager.haspager) {
            Spacer(modifier = Modifier.requiredHeight(requests.pager.pagerlineheight))
            Row(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(homeCarouselState.pageCount) { iteration ->
                    if (homeCarouselState.currentPage == iteration) {
                        color = requests.pager.pagerdefaultcolor
                        _shape = RoundedCornerShape(24.dp)
                        wsize = 34.dp
                    } else {
                        color = requests.pager.pagerselectedcolor
                        _shape = CircleShape
                        wsize = 16.dp
                    }
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(_shape)
                            .background(color)
                            .size(wsize, hsize)
                    )
                }
            }
        }
    }
}

@Composable
internal fun CarouselItem(modifier: Modifier, image: Int) = Column(
    modifier = Modifier
        .then(modifier),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}