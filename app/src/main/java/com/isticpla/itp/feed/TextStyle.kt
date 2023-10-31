package com.isticpla.itp.feed

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors


val feeddashboardLargeItemTitle= TextStyle(
    fontFamily = poppinFamily,
    fontSize = 14.sp,
    fontWeight = FontWeight.SemiBold,
    color= AppColors.grey_153
)
val feeddashboardLargeItemSpotText= TextStyle(
    fontFamily = poppinFamily,
    fontSize = 22.sp,
    fontWeight = FontWeight.Bold,
    color= Color.White
)
val feeddashboardLargeItemContent= TextStyle(
    fontFamily = poppinFamily,
    fontSize = 12.sp,
    fontWeight = FontWeight.Normal,
    color= AppColors.grey_153
)
val feeddashboardItemDate= TextStyle(
    fontFamily = poppinFamily,
    fontSize = 12.sp,
    fontWeight = FontWeight.Light,
    color= AppColors.grey_156
)
val btnCreateQuoteRequestButton=TextStyle(
    fontFamily = poppinFamily,
    fontSize = 12.sp,
    fontWeight = FontWeight.SemiBold,
    color = AppColors.primaryGrey
)