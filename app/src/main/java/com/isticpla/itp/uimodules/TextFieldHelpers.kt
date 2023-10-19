package com.isticpla.itp.uimodules

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.TextFieldColors
import androidx.compose.ui.graphics.Color
import com.isticpla.itp.R

fun defaultTextFieldColor(textColor:Int) = TextFieldColors(
    unfocusedContainerColor = Color.White,
    focusedContainerColor = Color.White,
    focusedTextColor = Color(textColor),
    unfocusedTextColor = Color(textColor),
    disabledTextColor = Color(textColor),
    errorTextColor = Color.Red,
    disabledContainerColor = Color.White,
    errorContainerColor = Color.White,
    cursorColor = Color.DarkGray,
    errorCursorColor = Color.DarkGray,
    textSelectionColors = TextSelectionColors(handleColor=Color.White,backgroundColor=Color.White),
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