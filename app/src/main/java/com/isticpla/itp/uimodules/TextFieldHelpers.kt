package com.isticpla.itp.uimodules

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.TextFieldColors
import androidx.compose.ui.graphics.Color

data class TextFieldColorVariants(
    var isdefault: Boolean = false,
    var unfocus: Color,
    var focused: Color,
    var disabled: Color,
    var error: Color,
)


data class DropDownTextFieldColorRequest(
    var cursor: Color,
    var cursorError: Color,
    var textSelectionHandle: Color,
    var textSelectionBackground: Color,
    var container: TextFieldColorVariants,
    var label: TextFieldColorVariants,
    var text: TextFieldColorVariants,
    var indicatior: TextFieldColorVariants,
    var trailing: TextFieldColorVariants,
    var leading: TextFieldColorVariants,
    var placeholder: TextFieldColorVariants,
    var suffix: TextFieldColorVariants,
    var supportingText: TextFieldColorVariants,
    var prefix: TextFieldColorVariants,
)

fun defaultTextFieldColor(request: DropDownTextFieldColorRequest?, isDefault: Boolean) =
    TextFieldColors(
        unfocusedContainerColor = if (isDefault) Color.White else request!!.container.unfocus,
        focusedContainerColor = if (isDefault) Color.White else request!!.container.focused,
        disabledContainerColor = if (isDefault) Color.White else request!!.container.disabled,
        errorContainerColor = if (isDefault) Color.White else request!!.container.error,

        focusedTextColor = if (isDefault) AppColors.primaryGrey else request!!.text.focused,
        unfocusedTextColor = if (isDefault) AppColors.primaryGrey else request!!.text.unfocus,
        disabledTextColor = if (isDefault) AppColors.primaryGrey else request!!.text.disabled,
        errorTextColor = if (isDefault) AppColors.primaryGrey else request!!.text.error,

        cursorColor = if (isDefault) AppColors.primaryGrey else request!!.cursor,
        errorCursorColor = if (isDefault) AppColors.primaryGrey else request!!.cursorError,
        textSelectionColors = TextSelectionColors(
            handleColor = if (isDefault) AppColors.primaryGrey else request!!.textSelectionHandle,
            backgroundColor = if (isDefault) AppColors.primaryGrey else request!!.textSelectionBackground
        ),

        focusedTrailingIconColor = if (isDefault) AppColors.grey_130 else request!!.trailing.focused,
        unfocusedTrailingIconColor = if (isDefault) AppColors.grey_130 else request!!.trailing.unfocus,
        disabledTrailingIconColor = if (isDefault) AppColors.grey_130 else request!!.trailing.disabled,
        errorTrailingIconColor = if (isDefault) AppColors.grey_130 else request!!.trailing.error,

        focusedIndicatorColor = if (isDefault) Color.Transparent else request!!.indicatior.focused,
        unfocusedIndicatorColor = if (isDefault) Color.Transparent else request!!.indicatior.unfocus,
        disabledIndicatorColor = if (isDefault) Color.Transparent else request!!.indicatior.disabled,
        errorIndicatorColor = if (isDefault) Color.Transparent else request!!.indicatior.error,

        focusedLeadingIconColor = if (isDefault) AppColors.grey_130 else request!!.leading.focused,
        unfocusedLeadingIconColor = if (isDefault) AppColors.grey_130 else request!!.leading.unfocus,
        disabledLeadingIconColor = if (isDefault) AppColors.grey_130 else request!!.leading.disabled,
        errorLeadingIconColor = if (isDefault) AppColors.grey_130 else request!!.leading.error,

        focusedLabelColor = if (isDefault) AppColors.grey_118 else request!!.label.focused,
        unfocusedLabelColor = if (isDefault) AppColors.grey_118 else request!!.label.unfocus,
        disabledLabelColor = if (isDefault) AppColors.grey_118 else request!!.label.disabled,
        errorLabelColor = if (isDefault) AppColors.grey_118 else request!!.label.error,

        focusedPlaceholderColor = if (isDefault) AppColors.grey_118 else request!!.placeholder.focused,
        unfocusedPlaceholderColor = if (isDefault) AppColors.grey_118 else request!!.placeholder.unfocus,
        disabledPlaceholderColor = if (isDefault) AppColors.grey_118 else request!!.placeholder.disabled,
        errorPlaceholderColor = if (isDefault) AppColors.grey_118 else request!!.placeholder.error,

        focusedSupportingTextColor = if (isDefault) AppColors.grey_118 else request!!.supportingText.focused,
        unfocusedSupportingTextColor = if (isDefault) AppColors.grey_118 else request!!.supportingText.unfocus,
        disabledSupportingTextColor = if (isDefault) AppColors.grey_118 else request!!.supportingText.disabled,
        errorSupportingTextColor = if (isDefault) AppColors.grey_118 else request!!.supportingText.error,

        focusedPrefixColor = if (isDefault) AppColors.grey_118 else request!!.prefix.focused,
        unfocusedPrefixColor = if (isDefault) AppColors.grey_118 else request!!.prefix.unfocus,
        disabledPrefixColor = if (isDefault) AppColors.grey_118 else request!!.prefix.disabled,
        errorPrefixColor = if (isDefault) AppColors.grey_118 else request!!.prefix.error,

        focusedSuffixColor = if (isDefault) AppColors.grey_118 else request!!.suffix.focused,
        unfocusedSuffixColor = if (isDefault) AppColors.grey_118 else request!!.suffix.unfocus,
        disabledSuffixColor = if (isDefault) AppColors.grey_118 else request!!.suffix.disabled,
        errorSuffixColor = if (isDefault) AppColors.grey_118 else request!!.suffix.error,
    )