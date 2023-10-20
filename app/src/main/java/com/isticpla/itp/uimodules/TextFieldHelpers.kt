package com.isticpla.itp.uimodules

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.TextFieldColors
import androidx.compose.ui.graphics.Color
import com.isticpla.itp.R

fun defaultTextFieldColor(containerColor:Color, textColor: Color) = TextFieldColors(
    unfocusedContainerColor =containerColor,
    focusedContainerColor = containerColor,
    focusedTextColor = textColor,
    unfocusedTextColor = textColor,
    disabledTextColor = textColor,
    errorTextColor = Color.Red,
    disabledContainerColor = containerColor,
    errorContainerColor = containerColor,
    cursorColor = Color.DarkGray,
    errorCursorColor = Color.DarkGray,
    textSelectionColors = TextSelectionColors(
        handleColor = AppColors.blue_104,
        backgroundColor = AppColors.blue_104
    ),
    focusedIndicatorColor = Color.White,
    unfocusedIndicatorColor = Color.White,
    disabledIndicatorColor = Color.White,
    errorIndicatorColor = Color.White,
    focusedLeadingIconColor = Color.White,
    unfocusedLeadingIconColor = Color.White,
    disabledLeadingIconColor = Color.White,
    errorLeadingIconColor = Color.White,
    focusedTrailingIconColor = Color.White,
    unfocusedTrailingIconColor = Color.White,
    disabledTrailingIconColor = Color.White,
    errorTrailingIconColor = Color.White,
    focusedLabelColor = Color.Gray,
    unfocusedLabelColor = Color.Gray,
    disabledLabelColor = Color.Gray,
    errorLabelColor = Color.White,
    focusedPlaceholderColor = Color.White,
    unfocusedPlaceholderColor = Color.White,
    disabledPlaceholderColor = Color.White,
    errorPlaceholderColor = Color.White,
    focusedSupportingTextColor = Color.White,
    unfocusedSupportingTextColor = Color.White,
    disabledSupportingTextColor = Color.White,
    errorSupportingTextColor = Color.White,
    focusedPrefixColor = Color.White,
    unfocusedPrefixColor = Color.White,
    disabledPrefixColor = Color.White,
    errorPrefixColor = Color.White,
    focusedSuffixColor = Color.White,
    unfocusedSuffixColor = Color.White,
    disabledSuffixColor = Color.White,
    errorSuffixColor = Color.White,
)