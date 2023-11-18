package com.isticpla.itp.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.isticpla.itp.uimodules.Bg
import com.isticpla.itp.uimodules.BottomBarMenuItem
import com.isticpla.itp.uimodules.BottomBarMenuItemType

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun Home(
    navController: NavController,
) {
    val homeViewMode = hiltViewModel<HomeViewMode>()
    val menuItemState = mutableListOf<BottomBarMenuItem>(
        BottomBarMenuItem(BottomBarMenuItemType.HOME, true),
        BottomBarMenuItem(BottomBarMenuItemType.BOOKMARK),
        BottomBarMenuItem(BottomBarMenuItemType.NOTIFICATION, hasbadge = true),
        BottomBarMenuItem(BottomBarMenuItemType.PROFILE),
    )

    Scaffold(modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = { HomeTopBar(navController) },
        bottomBar = { Bg(navController, menuItemState) }) { innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerpadding)
                .padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeSectionHeader()
            HomeSectionSectors(navController)
            HomeSectionDesigns(navController)
            HomeSectionCampaigns(navController)
            HomeSectionInStockSales(navController)
            HomeSectionSectorNews(navController)
        }
    }
}