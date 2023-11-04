package com.isticpla.itp.offers.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors


val offerGreenButtonLabelA= SpanStyle(
    fontFamily = poppinFamily,
    fontSize = 20.sp,
    fontWeight=FontWeight.Bold,
    color =AppColors.white_100
)
val offerGreenButtonLabelB= SpanStyle(
    fontFamily = poppinFamily,
    fontSize = 16.sp,
    fontWeight=FontWeight.Light,
    color =AppColors.white_100.copy(.70f)
)
val draftsListTitle=TextStyle(
    fontFamily = poppinFamily,
    fontSize = 20.sp,
    fontWeight = FontWeight.SemiBold,
    color = AppColors.primaryGrey
)
val draftsListItemDate=SpanStyle(
    fontFamily = poppinFamily,
    fontSize = 10.sp,
    fontWeight = FontWeight.SemiBold,
    color = AppColors.grey_130
)
val draftsListItemTitle=SpanStyle(
    fontFamily = poppinFamily,
    fontSize = 14.sp,
    fontWeight = FontWeight.SemiBold,
    color = AppColors.primaryGrey,
)
val draftsListItemTrailingItemText=TextStyle(
    fontFamily = poppinFamily,
    fontSize = 10.sp,
    fontWeight = FontWeight.Light,
    textAlign=TextAlign.Center,
    color = Color.Black.copy(.5f),
)