package com.isticpla.itp.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.listofShops
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.DropDownTextField
import com.isticpla.itp.uimodules.DropDowndTextFieldRequest
import com.isticpla.itp.uimodules.dropdownMenuItemColors
import com.isticpla.itp.uimodules.dropdownTextFieldColors

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeSectionHeader() {
    val configuration = LocalConfiguration.current
    val carouselImageWidth = configuration.screenWidthDp - 50
    Log.v("MainActivity", "Screen Width :${carouselImageWidth}")
    val shopselectedOptionText = rememberSaveable { mutableStateOf("") }
    val shopExpend = remember { mutableStateOf(false) }
    val listofCarousel = ArrayList<Int>()
    listofCarousel.add(R.mipmap.caroussel1)
    listofCarousel.add(R.mipmap.caroussel2)
    listofCarousel.add(R.mipmap.caroussel3)
    val homeCarouselState = rememberPagerState(pageCount = { listofCarousel.size })
    var wsize=16.dp
    var hsize=16.dp
    var _shape= CircleShape
    var color =AppColors.blue_104
    Column {
        DropDownTextField(
            request = DropDowndTextFieldRequest(
                exposedDropdownMenuBoxModifier = null,
                label = "Mağazalarım",
                selectedOptionText = shopselectedOptionText,
                expended = shopExpend,
                listOfOptions = listofShops,
                textFieldModifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, AppColors.primaryGrey, RoundedCornerShape(6.dp)),
                textFieldReadOnly = true,
                textfieldColors = dropdownTextFieldColors(null, true),
                menuItemColors = dropdownMenuItemColors(null, true),
                menuItemModifier = Modifier
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalPager(
            state = homeCarouselState,
            pageSpacing=20.dp
        ) { page ->
            val itm = listofCarousel[page]
            CarouselItem(image = itm)
        }
        Spacer(modifier=Modifier.height(18.dp))
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(homeCarouselState.pageCount) { iteration ->

                if (homeCarouselState.currentPage == iteration){
                    color=AppColors.blue_104
                    _shape= RoundedCornerShape(24.dp)
                    wsize=34.dp
                } else {
                    color=AppColors.blue_105
                    _shape= CircleShape
                    wsize=16.dp
                }
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(_shape)
                        .background(color)
                        .size(wsize,hsize)
                )
            }
        }
    }
}

@Composable
internal fun CarouselItem(image: Int) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(6.dp)),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun HomeSectionSectors() {

}

@Composable
fun HomeSectionDesigns() {

}

@Composable
fun HomeSectionCampains() {

}

@Composable
fun HomeSectionInStockSales() {

}

@Composable
fun HomeSectionSectorNews() {

}