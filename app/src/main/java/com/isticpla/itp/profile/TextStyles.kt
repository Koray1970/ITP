package com.isticpla.itp.profile

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors


val profileName= TextStyle(
    fontFamily = poppinFamily,
    fontSize = 24.sp,
    fontWeight = FontWeight.SemiBold,
    color = Color.Black
)
val profileTitleLocation= TextStyle(
    fontFamily = poppinFamily,
    fontSize = 12.sp,
    fontWeight = FontWeight.Normal,
    color = AppColors.grey_177
)
val profileInfoThreeCol=TextStyle(
    fontFamily = poppinFamily,
    textAlign = TextAlign.Center
)

val profileInfoThreeColHeader= SpanStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.SemiBold,
    color = Color.Black
)
val profileInfoThreeColValue= SpanStyle(
    fontSize = 12.sp,
    fontWeight = FontWeight.Normal,
    color = AppColors.grey_177
)

val profileMenuItemLabel=TextStyle(
    fontFamily = poppinFamily,
    fontSize=14.sp,
    fontWeight=FontWeight.SemiBold,
    textAlign = TextAlign.Left,
    color=AppColors.grey_186
)
val profileMenuItemExitLabel=TextStyle(
    fontFamily = poppinFamily,
    fontSize=14.sp,
    fontWeight=FontWeight.SemiBold,
    textAlign = TextAlign.Left,
    color=AppColors.red_100
)