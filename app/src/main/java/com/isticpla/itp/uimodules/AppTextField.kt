package com.isticpla.itp.uimodules

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class AppTextFieldDefaults() {
    companion object {
        //default radius=10
        @Composable
        fun RoundCornderShape(radius: Int = 10) = RoundedCornerShape(radius)

        @Composable
        fun TextFieldDefaultModifier(
            borderWidth: Dp = 1.dp,
            strokeColor: Color = AppColors.secondaryGrey
        ) = Modifier
            .border(width = borderWidth, color = strokeColor, shape = RoundedCornerShape(10))

        @Composable
        fun TextFieldDefaultsColors() = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedTextColor =AppColors.primaryGrey,
            unfocusedTextColor = AppColors.primaryGrey,
            focusedLabelColor = AppColors.grey_118,
            unfocusedLabelColor = AppColors.primaryGrey,
            focusedSuffixColor =AppColors.grey_118,
            unfocusedSuffixColor = AppColors.primaryGrey,
            focusedPrefixColor = AppColors.grey_118,
            unfocusedPrefixColor = AppColors.primaryGrey,
            focusedLeadingIconColor =AppColors.secondaryGrey,
            unfocusedLeadingIconColor = AppColors.secondaryGrey,
            focusedPlaceholderColor = AppColors.grey_118,
            unfocusedPlaceholderColor = AppColors.primaryGrey,
            focusedTrailingIconColor = AppColors.secondaryGrey,
            unfocusedTrailingIconColor = AppColors.secondaryGrey,
        )
        @Composable
        fun TextFieldKeyboardOptions()=KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            autoCorrect = false
        )
    }
}


@Composable
fun AppTextField(
    modifier: Modifier = AppTextFieldDefaults.TextFieldDefaultModifier(),
    shape: Shape = AppTextFieldDefaults.RoundCornderShape(),
    txtvalue: MutableState<String> = mutableStateOf(""),
    enabled: MutableState<Boolean> = mutableStateOf(true),
    isError: MutableState<Boolean> = mutableStateOf(false),
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = AppTextFieldDefaults.TextFieldKeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    colors: TextFieldColors = AppTextFieldDefaults.TextFieldDefaultsColors()
) = TextField(
    value = txtvalue.value,
    onValueChange = { txtvalue.value = it },
    shape = shape,
    enabled = enabled.value,
    isError = isError.value,
    modifier = modifier,
    colors = colors,
    label = label,
    placeholder = placeholder,
    leadingIcon = leadingIcon,
    trailingIcon = trailingIcon,
    prefix = prefix,
    suffix = suffix,
    supportingText = supportingText,
    keyboardOptions=keyboardOptions,
    keyboardActions=keyboardActions,
    singleLine=singleLine,
    maxLines=maxLines,
    minLines=minLines
)