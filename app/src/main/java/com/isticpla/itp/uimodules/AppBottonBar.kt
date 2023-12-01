package com.isticpla.itp.uimodules

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.Screen

enum class BottomBarMenuItemType(value: Int) {
    HOME(0),
    BOOKMARK(1),
    NOTIFICATION(2),
    PROFILE(3)
}

data class BottomBarMenuItem(
    val type: BottomBarMenuItemType,
    val isactive: Boolean = false,
    val hasbadge: Boolean = false,
)

val defaultmenuItemState = mutableListOf<BottomBarMenuItem>(
    BottomBarMenuItem(BottomBarMenuItemType.HOME),
    BottomBarMenuItem(BottomBarMenuItemType.BOOKMARK),
    BottomBarMenuItem(BottomBarMenuItemType.NOTIFICATION, hasbadge = true),
    BottomBarMenuItem(BottomBarMenuItemType.PROFILE),
)

@Composable
fun Bg(navController: NavController, activeMenuList: MutableList<BottomBarMenuItem>) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        .paint(
            painterResource(id = R.mipmap.beyazbg),
            contentScale = ContentScale.Crop
        )
) {
    Row(
        modifier = Modifier
            .padding(top = 28.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(top = 28.dp)
                //.background(Color.Red.copy(.5f))
                .weight(.33f),
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.End),
            verticalAlignment = Alignment.Top
        ) {
            val homeState = activeMenuList.first { a -> a.type == BottomBarMenuItemType.HOME }
            IconButton(
                onClick = {
                    if (!navController.currentDestination?.route?.contains("home")!!)
                        navController.navigate(Screen.Home.route)
                }
            ) {
                Image(
                    painter = painterResource(id = if (navController.currentDestination?.route.contentEquals("home")) R.drawable.menu_a_home  else R.drawable.menu_i_home),
                    modifier = Modifier
                        .drawBehind {
                            if (homeState.hasbadge) {
                                drawCircle(
                                    color = AppColors.red_100,
                                    radius = 4f,
                                    center = Offset(size.width / 2, size.height + 15)
                                )
                            }
                        },
                    contentDescription = null,
                    //tint = if (!homeState.isactive) AppColors.grey_118 else Color.Unspecified
                )
            }
            val bookmarkState =
                activeMenuList.first { a -> a.type == BottomBarMenuItemType.BOOKMARK }
            IconButton(
                onClick = {
                    if (navController.currentDestination?.route != "mypanel")
                        navController.navigate(Screen.MyPanel.route)
                }
            ) {
                Image(
                    painter = painterResource(id = if (navController.currentDestination?.route.contentEquals("mypanel")) R.drawable.menu_a_bookmark else R.drawable.menu_i_bookmark),
                    modifier = Modifier
                        .drawBehind {
                            if (bookmarkState.hasbadge)
                                drawCircle(
                                    color = AppColors.red_100,
                                    radius = 4f,
                                    center = Offset(size.width / 2, size.height + 15)
                                )
                        },
                    contentDescription = null,
                    //tint = if (!bookmarkState.isactive) AppColors.grey_118 else Color.Unspecified
                )
            }
        }
        Row(
            modifier = Modifier
                //.background(Color.Yellow.copy(.5f))
                .weight(.33f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.menu_red_add),
                modifier = Modifier
                    .requiredSize(67.dp)
                    .clickable {
                        if (!navController.currentDestination?.route?.contains("offer/dashboard")!!)
                            navController.navigate(Screen.CreateOfferDashboard.route)
                    },
                contentDescription = null
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 28.dp)
                //.background(Color.Red.copy(.5f))
                .weight(.33f),
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
            verticalAlignment = Alignment.Top
        ) {
            val notificationState =
                activeMenuList.first { a -> a.type == BottomBarMenuItemType.NOTIFICATION }
            IconButton(
                onClick = {
                    if (!navController.currentDestination?.route?.contains("home/notifications")!!)
                        navController.navigate(Screen.Notifications.route)
                }
            ) {
                Image(
                    painter = painterResource(id = if (navController.currentDestination?.route.contentEquals("home/notifications")) R.drawable.menu_a_notification else R.drawable.menu_i_notification),
                    modifier = Modifier
                        .drawBehind {
                            if (notificationState.hasbadge)
                                drawCircle(
                                    color = AppColors.red_100,
                                    radius = 4f,
                                    center = Offset(size.width / 2, size.height + 15)
                                )
                        },
                    contentDescription = null,
                    //tint = if (!notificationState.isactive) AppColors.grey_118 else Color.Unspecified
                )
            }
            val profileState =
                activeMenuList.first { a -> a.type == BottomBarMenuItemType.PROFILE }
            IconButton(
                onClick = { navController.navigate(Screen.ProfileDashboard.route) }
            ) {
                Image(
                    painter = painterResource(id = if (navController.currentDestination?.route.contentEquals("profile/dashboard")) R.drawable.menu_a_profile else R.drawable.menu_i_profile),
                    modifier = Modifier
                        .drawBehind {
                            if (profileState.hasbadge) {
                                drawCircle(
                                    color = AppColors.red_100,
                                    radius = 4f,
                                    center = Offset(size.width / 2, size.height + 15)
                                )
                            }
                        },
                    contentDescription = null,
                    //tint = if (!profileState.isactive) AppColors.grey_118 else Color.Unspecified
                )
            }
        }
    }
}