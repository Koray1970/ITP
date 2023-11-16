package com.isticpla.itp.views.helpers

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.home.homeSubSectionTitle
import com.isticpla.itp.uimodules.AppColors
import dagger.hilt.android.HiltAndroidApp

data class TopBarActionItem(
    val url: (() -> Unit)? = null,
    val icon: Int,
    val tint: Color = Color.Unspecified
)

data class AppMediumTopBarItem(
    val title: @Composable () -> Unit = { Text(text = "", style = TextStyle.Default) },
    val actions: MutableList<TopBarActionItem> = emptyList<TopBarActionItem>().toMutableList(),
    val navigationIcon: TopBarActionItem? = TopBarActionItem(icon = R.drawable.arrow_left)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppMediumTopBar(topBarItem: AppMediumTopBarItem) = @Composable {
    MediumTopAppBar(
        colors = TopAppBarColors(
            containerColor = Color.Transparent, scrolledContainerColor = Color.Transparent,
            navigationIconContentColor = AppColors.primaryGrey,
            titleContentColor = AppColors.primaryGrey,
            actionIconContentColor = AppColors.primaryGrey,
        ),
        title = topBarItem.title,
        actions = {
            if (topBarItem.actions.isNotEmpty()) {
                topBarItem.actions.forEach { t ->
                    if (t.url != null) {
                        IconButton(onClick = t.url!!) {
                            Icon(
                                painter = painterResource(id = t.icon),
                                contentDescription = null, tint = t.tint
                            )
                        }
                    } else {
                        Icon(
                            painter = painterResource(id = t.icon),
                            contentDescription = null, tint = t.tint
                        )
                    }
                }
            }
        },
        navigationIcon = {
            if (topBarItem.navigationIcon != null)
                if (topBarItem.navigationIcon.url != null) {
                    IconButton(onClick = topBarItem.navigationIcon.url) {
                        Icon(
                            painter = painterResource(id = topBarItem.navigationIcon.icon),
                            contentDescription = null, tint = topBarItem.navigationIcon.tint
                        )
                    }
                } else {
                    Icon(
                        painter = painterResource(id = topBarItem.navigationIcon.icon),
                        contentDescription = null, tint = topBarItem.navigationIcon.tint
                    )
                }
        }
    )
}
