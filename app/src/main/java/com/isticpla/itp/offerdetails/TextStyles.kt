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
    textAlign = TextAlign.Center,
    color = AppColors.secondaryGrey,
)
val offerdetailTrackingCodeA = SpanStyle(
    fontWeight = FontWeight.Normal,
)
val offerdetailTrackingCodeB = SpanStyle(
    fontWeight = FontWeight.Bold,
)
val offerdetailTabLabel= TextStyle(
    fontFamily = poppinFamily,
    fontSize = 12.sp,
    fontWeight=FontWeight.Normal,
    textAlign = TextAlign.Center,
)
val offerdetailTabLabelSelected= TextStyle(
    fontFamily = poppinFamily,
    fontSize = 11.sp,
    fontWeight=FontWeight.Bold,
    textAlign = TextAlign.Center,
)
val offerdetailTrackText= TextStyle(
    fontFamily = poppinFamily,
    fontSize = 12.sp,
)
val offerdetailTrackLabel= SpanStyle(
    fontWeight=FontWeight.Normal,
    color=AppColors.grey_174
)
val offerdetailTrackDate= SpanStyle(
    fontWeight=FontWeight.Normal,
    color=AppColors.grey_174.copy(.3f)
)

val offerdetailTrackButton= TextStyle(
    fontFamily = poppinFamily,
    fontSize = 12.sp,
    textAlign = TextAlign.Center
)

val offerdetailTrackButtonLabel= SpanStyle(
    fontWeight=FontWeight.Normal,
)
val offerdetailTrackButtonDate= SpanStyle(
    fontWeight=FontWeight.SemiBold,
)

val offerdetailOffersTextStyle= TextStyle(
    fontFamily = poppinFamily,
    fontSize = 12.sp,
    lineHeight=12.sp,
    textAlign = TextAlign.Left
)
val offerdetailOffersTitle= SpanStyle(
    fontWeight=FontWeight.SemiBold,
    color=AppColors.grey_124
)
val offerdetailOffersSubText= SpanStyle(
    fontWeight=FontWeight.Normal,
    fontSize = 10.sp,
    color=AppColors.grey_171
)
val offerdetailOffersPriceTextStyle= TextStyle(
    fontFamily = poppinFamily,
    fontSize = 12.sp,
    lineHeight=12.sp,
    textAlign = TextAlign.Right
)

val offerdetailChatTextStyle= TextStyle(
    fontFamily = poppinFamily,
    fontSize = 12.sp,
    lineHeight=12.sp,
    textAlign = TextAlign.Left
)
val offerdetailChatPersonName= SpanStyle(
    fontWeight=FontWeight.SemiBold,
    color=AppColors.grey_124
)
val offerdetailChatMessage= SpanStyle(
    fontWeight=FontWeight.Medium,
    fontSize=10.sp,
    color=AppColors.grey_171
)