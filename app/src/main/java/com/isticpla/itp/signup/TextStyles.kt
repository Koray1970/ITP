package com.isticpla.itp.signup

import android.content.Context
import androidx.compose.material3.CheckboxColors
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.isticpla.itp.R
import com.isticpla.itp.poppinFamily

fun signupHeader(context: Context)= TextStyle(
    fontFamily = poppinFamily,
    fontStyle=FontStyle.Normal,
    fontWeight = FontWeight.Bold,
    lineHeight = 1.2.em,
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
fun signupSubmitButton (context: Context)= TextStyle(
    fontFamily = poppinFamily,
    fontStyle=FontStyle.Normal,
    fontWeight = FontWeight.W400,
    fontSize=16.sp,
    color = Color.White
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
)fun signupBlueText(context: Context)= TextStyle(
    fontFamily = poppinFamily,
    fontStyle=FontStyle.Normal,
    fontWeight = FontWeight.Bold,
    fontSize=15.sp,
    color = Color(context.getColor(R.color.blueff0495f1))
)
fun validatePhoneTextFieldTextStyle(context: Context)=TextStyle(
    textAlign = TextAlign.Center,
    fontSize = 22.sp,
    fontWeight = FontWeight.Bold
)
fun signCheckBoxColors(context:Context):CheckboxColors = CheckboxColors(
    checkedCheckmarkColor = Color.White,
    uncheckedCheckmarkColor = Color.Transparent,
    checkedBoxColor = Color(context.getColor(R.color.blueff1c84ff)),
    uncheckedBoxColor = Color.Transparent,
    disabledCheckedBoxColor = Color(context.getColor(R.color.grayffc1c1c1)),
    disabledUncheckedBoxColor = Color.Transparent,
    disabledIndeterminateBoxColor = Color(context.getColor(R.color.grayffc1c1c1)),
    checkedBorderColor = Color(context.getColor(R.color.blueff1c84ff)),
    uncheckedBorderColor = Color(context.getColor(R.color.grayff8391a1)),
    disabledBorderColor = Color(context.getColor(R.color.grayffc1c1c1)),
    disabledUncheckedBorderColor = Color(context.getColor(R.color.grayffc1c1c1)),
    disabledIndeterminateBorderColor = Color(context.getColor(R.color.grayffc1c1c1))
)