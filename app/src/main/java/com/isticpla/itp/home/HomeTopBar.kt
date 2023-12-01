package com.isticpla.itp.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.Screen
import com.isticpla.itp.uimodules.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(navController: NavController) =
    TopAppBar(
        modifier = Modifier.padding(start = 10.dp),
        title = { Text("") },
        navigationIcon = {
                Image(
                    painter = painterResource(id = R.drawable.home_logo),
                    contentDescription = null,
                    modifier=Modifier
                        .clickable {
                            navController.navigate(Screen.Home.route)
                        }
                        .size(110.dp)
                )
        },
        actions = {
            Row(
                modifier = Modifier.padding(horizontal = 0.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = { navController.navigate(Screen.Messages.route) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ico_messages),
                        contentDescription = null
                    )
                }
                IconButton(onClick = { navController.navigate(Screen.Jobs.route) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ico_task),
                        contentDescription = null
                    )
                }
                IconButton(onClick = { navController.navigate(Screen.Notifications.route) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ico_notifications),
                        contentDescription = null
                    )
                }
            }
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            navigationIconContentColor = AppColors.grey_138,
            actionIconContentColor = AppColors.grey_138
        )
    )