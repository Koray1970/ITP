package com.isticpla.itp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.*
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.home.*
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.Bg
import com.isticpla.itp.uimodules.BottomBarMenuItem
import com.isticpla.itp.uimodules.BottomBarMenuItemType
import com.isticpla.itp.uistyles.*
import com.isticpla.itp.views.helpers.AppMediumTopBar
import com.isticpla.itp.views.helpers.AppMediumTopBarItem
import com.isticpla.itp.views.helpers.TopBarActionItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MyPanel(
    navController: NavController,
) {
    val roundcornerRectangel = RoundedCornerShape(10.dp)

    val menuItemState = mutableListOf<BottomBarMenuItem>(
        BottomBarMenuItem(BottomBarMenuItemType.HOME, true),
        BottomBarMenuItem(BottomBarMenuItemType.BOOKMARK),
        BottomBarMenuItem(BottomBarMenuItemType.NOTIFICATION, hasbadge = true),
        BottomBarMenuItem(BottomBarMenuItemType.PROFILE),
    )

    val homeViewModel = hiltViewModel<HomeViewMode>()

    val activeoffers =
        homeViewModel.panelActiveOffers.collectAsStateWithLifecycle(initialValue = emptyList<PanelActiveOfferItem>())
    val collections =
        homeViewModel.panelCollections.collectAsStateWithLifecycle(initialValue = emptyList<PanelCollectionItem>())
    val drafts =
        homeViewModel.panelDrafts.collectAsStateWithLifecycle(initialValue = emptyList<PanelOfferDraftItem>())
    val completedOffers =
        homeViewModel.panelCompletedOffers.collectAsStateWithLifecycle(initialValue = emptyList<PanelOfferCompletedItem>())

    Scaffold(
        containerColor = Color.White,
        topBar = AppMediumTopBar(
            AppMediumTopBarItem(
                containerColor = Color.White.copy(.8f),
                title = { Text("Panelim", style = homeSubSectionTitle) },
                actions = listOf<TopBarActionItem>(
                    TopBarActionItem(
                        url = { navController.navigate("home") },
                        icon = R.drawable.outline_home_24
                    )
                ).toMutableList(),
                navigationIcon = TopBarActionItem(
                    url = { navController.popBackStack() },
                    icon = R.drawable.arrow_left
                )
            )
        ),
        bottomBar = { Bg(navController, menuItemState) }
    ) { innerpadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerpadding)
        ) {
            //region Aktif Siparislerim
            PanelSectionTitle(
                title = "Aktif Siparişlerim",
                numberofitems = activeoffers.value.size,
                showallNav = { navController.navigate("mypanel/activeoffers") })

            activeoffers.value.take(3).forEach { ac ->
                ListItem(
                    modifier = Modifier.clickable { },
                    colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                    headlineContent = {
                        Text(text = buildAnnotatedString {
                            withStyle(style = listitemTitle) { append("${ac.title}\n") }
                            append("${ac.status.result}\n")
                            append("${ac.date}")
                        }, style = listitemHeaderLineContent)
                    },
                    leadingContent = {
                        Card(
                            modifier = Modifier
                                .size(80.dp)
                                .clip(roundcornerRectangel),
                            shape = roundcornerRectangel,
                            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                        ) {
                            Image(
                                painter = painterResource(id = ac.image),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }
                    },
                    trailingContent = {
                        Column(
                            modifier = Modifier.size(40.dp, 40.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.round_arrow_forward_ios_24),
                                contentDescription = null,
                                tint = AppColors.grey_124
                            )
                        }
                    }
                )
            }
            //endregion
            //region Teklif Talepleri
            Spacer(modifier = Modifier.height(30.dp))
            PanelSectionTitle(
                title = "Teklif Taleplerim",
                numberofitems = activeoffers.value.size,
                showallNav = {})
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
                    .horizontalScroll(rememberScrollState()),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally)
            ) {
                activeoffers.value.forEach { tt ->
                    Column(
                        modifier = Modifier
                            .requiredWidth(125.dp),
                    ) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                            modifier = Modifier.size(125.dp),
                            shape = roundcornerRectangel,
                        ) {
                            Image(
                                painter = painterResource(id = tt.image),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }
                        Text(text = buildAnnotatedString {
                            withStyle(style = listitemTitle) { append("${tt.title}\n") }
                            append("${tt.status.result}")
                        }, style = listitemHeaderLineContent)
                    }
                }
            }
            //endregion
            //region Koleksiyonlarim
            Spacer(modifier = Modifier.height(30.dp))
            PanelSectionTitle(
                title = "Koleksiyonlarım",
                numberofitems = collections.value.size,
                showallNav = { navController.navigate("mypanel/collections") })
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
                    .horizontalScroll(rememberScrollState()),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally)
            ) {
                collections.value.forEach { tt ->
                    Column(
                        modifier = Modifier
                            .requiredWidth(135.dp),
                    ) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                            modifier = Modifier.size(135.dp, 170.dp),
                            shape = roundcornerRectangel,
                        ) {
                            Image(
                                painter = painterResource(id = tt.image),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(135.dp, 170.dp)
                            )
                        }
                        Text(text = buildAnnotatedString {
                            withStyle(style = listitemTitle) { append("${tt.title}\n") }
                            append("${tt.status.result}")
                        }, style = listitemHeaderLineContent.merge(textAlign = TextAlign.Center))
                    }
                }
            }
            //endregion
            //region Taslaklarim
            Spacer(modifier = Modifier.height(30.dp))
            PanelSectionTitle(
                title = "Taslaklarım",
                numberofitems = drafts.value.size,
                showallNav = {})
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
                    .horizontalScroll(rememberScrollState()),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally)
            ) {
                drafts.value.forEach { tt ->
                    Column(
                        modifier = Modifier
                            .requiredWidth(125.dp),
                    ) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                            modifier = Modifier.size(125.dp),
                            shape = roundcornerRectangel,
                        ) {
                            Image(
                                painter = painterResource(id = tt.image),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }
                        Text(text = buildAnnotatedString {
                            withStyle(style = listitemTitle) { append("${tt.title}\n") }
                            append("${tt.status.result}")
                        }, style = listitemHeaderLineContent)
                    }
                }
            }
            //endregion
            //region Tamamlananlar
            Spacer(modifier = Modifier.height(30.dp))
            PanelSectionTitle(
                title = "Tamamlananlar",
                numberofitems = completedOffers.value.size,
                showallNav = {})
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    //.padding(start = 10.dp)
                    .horizontalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                maxItemsInEachRow = 2
            ) {
                completedOffers.value.forEach { tt ->
                    ListItem(
                        modifier = Modifier.clickable { },
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        headlineContent = {
                            Text(text = buildAnnotatedString {
                                withStyle(style = listitemCompleteTitle) { append("${tt.title}\n") }
                                append("${tt.status.result}")
                            }, style = listitemHeaderLineContent)
                        },
                        leadingContent = {
                            Card(
                                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                                modifier = Modifier.size(50.dp),
                                shape = roundcornerRectangel,
                            ) {
                                Image(
                                    painter = painterResource(id = tt.image),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    )
                }
            }
            //endregion
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
fun PanelSectionTitle(
    title: String,
    numberofitems: Int? = null,
    showallNav: () -> Unit,
    showallVisible: Boolean = true
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    Row(
        modifier = Modifier.weight(1f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            10.dp,
            Alignment.Start
        )
    ) {
        Text(text = title, style = sectionTitle)
        if (numberofitems != null)
            if (numberofitems > 0) {
                Card(
                    shape = RoundedCornerShape(10),
                    colors = CardDefaults.cardColors(containerColor = AppColors.red_100)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 6.dp, vertical = 4.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = numberofitems.toString(), style = sectionBadge)
                    }
                }
            }
    }
    if(showallVisible) {
        TextButton(
            onClick = showallNav//{ /*navController.navigate("")*/ },
        ) {
            Text("Hepsini Göster", style = homeSectorShowAll)
        }
    }
}