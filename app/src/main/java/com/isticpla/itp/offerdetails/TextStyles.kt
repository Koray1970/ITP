package com.isticpla.itp.offerdetails

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors

val offerdetailHeader = TextStyle(
    fontFamily = poppinFamily,
    fontSize = 24.sp,
    lineHeight=30.sp,
    fontWeight = FontWeight.SemiBold,
    textAlign = TextAlign.Center,
    color = AppColors.primaryGrey,
)
val offerdetailTrackingCode= TextStyle(
    fontFamily = poppinFamily,
    fontSize = 14.sp,
    lineHeight=14.sp,
    textAlign = TextAlign.Center,
    color = AppColors.secondaryGrey,
)
val offerdetailTrackingCodeA = SpanStyle(
    fontWeight = FontWeight.Normal,
)
val offerdetailTrackingCodeB = SpanStyle(
    fontWeight = FontWeight.Bold,
)