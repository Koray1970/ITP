package com.isticpla.itp.uistyles

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors

val topmenuTitle=TextStyle(
    fontFamily = poppinFamily,
    fontSize = 20.sp,
    fontWeight = FontWeight.SemiBold,
    color= Color.Black
)

//region Panelim
val sectionTitle=TextStyle(
    fontFamily = poppinFamily,
    fontSize = 20.sp,
    fontWeight = FontWeight.SemiBold,
    color= AppColors.grey_dark
)
val sectionBadge=TextStyle(
    fontFamily = poppinFamily,
    fontSize = 12.sp,
    fontWeight = FontWeight.SemiBold,
    textAlign=TextAlign.Center,
    color= Color.White
)
val listitemHeaderLineContent=TextStyle(
    fontFamily = poppinFamily,
    fontSize = 10.sp,
    lineHeight=16.sp,
    fontWeight = FontWeight.Normal,
    textAlign=TextAlign.Left,
    color=AppColors.grey_171
)
val listitemTitle=SpanStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.SemiBold,
    color=AppColors.grey_124
)
val listitemCompleteTitle=SpanStyle(
    fontSize = 12.sp,
    fontWeight = FontWeight.SemiBold,
    color=AppColors.grey_124
)

//endregion
