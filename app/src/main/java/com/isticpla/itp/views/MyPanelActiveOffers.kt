package com.isticpla.itp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.OrderStagesStatus
import com.isticpla.itp.dummydata.PanelActiveOfferItem
import com.isticpla.itp.dummydata.PanelCollectionItem
import com.isticpla.itp.dummydata.PanelOfferCompletedItem
import com.isticpla.itp.dummydata.PanelOfferDraftItem
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.home.homeSubSectionTitle
import com.isticpla.itp.offers.draftsListItemDate
import com.isticpla.itp.offers.draftsListItemTitle
import com.isticpla.itp.offers.draftsListItemTrailingItemText
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.Bg
import com.isticpla.itp.uimodules.BottomBarMenuItem
import com.isticpla.itp.uimodules.BottomBarMenuItemType
import com.isticpla.itp.uistyles.listitemHeaderLineContent
import com.isticpla.itp.uistyles.listitemTitle
import com.isticpla.itp.views.helpers.AppMediumTopBar
import com.isticpla.itp.views.helpers.AppMediumTopBarItem
import com.isticpla.itp.views.helpers.TopBarActionItem

@Composable
fun MyPanelActiveOffers(
    navController: NavController,
) {
    val menuItemState = mutableListOf(
        BottomBarMenuItem(BottomBarMenuItemType.HOME, true),
        BottomBarMenuItem(BottomBarMenuItemType.BOOKMARK),
        BottomBarMenuItem(BottomBarMenuItemType.NOTIFICATION, hasbadge = true),
        BottomBarMenuItem(BottomBarMenuItemType.PROFILE),
    )

    val homeViewModel = hiltViewModel<HomeViewMode>()

    val activeoffers =
        homeViewModel.panelActiveOffers.collectAsStateWithLifecycle(initialValue = emptyList<PanelActiveOfferItem>())
    Scaffold(
        containerColor = Color.White,
        topBar = AppMediumTopBar(
            AppMediumTopBarItem(
                containerColor = Color.White.copy(.8f),
                title = { Text("Aktif Siparişlerim", style = homeSubSectionTitle) },
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
                .padding(horizontal = 10.dp)
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            activeoffers.value.forEach { o ->
                ListItem(
                    headlineContent = {
                        Column(
                            modifier = Modifier
                                .drawWithContent {
                                    drawContent()
                                    drawLine(
                                        color = AppColors.grey_153,
                                        start = Offset(size.width, 0f),
                                        end = Offset(size.width, size.height),
                                        strokeWidth = 2f
                                    )
                                }
                        ) {
                            Text(text = buildAnnotatedString {
                                withStyle(style = draftsListItemDate) { append("${o.date}\n") }
                                withStyle(style = draftsListItemTitle) { append(o.title) }
                            })
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(
                                    4.dp,
                                    Alignment.Start
                                )
                            ) {
                                repeat(4) {g->
                                    Icon(
                                        painter = painterResource(id = R.drawable.round_check_circle_24),
                                        contentDescription = null,
                                        tint =
                                        if (o.status.id > g)
                                            AppColors.green_100
                                        else
                                            AppColors.green_100.copy(.2f)
                                    )
                                }
                            }

                        }
                    },
                    leadingContent = {
                        Image(
                            painter = painterResource(id = o.image),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .requiredSize(80.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                    },
                    trailingContent = {
                        Column(
                            modifier = Modifier
                                .clickable { }
                                .requiredSize(58.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.round_content_copy_24),
                                contentDescription = null,
                                tint = Color.Black.copy(.5f)
                            )
                            Text(
                                text = "Kopya\nOluştur",
                                style = draftsListItemTrailingItemText
                            )
                        }
                    },
                    colors = ListItemDefaults.colors(
                        containerColor = Color.Transparent,
                        headlineColor = Color.Green
                    ),
                    modifier = Modifier
                        .padding(0.dp)
                        .drawWithContent {
                            drawContent()
                            drawLine(
                                color = AppColors.grey_153,
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height),
                                strokeWidth = 2f
                            )
                        }
                )
            }
        }
    }
}