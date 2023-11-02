package com.isticpla.itp.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.BusinessTypeItem
import com.isticpla.itp.dummydata.HomeCampaignItem
import com.isticpla.itp.dummydata.HomeDesignItem
import com.isticpla.itp.dummydata.SectorNewsItem
import com.isticpla.itp.dummydata.listofDesigns
import com.isticpla.itp.dummydata.listofHomeCampaigns
import com.isticpla.itp.dummydata.listofHomeSectorNews
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.DropDownTextField
import com.isticpla.itp.uimodules.DropDowndTextFieldRequest
import com.isticpla.itp.uimodules.dropdownMenuItemColors
import com.isticpla.itp.uimodules.dropdownTextFieldColors
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeSectionHeader(homeViewMode: HomeViewMode) {
    val configuration = LocalConfiguration.current
    val listofShops =
        homeViewMode.shopList.collectAsState(initial = emptyList<Pair<String, String>>())

    val carouselImageWidth = configuration.screenWidthDp - 50
    Log.v("MainActivity", "Screen Width :${carouselImageWidth}")
    val shopselectedOptionText = rememberSaveable { mutableStateOf("") }
    val shopExpend = remember { mutableStateOf(false) }
    val listofCarousel = ArrayList<Int>()
    listofCarousel.add(R.mipmap.caroussel1)
    listofCarousel.add(R.mipmap.caroussel2)
    listofCarousel.add(R.mipmap.caroussel3)
    val homeCarouselState = rememberPagerState(pageCount = { listofCarousel.size })
    var wsize = 16.dp
    var hsize = 16.dp
    var _shape = CircleShape
    var color = AppColors.blue_104
    Column {
        DropDownTextField(
            request = DropDowndTextFieldRequest(
                exposedDropdownMenuBoxModifier = null,
                label = "Mağazalarım",
                selectedOptionText = shopselectedOptionText,
                expended = shopExpend,
                listOfOptions = listofShops.value,
                textFieldModifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, AppColors.grey_133, RoundedCornerShape(6.dp)),
                textFieldReadOnly = true,
                textfieldColors = dropdownTextFieldColors(null, true),
                menuItemColors = dropdownMenuItemColors(null, true),
                menuItemModifier = Modifier
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalPager(
            state = homeCarouselState,
            pageSpacing = 20.dp
        ) { page ->
            val itm = listofCarousel[page]
            CarouselItem(image = itm)
        }
        Spacer(modifier = Modifier.height(18.dp))
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
                    color = AppColors.blue_104
                    _shape = RoundedCornerShape(24.dp)
                    wsize = 34.dp
                } else {
                    color = AppColors.blue_105
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

@Composable
internal fun CarouselItem(image: Int) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .requiredHeight(IntrinsicSize.Min)
        .clip(RoundedCornerShape(8.dp)),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSectionSectors(
    navController: NavController,
    homeViewMode: HomeViewMode
) {
    val bussinesList =
        homeViewMode.sectorList.collectAsState(initial = emptyList<BusinessTypeItem>())
    Column() {
        Spacer(modifier = Modifier.height(40.dp))
        Text("Sektörler", style = homeSectionTitle)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            bussinesList.value.forEach { b ->
                var isSectorSelected by remember { mutableStateOf(true) }
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardColors(
                        containerColor = if (isSectorSelected) {
                            AppColors.grey_133
                        } else {
                            AppColors.primaryGrey
                        },
                        contentColor = if (isSectorSelected) {
                            AppColors.primaryGrey
                        } else {
                            Color.White
                        },
                        disabledContainerColor = if (isSectorSelected) {
                            AppColors.grey_133
                        } else {
                            AppColors.primaryGrey
                        },
                        disabledContentColor = if (isSectorSelected) {
                            AppColors.primaryGrey
                        } else {
                            Color.White
                        }
                    ),
                    modifier = Modifier
                        .width(96.dp)
                        .height(128.dp)
                        .padding(all = 7.dp),
                    onClick = {
                        //isSectorSelected = !isSectorSelected
                        navController.navigate("feed")
                    }
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(painter = painterResource(id = b.icon), contentDescription = null)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = b.label.uppercase(Locale.ROOT), style = homeSectorLabel)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSectionDesigns(
    navController: NavController,
    homeViewMode: HomeViewMode
) {
    val listofDesigns =
        homeViewMode.designsList.collectAsState(initial = emptyList<HomeDesignItem>())
    Column() {
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Tasarımlar", modifier = Modifier.wrapContentSize(), style = homeSectionTitle)
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = { },
            ) {
                Text("Hepsini Göster", style = homeSectorShowAll)
            }
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            listofDesigns.value.forEach { b ->
                Column(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            disabledContentColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .width(140.dp)
                            .height(210.dp),
                        onClick = {}
                    ) {
                        Image(
                            painter = painterResource(id = b.image),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = b.title,
                        modifier = Modifier.padding(start = 8.dp),
                        style = homeSectorDesignCardLabel
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSectionCampaigns(
    navController: NavController,
    homeViewMode: HomeViewMode
) {
    val listofCampaigns =
        homeViewMode.campaignList.collectAsState(initial = emptyList<HomeCampaignItem>())
    Column() {
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Kampanyalar", modifier = Modifier.wrapContentSize(), style = homeSectionTitle)
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = { },
            ) {
                Text("Hepsini Göster", style = homeSectorShowAll)
            }
        }
        Spacer(modifier = Modifier.height(3.dp))
        Column(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var c = 0
            listofCampaigns.value.forEach { b ->
                when (b.uitype) {
                    1 -> {
                        Card(
                            modifier = Modifier.padding(bottom = 16.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardColors(
                                containerColor = AppColors.grey_144,
                                contentColor = AppColors.grey_147,
                                disabledContainerColor = AppColors.grey_144,
                                disabledContentColor = AppColors.grey_147
                            ),
                            onClick = {}
                        ) {
                            Row(
                                modifier = Modifier.padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .wrapContentWidth(Alignment.Start)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(16.dp)
                                    ) {
                                        VerticalDivider(
                                            thickness = 1.dp,
                                            color = AppColors.grey_138
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(b.subTitle, style = homeSectorCampaignCardSpot)
                                    }
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Text(b.title, style = homeSectorCampaignCardTitle)
                                }
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .wrapContentWidth(Alignment.End),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .padding(vertical = 10.dp)
                                            .size(120.dp)
                                            .clip(CircleShape)
                                            .background(AppColors.grey_150)
                                    ) {}
                                    Box {
                                        Image(
                                            painter = painterResource(id = b.image),
                                            contentDescription = null,
                                            contentScale = ContentScale.Fit,
                                            modifier = Modifier.size(140.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    else -> {
                        if (b.imPosition == 1) {
                            Card(
                                modifier = Modifier
                                    .padding(bottom = 16.dp)
                                    .requiredWidth(IntrinsicSize.Min)
                                    .paint(
                                        sizeToIntrinsics = true,
                                        painter = painterResource(id = b.image),
                                        contentScale = ContentScale.Fit,
                                        alignment = Alignment.CenterStart
                                    ),
                                shape = RoundedCornerShape(8.dp),
                                colors = CardColors(
                                    containerColor = AppColors.grey_144,
                                    contentColor = AppColors.grey_147,
                                    disabledContainerColor = AppColors.grey_144,
                                    disabledContentColor = AppColors.grey_147
                                ),
                                onClick = {}
                            ) {
                                Column(
                                    modifier = Modifier
                                        .wrapContentWidth(Alignment.Start)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(16.dp)
                                    ) {
                                        VerticalDivider(
                                            thickness = 1.dp,
                                            color = AppColors.grey_138
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(b.subTitle, style = homeSectorCampaignCardSpot)
                                    }
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Text(b.title, style = homeSectorCampaignCardTitle)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSectionInStockSales(
    navController: NavController,
    homeViewMode: HomeViewMode
) {
    val listofStockSales =
        homeViewMode.stokSaleList.collectAsState(initial = emptyList<HomeDesignItem>())
    Column() {
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Stoklu Satışlar", modifier = Modifier.wrapContentSize(), style = homeSectionTitle)
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = { },
            ) {
                Text("Hepsini Göster", style = homeSectorShowAll)
            }
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            listofStockSales.value.forEach { b ->
                Column(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Card(
                        //shape = RoundedCornerShape(8.dp),
                        colors = CardColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            disabledContentColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .width(140.dp)
                            .height(210.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .paint(
                                painter = painterResource(id = b.image),
                                contentScale = ContentScale.Crop
                            ),
                        onClick = {}
                    ) {
                        Text(
                            text = b.price,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp, end = 8.dp),
                            style = homeSectorDesignCardPriceLabel
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(

                    ) {
                        Text(
                            text = b.title,
                            modifier = Modifier
                                .width(IntrinsicSize.Min)
                                .padding(start = 8.dp, end = 8.dp),
                            maxLines = 3,
                            style = homeSectorDesignCardLabel
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HomeSectionSectorNews(
    navController: NavController,
    homeViewMode: HomeViewMode
) {
    val listofSectorNews =
        homeViewMode.sectorNewsList.collectAsState(initial = emptyList<SectorNewsItem>())
    Column() {
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                "Sektörel Haberler",
                modifier = Modifier.wrapContentSize(),
                style = homeSectionTitle
            )
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = {
                    navController.navigate("newlist")
                },
            ) {
                Text("Hepsini Göster", style = homeSectorShowAll)
            }
        }
        Spacer(modifier = Modifier.height(3.dp))
        Column(
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            listofSectorNews.value.forEach { b ->
                ListItem(
                    modifier = Modifier.clickable { },
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
                            modifier = Modifier.requiredWidth(260.dp),
                            style = homeSectorNewsTitle
                        )
                    },
                    supportingContent = {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                b.content,
                                modifier = Modifier.requiredWidth(260.dp),
                                style = homeSectorNewsContent
                            )
                            Text(b.date, style = homeSectorNewsDate)
                        }
                    },
                    leadingContent = {
                        Box(
                            modifier = Modifier
                                .size(48.dp, 48.dp)
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