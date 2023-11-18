package com.isticpla.itp.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.uimodules.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(navController: NavController) =
    TopAppBar(title = { Text("") }, navigationIcon = {
        Icon(
            painter = painterResource(id = R.drawable.home_logo),
            contentDescription = null,
            tint = AppColors.grey_138
        )
    }, actions = {
        IconButton(onClick = { navController.navigate("home/messages") }) {
            Icon(
                painter = painterResource(id = R.drawable.ico_messages),
                contentDescription = null
            )
        }
        IconButton(onClick = { navController.navigate("home/jobs") }) {
            Icon(painter = painterResource(id = R.drawable.ico_task), contentDescription = null)
        }
        IconButton(onClick = { navController.navigate("home/notifications") }) {
            Icon(
                painter = painterResource(id = R.drawable.ico_notifications),
                contentDescription = null
            )
        }
    }, colors = TopAppBarDefaults.topAppBarColors(
        containerColor = Color.White,
        navigationIconContentColor = AppColors.grey_138,
        actionIconContentColor = AppColors.grey_138
    )
    )