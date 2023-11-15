package com.isticpla.itp.profile

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uistyles.topmenuTitle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProfileTopBar(
    navController: NavController,
    title: String,
    navigationulr: String,
    actionurl: String,
) =
    MediumTopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.White, scrolledContainerColor = Color.White,
            navigationIconContentColor = AppColors.primaryGrey,
            titleContentColor = AppColors.primaryGrey,
            actionIconContentColor = AppColors.primaryGrey,
        ),
        title = { Text(title, style = topmenuTitle) },
        actions = {
            IconButton(onClick = { navController.navigate(actionurl) }) {
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