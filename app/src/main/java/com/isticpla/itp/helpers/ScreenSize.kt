package com.isticpla.itp.helpers

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

data class ScreenSizeItem(val width: Int, val height: Int)

@Composable
fun GetScreenSize(): ScreenSizeItem {
    val configuration = LocalConfiguration.current
    return ScreenSizeItem(configuration.screenWidthDp, configuration.screenHeightDp)
}