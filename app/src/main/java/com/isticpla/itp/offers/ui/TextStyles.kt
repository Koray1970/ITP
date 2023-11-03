package com.isticpla.itp.offers.ui

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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