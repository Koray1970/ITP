package com.isticpla.itp.uimodules

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <K, V> AppExposedDropdownMenuBox(
    dropdownmodifier: Modifier = Modifier,
    expended: MutableState<Boolean> = mutableStateOf(false),
    selectedOptionText: MutableState<String> = mutableStateOf(""),
    selectedOptionKey: MutableState<String> = mutableStateOf(""),
    isError: MutableState<Boolean> = mutableStateOf(false),
    enabled: MutableState<Boolean> = mutableStateOf(true),
    dropdownlabel: @Composable (() -> Unit)? = null,
    onChangeSubEvent:(() -> Unit)? = null,
    onClearIconClickEvent:(()->Unit)?=null,
    onMenuItemClickEvent:(() -> Unit)? = null,
    listdata:MutableList<Pair<K, V>>,
    hascleartext: MutableState<Boolean> = mutableStateOf(true),
    colors: TextFieldColors = AppTextFieldDefaults.TextFieldDefaultsColors()
) = ExposedDropdownMenuBox(
    modifier = dropdownmodifier,
    expanded = if (!enabled.value) {
        enabled.value
    } else {
        expended.value
    },
    onExpandedChange = {
        if (!enabled.value)
            expended.value = false
        else
            expended.value = !expended.value
    },
) {
    TextField(
        colors = colors,
        // The `menuAnchor` modifier must be passed to the text field for correctness.
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if (isError.value) AppColors.red_0xffe23e3e else AppColors.secondaryGrey,
                shape = RoundedCornerShape(10)
            )
            .menuAnchor(),
        value = selectedOptionText.value,
        onValueChange = {
            selectedOptionText.value = it
            if (onChangeSubEvent != null) {
                onChangeSubEvent()
            }
            isError.value = it.isEmpty()
        },
        enabled = enabled.value,
        isError = isError.value,
        readOnly = true,
        singleLine = true,
        label = dropdownlabel,
        trailingIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (hascleartext.value) {
                    AppDropdownDefaults.ClearTextIcon(
                        text = selectedOptionText,
                        isenabled = true,
                        onClickEvent = onClearIconClickEvent
                    )
                }
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expended.value)
            }
        },

        )
    // filter options based on text field value

    if (listdata.isNotEmpty()) {
        ExposedDropdownMenu(
            modifier = Modifier.background(Color.White),
            expanded = if (!enabled.value) {
                enabled.value
            } else {
                expended.value
            },
            onDismissRequest = { expended.value = false },
        ) {
            listdata.forEach { selectionOption ->
                DropdownMenuItem(
                    modifier = Modifier.background(Color.White),
                    text = {
                        Text(
                            text = "${selectionOption.second}",
                            style = AppTextFieldWithPhoneAreaDefaults.TextFieldTextStyle
                        )
                    },
                    onClick = {
                        selectedOptionText.value = selectionOption.second.toString()
                        selectedOptionKey.value = selectionOption.first.toString()
                        if (onMenuItemClickEvent != null) {
                            onMenuItemClickEvent()
                        }
                        expended.value = false
                        isError.value = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    colors = MenuDefaults.itemColors(
                        textColor = AppColors.primaryGrey
                    )
                )
            }
        }
    }
}