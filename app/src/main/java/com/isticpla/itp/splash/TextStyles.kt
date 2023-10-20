package com.isticpla.itp.splash

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.isticpla.itp.R
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors


fun defaultTextTitle(context: Context)= TextStyle(
    fontFamily = poppinFamily,
    fontWeight = FontWeight.Bold,
    fontSize=26.sp,
    color = AppColors.primaryGrey//Color(context.getColor(R.color.grayprimary))
)
fun defaultText (context: Context)= TextStyle(
    fontFamily = poppinFamily,
    fontWeight = FontWeight.Normal,
    fontSize=16.sp,
    color = AppColors.primaryGrey//Color(context.getColor(R.color.grayprimary))
)
