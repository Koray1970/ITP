package com.isticpla.itp.home

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors

val homeSectionTitle= TextStyle(
    fontFamily = poppinFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
    color = AppColors.primaryGrey,
    lineHeight = 7.em
)
val homeSectorLabel= TextStyle(
    fontFamily = poppinFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 12.sp,
    color = AppColors.primaryGrey,
)