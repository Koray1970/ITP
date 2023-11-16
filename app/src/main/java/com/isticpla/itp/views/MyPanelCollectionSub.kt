package com.isticpla.itp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.isticpla.itp.dummydata.PanelCollectionItem
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.home.homeSubSectionTitle
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.Bg
import com.isticpla.itp.uimodules.BottomBarMenuItem
import com.isticpla.itp.uimodules.BottomBarMenuItemType
import com.isticpla.itp.uistyles.listitemHeaderLineContent
import com.isticpla.itp.uistyles.listitemTitle
import com.isticpla.itp.views.helpers.AppMediumTopBar
import com.isticpla.itp.views.helpers.AppMediumTopBarItem
import com.isticpla.itp.views.helpers.TopBarActionItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPanelCollectionSub(
    navController: NavController,
) {
    val roundcornerRectangel = RoundedCornerShape(10.dp)
    val homeViewModel = hiltViewModel<HomeViewMode>()

    val menuItemState = mutableListOf<BottomBarMenuItem>(
        BottomBarMenuItem(BottomBarMenuItemType.HOME, true),
        BottomBarMenuItem(BottomBarMenuItemType.BOOKMARK),
        BottomBarMenuItem(BottomBarMenuItemType.NOTIFICATION, hasbadge = true),
        BottomBarMenuItem(BottomBarMenuItemType.PROFILE),
    )


    val collections =
        homeViewModel.panelCollectionSub.collectAsStateWithLifecycle(initialValue = emptyList<PanelCollectionItem>())

    Scaffold(
        containerColor = Color.White,
        topBar = AppMediumTopBar(
            AppMediumTopBarItem(
                containerColor = Color.White.copy(.8f),
                title = {
                    Text(text = "Kolleksiyonlarım", style = homeSubSectionTitle)
                },
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
            PanelSectionTitle(
                title = "Çay Bardakları",
                numberofitems = collections.value.size,
                showallNav = {},
                showallVisible = false
            )
            collections.value.forEach { ac ->
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
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(80.dp)
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
        }
    }
}