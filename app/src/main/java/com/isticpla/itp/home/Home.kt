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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.isticpla.itp.Screen
import com.isticpla.itp.database.AccountViewModel
import com.isticpla.itp.uimodules.AppProgress
import com.isticpla.itp.uimodules.Bg
import com.isticpla.itp.uimodules.BottomBarMenuItem
import com.isticpla.itp.uimodules.BottomBarMenuItemType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun Home(
    navController: NavController,
) {
    val scope = rememberCoroutineScope()
    val progressState = remember { mutableStateOf(true) }
    val menuItemState = mutableListOf<BottomBarMenuItem>(
        BottomBarMenuItem(BottomBarMenuItemType.HOME, true),
        BottomBarMenuItem(BottomBarMenuItemType.BOOKMARK),
        BottomBarMenuItem(BottomBarMenuItemType.NOTIFICATION, hasbadge = true),
        BottomBarMenuItem(BottomBarMenuItemType.PROFILE),
    )

    val accountViewModel = hiltViewModel<AccountViewModel>()
    val numberofAccount =
        accountViewModel.getNumberofAccount.collectAsStateWithLifecycle(initialValue = Int)

    scope.launch {
        when (numberofAccount.value) {
            0 -> {
                navController.navigate(Screen.StartSelectCulture.route)
            }

            else -> {
                accountViewModel.isAccountCreated().collect{ g->
                    if (g.second != null)
                        println("DD: ${g.second!!}")
                    when (g.first) {
                        true -> {
                            delay(5000L)
                            progressState.value = false
                        }

                        else -> {
                            if (g.second != null)
                                navController.navigate(g.second!!)
                        }
                    }

                }


            }
        }
    }


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
    AppProgress(progressState)
}