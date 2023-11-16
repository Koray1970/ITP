package com.isticpla.itp.uistyles

import androidx.compose.ui.graphics.Color
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

//start::panelim
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
//end::panelim
