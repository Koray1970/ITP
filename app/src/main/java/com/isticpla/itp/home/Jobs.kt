package com.isticpla.itp.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.listofNotifications
import com.isticpla.itp.dummydata.listofTasks
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.Bg
import com.isticpla.itp.uimodules.BottomBarMenuItem
import com.isticpla.itp.uimodules.BottomBarMenuItemType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Jobs(navController: NavController) {
    val menuItemState = mutableListOf<BottomBarMenuItem>(
        BottomBarMenuItem(BottomBarMenuItemType.HOME),
        BottomBarMenuItem(BottomBarMenuItemType.BOOKMARK),
        BottomBarMenuItem(BottomBarMenuItemType.NOTIFICATION, isactive = true, hasbadge = true),
        BottomBarMenuItem(BottomBarMenuItemType.PROFILE),
    )
    Scaffold(
        containerColor = Color.White,
        bottomBar = { Bg(navController, menuItemState) },
        topBar = {
            MediumTopAppBar(
                colors = TopAppBarColors(
                    containerColor = Color.White, scrolledContainerColor = Color.White,
                    navigationIconContentColor = AppColors.primaryGrey,
                    titleContentColor = AppColors.primaryGrey,
                    actionIconContentColor = AppColors.primaryGrey,
                ),
                title = { Text("Görevler", style = homeSubSectionTitle) },
                actions = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_home_24),
                            contentDescription = null
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_left),
                            contentDescription = null
                        )
                    }
                }
            )
        }) { innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .padding(horizontal = 10.dp)
        ) {
            Text("Aşağıdaki Görevleri Tamamlayınız!", style = homeSubSectionSubTitle)
            listofTasks.forEach { b ->
                ListItem(
                    colors = ListItemColors(
                        containerColor = Color.White,
                        headlineColor = AppColors.primaryGrey,
                        supportingTextColor = AppColors.grey_130,
                        leadingIconColor = AppColors.grey_130,
                        trailingIconColor = AppColors.grey_130,
                        overlineColor = AppColors.grey_130,
                        disabledHeadlineColor = AppColors.primaryGrey,
                        disabledLeadingIconColor = AppColors.grey_130,
                        disabledTrailingIconColor = AppColors.grey_130
                    ),
                    headlineContent = { Text(b.title, style = notficationListHeader) },
                    supportingContent = {
                        Text(
                            text = b.subTitle ?: "", style = notficationListSupportingtext
                        )
                    },
                    leadingContent = {
                        Text(
                            b.date, style = notficationListDate, modifier = Modifier.width(60.dp)
                        )
                    },
                )
                HorizontalDivider(thickness = 1.dp, color = AppColors.primaryGreyDisabled)
            }
            Spacer(modifier = Modifier.height(26.dp))
            Text("Daha Öncekiler", style = homeSubSectionSubTitle)
            val lastone = listofNotifications.last()
            ListItem(
                headlineContent = { Text(lastone.title, style = notficationListHeader) },
                supportingContent = {
                    Text(
                        text = lastone.subTitle ?: "", style = notficationListSupportingtext
                    )
                },
                leadingContent = { Text(lastone.date, style = notficationListDate) },
            )
            HorizontalDivider(thickness = 1.dp, color = AppColors.primaryGreyDisabled)
        }
    }
}