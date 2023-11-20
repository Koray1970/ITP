package com.isticpla.itp.signup

import android.content.Context
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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

val signupHeader = TextStyle(
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

val signupSubTitle = TextStyle(
    fontFamily = poppinFamily,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight.W400,
    fontSize = 16.sp,
    color = AppColors.primaryGrey//Color(context.getColor(R.color.gray99))
)

val signupSubmitButton = TextStyle(
    fontFamily = poppinFamily,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight.W400,
    fontSize = 16.sp,
    color = Color.White
)

val signupCheckboxLabel = TextStyle(
    fontFamily = poppinFamily,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight.W400,
    fontSize = 12.sp,
    color = AppColors.primaryGrey//Color(context.getColor(R.color.gray99))
)

val signupSegmentTitle = TextStyle(
    fontFamily = poppinFamily,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight.W600,
    lineHeight = 22.4.sp,
    fontSize = 16.sp,
    color = AppColors.grey_124//Color(context.getColor(R.color.neutral90))
)
val signupFooterContent = TextStyle(
    fontFamily = poppinFamily,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight.Normal,
    lineHeight = 22.4.sp,
    fontSize = 12.sp,
    color = AppColors.grey_0xFF8391A1
)

val signupBlueText = TextStyle(
    fontFamily = poppinFamily,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight.Bold,
    fontSize = 15.sp,
    color = AppColors.blue_104//Color(context.getColor(R.color.blue002))
)

val validatePhoneTextFieldTextStyle = TextStyle(
    fontFamily = poppinFamily,
    textAlign = TextAlign.Center,
    fontSize = 22.sp,
    fontWeight = FontWeight.Bold
)

@Composable
fun signCheckBoxColors(
    isrequired: MutableState<Boolean> = mutableStateOf(false)
) =
    CheckboxDefaults.colors(
        checkedColor = AppColors.blue_103,
        uncheckedColor = if (!isrequired.value) AppColors.blue_103 else AppColors.red_0xffe23e3e,
        checkmarkColor = Color.White,
        disabledCheckedColor = AppColors.blue_103.copy(.5f),
        disabledUncheckedColor = AppColors.red_0xffe23e3e.copy(.5f),
        disabledIndeterminateColor = AppColors.grey_118.copy(.5f)
    )