package com.isticpla.itp.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.database.Account
import com.isticpla.itp.database.AccountViewModel
import com.isticpla.itp.dummydata.BusinessTypeItem
import com.isticpla.itp.dummydata.ShopItem
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.AppShopDropdown
import com.isticpla.itp.uimodules.Carousel
import com.isticpla.itp.uimodules.CarouselPagerRequest
import com.isticpla.itp.uimodules.CarouselRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun HomeSectionHeader(
    buttomSheetScaffoldState: BottomSheetScaffoldState
) {
    val scope = rememberCoroutineScope()
    val homeViewModel = hiltViewModel<HomeViewMode>()
    val selectedShop =
        homeViewModel.selectedShop.collectAsStateWithLifecycle(
            initialValue = mutableStateOf(
                ShopItem(0, "", "")
            )
        )

    val carouselListState = homeViewModel.carouselList.collectAsState(initial = emptyList())
    val carouselRequest = CarouselRequest(
        visuals = carouselListState.value,
        carouselmodifier = Modifier,
        pagespacing = 20.dp,
        visualitemmodifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .requiredHeight(220.dp),
        pager = CarouselPagerRequest(true, 18.dp, AppColors.blue_0xFF0495f1, AppColors.greyLight)
    )

    Column {

        OutlinedCard(
            modifier = Modifier
                .clickable {
                    scope.launch {
                        buttomSheetScaffoldState.bottomSheetState.expand()
                    }
                }
                .fillMaxWidth(),
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(1.dp, AppColors.secondaryGrey),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Row(
                modifier=Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = AppColors.blue_0xFF0495f1
                            )
                        ) { append("${selectedShop.value.value.name}\n") }
                        append(selectedShop.value.value.address)
                    },
                    style = TextStyle(
                        fontFamily = poppinFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    //modifier = Modifier.weight(1f)

                )
                Icon(painter= painterResource(id = R.drawable.round_expand_more_24), contentDescription = null,tint=AppColors.secondaryGrey)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Carousel(requests = carouselRequest)
    }
}


@Composable
fun HomeSectionSectors(
    navController: NavController,
) {
    val accountViewModel = hiltViewModel<AccountViewModel>()
    val account = accountViewModel.getAccount.collectAsStateWithLifecycle(initialValue = Account())
    var sectorList = remember { mutableListOf<BusinessTypeItem>() }
    LaunchedEffect(Unit) {
        delay(680L)
        if (account.value.id <= 0)
            navController.navigate("startselectculture")
    }
    if (!account.value.sectors.isNullOrEmpty()) {
        account.value.sectors?.let {
            val cc = accountViewModel.getSectorList(it)
                .collectAsStateWithLifecycle(initialValue = emptyList())
            if (cc.value.isNotEmpty())
                sectorList = cc.value.toMutableList()
        }
    }


    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text("Sektörler", style = homeSectionTitle)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start)
        ) {
            sectorList.forEach { b ->
                val isSectorSelected by remember { mutableStateOf(true) }
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .width(96.dp)
                        .height(128.dp),
                    //.padding(all = 7.dp),
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

@Composable
fun HomeSectionDesigns(
    navController: NavController
) {
    val homeViewModel = hiltViewModel<HomeViewMode>()
    val listofDesigns =
        homeViewModel.designsList.collectAsState(initial = emptyList())
    Column {
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Tasarımlar", modifier = Modifier.wrapContentSize(), style = homeSectionTitle)
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = { navController.navigate("feed") },
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

@Composable
fun HomeSectionCampaigns(
    navController: NavController
) {
    val homeViewModel = hiltViewModel<HomeViewMode>()
    val listofCampaigns =
        homeViewModel.campaignList.collectAsState(initial = emptyList())
    Column {

        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Kampanyalar", modifier = Modifier.wrapContentSize(), style = homeSectionTitle)
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = { navController.navigate("campaigns") },
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

            listofCampaigns.value.take(2).forEach { b ->

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
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start)
            ) {
                if (listofCampaigns.value.isNotEmpty()) {
                    //region left side
                    val lastOne = listofCampaigns.value[2]
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(.48f)
                            .requiredHeight(280.dp)
                            .padding(bottom = 16.dp),
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
                                .fillMaxSize()
                                .paint(
                                    sizeToIntrinsics = false,
                                    painter = painterResource(id = lastOne.image),
                                    contentScale = ContentScale.Fit,
                                    alignment = Alignment.CenterStart
                                ),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.End
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(.7f)
                                    .padding(end = 30.dp)
                                    .wrapContentWidth(Alignment.End)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(16.dp)
                                ) {

                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(lastOne.subTitle, style = homeSectorCampaignCardSpot)
                                }
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(lastOne.title, style = homeSectorCampaignCardTitle)
                            }
                        }
                    }
                    //endregion

                    //region right side
                    val lastTwo = listofCampaigns.value[3]
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(280.dp)
                            .padding(bottom = 16.dp),
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
                                .fillMaxSize()
                                .paint(
                                    sizeToIntrinsics = false,
                                    painter = painterResource(id = lastTwo.image),
                                    contentScale = ContentScale.Fit,
                                    alignment = Alignment.CenterEnd
                                ),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.End
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(start = 10.dp, end = 60.dp)
                                    .fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(16.dp)
                                ) {

                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(lastTwo.subTitle, style = homeSectorCampaignCardSpot)
                                }
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(lastTwo.title, style = homeSectorCampaignCardTitle)
                            }
                        }
                    }
                    //endregion
                }
            }
        }
    }

}

@Composable
fun HomeSectionInStockSales(
    navController: NavController,
) {
    val homeViewModel = hiltViewModel<HomeViewMode>()
    val listofStockSales =
        homeViewModel.stokSaleList.collectAsState(initial = emptyList())
    Column {
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Stoklu Satışlar", modifier = Modifier.wrapContentSize(), style = homeSectionTitle)
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = { navController.navigate("stocksales") },
            ) {
                Text("Hepsini Göster", style = homeSectorShowAll)
            }
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            listofStockSales.value.forEach { b ->
                Column(
                    modifier = Modifier
                        .clickable { }
                        .requiredWidth(140.dp)
                        .requiredHeight(290.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .size(140.dp, 210.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .paint(
                                painter = painterResource(id = b.image),
                                contentScale = ContentScale.Crop
                            ),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = b.price,
                            modifier = Modifier
                                .background(Color.White.copy(.6f))
                                .fillMaxWidth()
                                .padding(start = 8.dp, end = 8.dp),
                            style = homeSectorDesignCardPriceLabel
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = b.title,
                        modifier = Modifier
                            .requiredWidth(140.dp)
                            .requiredHeight(90.dp),
                        maxLines = 3,
                        style = homeSectorDesignCardLabel
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun HomeSectionSectorNews(
    navController: NavController,
) {
    val homeViewModel = hiltViewModel<HomeViewMode>()
    val listofSectorNews =
        homeViewModel.sectorNewsList.collectAsState(initial = emptyList())
    Column {
        Spacer(modifier = Modifier.height(20.dp))
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