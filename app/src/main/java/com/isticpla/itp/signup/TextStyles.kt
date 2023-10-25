package com.isticpla.itp.signup

import android.content.Context
import androidx.compose.material3.CheckboxColors
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.isticpla.itp.R
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors

fun signupHeader(context: Context) = TextStyle(
    fontFamily = poppinFamily,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight.Bold,
    lineHeight = 1.2.em,
    fontSize = 24.sp,
    color = AppColors.primaryGrey//Color(context.getColor(R.color.gray99))
)

val signupHeaderSpanStyle = SpanStyle(
    fontFamily = poppinFamily,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight.Bold,
    fontSize = 24.sp,
    color = AppColors.primaryGrey
)

fun signupSubTitle(context: Context) = TextStyle(
    fontFamily = poppinFamily,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight.W400,
    fontSize = 16.sp,
    color = AppColors.primaryGrey//Color(context.getColor(R.color.gray99))
)

fun signupSubmitButton(context: Context) = TextStyle(
    fontFamily = poppinFamily,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight.W400,
    fontSize = 16.sp,
    color = Color.White
)

fun signupCheckboxLabel(context: Context) = TextStyle(
    fontFamily = poppinFamily,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight.W400,
    fontSize = 12.sp,
    color = AppColors.primaryGrey//Color(context.getColor(R.color.gray99))
)

fun signupSegmentTitle(context: Context) = TextStyle(
    fontFamily = poppinFamily,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight.W600,
    lineHeight = 22.4.sp,
    fontSize = 16.sp,
    color = AppColors.grey_124//Color(context.getColor(R.color.neutral90))
)

fun signupBlueText(context: Context) = TextStyle(
    fontFamily = poppinFamily,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight.Bold,
    fontSize = 15.sp,
    color = AppColors.blue_104//Color(context.getColor(R.color.blue002))
)

fun validatePhoneTextFieldTextStyle(context: Context) = TextStyle(
    fontFamily = poppinFamily,
    textAlign = TextAlign.Center,
    fontSize = 22.sp,
    fontWeight = FontWeight.Bold
)

fun signCheckBoxColors(context: Context): CheckboxColors = CheckboxColors(
    checkedCheckmarkColor = Color.White,
    uncheckedCheckmarkColor = Color.Transparent,
    checkedBoxColor = AppColors.blue_103,//Color(context.getColor(R.color.blue001))
    uncheckedBoxColor = Color.Transparent,
    disabledCheckedBoxColor = AppColors.grey_118,//Color(context.getColor(R.color.gray105))
    disabledUncheckedBoxColor = Color.Transparent,
    disabledIndeterminateBoxColor = AppColors.grey_118,//Color(context.getColor(R.color.gray105))
    checkedBorderColor = AppColors.blue_103,//Color(context.getColor(R.color.blue001))
    uncheckedBorderColor = AppColors.grey_121,//Color(context.getColor(R.color.gray100))
    disabledBorderColor = AppColors.grey_118,//Color(context.getColor(R.color.gray105))
    disabledUncheckedBorderColor = AppColors.grey_118,// Color(context.getColor(R.color.gray105))
    disabledIndeterminateBorderColor = AppColors.grey_118//Color(context.getColor(R.color.gray105))
)