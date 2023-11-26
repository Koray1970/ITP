package com.isticpla.itp.uimodules

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.isticpla.itp.R
import com.isticpla.itp.poppinFamily

class AppTextFieldDefaults() {
    companion object {
        //default radius=10
        @Composable
        fun RoundCornderShape(radius: Int = 10) = RoundedCornerShape(radius)

        fun TextFieldDefaultModifier(
            fillmaxwidth: Float = 1f,
            borderWidth: Dp = 1.dp,
            strokeColor: Color = AppColors.secondaryGrey,
            iserror: MutableState<Boolean> = mutableStateOf(false)
        ) = Modifier
            .fillMaxWidth(fillmaxwidth)
            .border(
                width = borderWidth,
                color = if (iserror.value) Color.Red else strokeColor,
                shape = RoundedCornerShape(10)
            )

        val textfieldBorderModifier = Modifier.border(
            width = 1.dp,
            color = AppColors.secondaryGrey,
            shape = RoundedCornerShape(10)
        )

        @Composable
        fun TextFieldDefaultsColors() = TextFieldDefaults.colors(
            selectionColors = TextSelectionColors(
                backgroundColor = AppColors.primaryGrey.copy(.2f),
                handleColor = AppColors.grey_118
            ),
            cursorColor = AppColors.primaryGrey,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            errorContainerColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedTextColor = AppColors.primaryGrey,
            unfocusedTextColor = AppColors.primaryGrey,
            focusedLabelColor = AppColors.grey_118,
            unfocusedLabelColor = AppColors.grey_118,
            focusedSuffixColor = AppColors.grey_118,
            unfocusedSuffixColor = AppColors.primaryGrey,
            focusedPrefixColor = AppColors.grey_118,
            unfocusedPrefixColor = AppColors.primaryGrey,
            focusedLeadingIconColor = AppColors.primaryGrey,
            unfocusedLeadingIconColor = AppColors.primaryGrey,
            focusedTrailingIconColor = AppColors.grey_118,
            unfocusedTrailingIconColor = AppColors.secondaryGrey,
            focusedPlaceholderColor = AppColors.grey_118,
            unfocusedPlaceholderColor = AppColors.primaryGrey,
            disabledContainerColor = Color.White,
            disabledIndicatorColor = Color.Transparent,
            disabledLabelColor = AppColors.grey_118,
            disabledTextColor = AppColors.grey_118,
            disabledPlaceholderColor = AppColors.grey_118,
            disabledTrailingIconColor = AppColors.grey_118,
            disabledLeadingIconColor = AppColors.grey_118,
        )

        @Composable
        fun TextFieldKeyboardOptions(
            keyboardtype: KeyboardType = KeyboardType.Ascii,
            capitalization: KeyboardCapitalization = KeyboardCapitalization.Words,
            autoCorrect: Boolean = false
        ) =
            KeyboardOptions(
                keyboardType = keyboardtype,
                capitalization = capitalization,
                autoCorrect = autoCorrect
            )

        @Composable
        fun ClearTextIcon(text: MutableState<String>, isenabled: Boolean = true) {
            if (isenabled)
                if (text.value.length > 0) {
                    IconButton(
                        onClick = { text.value = "" }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.round_clear_24),
                            contentDescription = null
                        )
                    }
                }
        }

        val TextFieldTextStyle = TextStyle(
            fontFamily = poppinFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    shape: Shape = AppTextFieldDefaults.RoundCornderShape(),
    txtvalue: MutableState<String> = mutableStateOf(""),
    enabled: MutableState<Boolean> = mutableStateOf(true),
    isError: MutableState<Boolean> = mutableStateOf(false),
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = {
        AppTextFieldDefaults.ClearTextIcon(text = txtvalue, isenabled = true)
    },
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = AppTextFieldDefaults.TextFieldKeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    colors: TextFieldColors = AppTextFieldDefaults.TextFieldDefaultsColors(),
    maxLength: Int? = null
) {
    TextField(
        value = txtvalue.value,
        onValueChange = {
            if (maxLength != null) {
                if (it.length <= maxLength)
                    txtvalue.value = it
            } else
                txtvalue.value = it
            isError.value = it.isEmpty()
        },
        modifier = Modifier
            .border(
                width = 1.dp,
                color = if (isError.value) AppColors.red_0xffe23e3e else AppColors.secondaryGrey,
                shape = RoundedCornerShape(10)
            )
            .then(modifier),
        shape = shape,
        enabled = enabled.value,
        isError = isError.value,
        colors = colors,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix,
        supportingText = supportingText,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
    )
}