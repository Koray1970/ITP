package com.isticpla.itp.uimodules

import androidx.compose.foundation.background
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType


data class DropDowndTextFieldRequest<K, V>(
    var exposedDropdownMenuBoxModifier: Modifier = Modifier,
    var label: String,
    var selectedOptionText: MutableState<String>,
    var expended: MutableState<Boolean> = mutableStateOf(false),
    var listOfOptions: List<Pair<K, V>> = emptyList<Pair<K, V>>(),
    var textFieldModifier: Modifier = Modifier,
    var textFieldReadOnly: Boolean,
    var textfieldColors: TextFieldColors,
    var menuItemColors: MenuItemColors,
    var menuItemModifier: Modifier = Modifier,
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <K, V> DropDownTextField(request: DropDowndTextFieldRequest<K, V>) = ExposedDropdownMenuBox(
    modifier = request.exposedDropdownMenuBoxModifier,
    expanded = request.expended.value,
    onExpandedChange = { request.expended.value = !request.expended.value },
) {
    TextField(
        // The `menuAnchor` modifier must be passed to the text field for correctness.
        modifier = Modifier
            .menuAnchor()
            .then(request.textFieldModifier),
        readOnly = request.textFieldReadOnly,
        value = request.selectedOptionText.value,
        onValueChange = { request.selectedOptionText.value = it },
        label = { Text(text = request.label) },
        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = request.expended.value) },
        //colors = ExposedDropdownMenuDefaults.textFieldColors(),
        keyboardOptions = KeyboardOptions(autoCorrect = false, keyboardType = KeyboardType.Ascii),
        colors = request.textfieldColors
    )
    // filter options based on text field value
    val filteringOptions =
        request.listOfOptions.filter {
            it.first.toString().contains(request.selectedOptionText.value, ignoreCase = true)
        }
    if (filteringOptions.isNotEmpty()) {
        ExposedDropdownMenu(
            modifier=Modifier.background(Color.White),
            expanded = request.expended.value,
            onDismissRequest = { request.expended.value = false },
        ) {
            request.listOfOptions.forEach { selectionOption ->
                val lstFirstItem = selectionOption.first.toString()
                DropdownMenuItem(
                    modifier = request.menuItemModifier,
                    text = { Text(lstFirstItem) },
                    onClick = {
                        request.selectedOptionText.value = lstFirstItem
                        request.expended.value = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    colors =  request.menuItemColors
                )
            }
        }
    }
}


fun dropdownTextFieldColors(request: DropDownTextFieldColorRequest?, isDefault: Boolean) =
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

data class dropdownMenuItemColorRequest(
    val textColor: Color,
    val leadingIconColor: Color,
    val trailingIconColor: Color,
    val disabledTextColor: Color,
    val disabledLeadingIconColor: Color,
    val disabledTrailingIconColor: Color,
)

fun dropdownMenuItemColors(menuItemColor: MenuItemColors?, isDefault: Boolean) = MenuItemColors(
    textColor = if (isDefault) AppColors.primaryGrey else menuItemColor!!.textColor,
    leadingIconColor = if (isDefault) AppColors.primaryGrey else menuItemColor!!.leadingIconColor,
    trailingIconColor = if (isDefault) AppColors.primaryGrey else menuItemColor!!.trailingIconColor,
    disabledTextColor = if (isDefault) AppColors.primaryGrey else menuItemColor!!.disabledTextColor,
    disabledLeadingIconColor = if (isDefault) AppColors.primaryGrey else menuItemColor!!.disabledLeadingIconColor,
    disabledTrailingIconColor = if (isDefault) AppColors.primaryGrey else menuItemColor!!.disabledTrailingIconColor
)