package com.isticpla.itp.signup

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.isticpla.itp.R
import com.isticpla.itp.poppinFamily

fun signupHeader(context: Context)= TextStyle(
    fontFamily = poppinFamily,
    fontStyle=FontStyle.Normal,
    fontWeight = FontWeight.W700,
    lineHeight = 28.8.sp,
    fontSize=24.sp,
    color = Color(context.getColor(R.color.grayprimary))
)
fun signupSubTitle (context: Context)= TextStyle(
    fontFamily = poppinFamily,
    fontStyle=FontStyle.Normal,
    fontWeight = FontWeight.W400,
    fontSize=16.sp,
    color = Color(context.getColor(R.color.grayprimary))
)
fun signupCheckboxLabel (context: Context)= TextStyle(
    fontFamily = poppinFamily,
    fontStyle=FontStyle.Normal,
    fontWeight = FontWeight.W400,
    fontSize=12.sp,
    color = Color(context.getColor(R.color.grayprimary))
)
fun signupSegmentTitle(context: Context)= TextStyle(
    fontFamily = poppinFamily,
    fontStyle=FontStyle.Normal,
    fontWeight = FontWeight.W600,
    lineHeight = 22.4.sp,
    fontSize=16.sp,
    color = Color(context.getColor(R.color.neutral90))
)
fun signupPhoneComboBox(context: Context)= TextStyle(
    fontFamily = poppinFamily,
    fontStyle=FontStyle.Normal,
    fontWeight = FontWeight.W400,
    fontSize=14.sp,
    color = Color(context.getColor(R.color.neutral90))
)