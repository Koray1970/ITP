package com.isticpla.itp.feed

import androidx.compose.material3.CardColors
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors


val feeddashboardLargeItemTitle= TextStyle(
    fontFamily = poppinFamily,
    fontSize = 16.sp,
    fontWeight = FontWeight.SemiBold,
    color= AppColors.grey_dark
)
val feeddashboardLargeItemSpotText= TextStyle(
    fontFamily = poppinFamily,
    fontSize = 22.sp,
    fontWeight = FontWeight.Bold,
    color= Color.White
)
val feeddashboardLargeItemContent= TextStyle(
    fontFamily = poppinFamily,
    fontSize = 14.sp,
    fontWeight = FontWeight.Normal,
    color= AppColors.grey_dark
)
val feeddashboardItemDate= TextStyle(
    fontFamily = poppinFamily,
    fontSize = 12.sp,
    fontWeight = FontWeight.Light,
    color= AppColors.grey_156
)
val btnCreateQuoteRequestButton=TextStyle(
    fontFamily = poppinFamily,
    fontSize = 14.sp,
    fontWeight = FontWeight.SemiBold,
    textAlign=TextAlign.Center,
    color = AppColors.primaryGrey
)
val feedDashboardButtonColors= CardColors(
    containerColor=AppColors.grey_127,
    contentColor = AppColors.primaryGrey,
    disabledContainerColor = AppColors.grey_127,
    disabledContentColor = AppColors.primaryGrey,
)
//feed detail styles
val feedDetailTitle=TextStyle(
    fontFamily = poppinFamily,
    fontSize = 22.sp,
    fontWeight = FontWeight.SemiBold,
    textAlign = TextAlign.Left,
    color=AppColors.grey_dark
)
val feedDetailContent=TextStyle(
    fontFamily = poppinFamily,
    fontSize = 14.sp,
    fontWeight = FontWeight.Normal,
    textAlign = TextAlign.Left,
    color=AppColors.grey_dark
)
val feedDetailDate=TextStyle(
    fontFamily = poppinFamily,
    fontSize = 12.sp,
    fontWeight = FontWeight.Normal,
    textAlign = TextAlign.Left,
    color=AppColors.grey_dark.copy(.8f)
)
val feedDetailButtonColors= CardColors(
    containerColor=AppColors.blue_100,
    contentColor = Color.White,
    disabledContainerColor = AppColors.blue_100,
    disabledContentColor = Color.White,
)
val btnFeedDetailCreateQuoteRequest=TextStyle(
    fontFamily = poppinFamily,
    fontSize = 14.sp,
    fontWeight = FontWeight.SemiBold,
    textAlign=TextAlign.Center,
    color = Color.White
)
val btnFeedDetailDeepAnalyzerTitle=TextStyle(
    fontFamily = poppinFamily,
    fontSize = 14.sp,
    fontWeight = FontWeight.SemiBold,
    textAlign=TextAlign.Left,
    color = AppColors.blue_100
)
val btnFeedDetailDeepAnalyzerSpotText=TextStyle(
    fontFamily = poppinFamily,
    fontSize = 12.sp,
    fontWeight = FontWeight.Normal,
    textAlign=TextAlign.Left,
    color = AppColors.blue_102
)