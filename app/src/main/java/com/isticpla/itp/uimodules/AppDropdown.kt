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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.isticpla.itp.R

class AppDropdownDefaults {
    companion object {
        @Composable
        fun ExposedDropdownMenuBoxModifier(
            maxwidth: Float = 1f,
            borderWidth: Dp = 1.dp,
            strokeColor: Color = AppColors.secondaryGrey
        ) = Modifier
            .fillMaxWidth(maxwidth)
            .border(width = borderWidth, color = strokeColor, shape = RoundedCornerShape(10))

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
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <K, V> AppDropdown(
    dropdownmodifier: Modifier = Modifier,
    expended: MutableState<Boolean> = mutableStateOf(false),
    selectedOptionText: MutableState<String> = mutableStateOf(""),
    selectedOptionKey: MutableState<String> = mutableStateOf(""),
    isError: MutableState<Boolean> = mutableStateOf(false),
    enabled: MutableState<Boolean> = mutableStateOf(true),
    dropdownlabel: @Composable (() -> Unit)? = null,
    listdata: List<Pair<K, V>>,
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
            isError.value = it.isEmpty()
        },
        enabled = enabled.value,
        isError = isError.value,
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
                        isenabled = true
                    )
                }
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expended.value)
            }
        },

        )
    // filter options based on text field value
    val filteringOptions = listdata.filter {
        it.second.toString().contains(selectedOptionText.value, ignoreCase = true)
    }
    if (filteringOptions.isNotEmpty()) {
        ExposedDropdownMenu(
            modifier = Modifier.background(Color.White),
            expanded = if (!enabled.value) {
                enabled.value
            } else {
                expended.value
            },
            onDismissRequest = { expended.value = false },
        ) {
            filteringOptions.forEach { selectionOption ->
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
@Composable
fun AppDefaultStyleText(value:String)=Text(
    text = value,
    style = AppTextFieldDefaults.TextFieldTextStyle
)
/*
usage model
val listOfAppCulture =
        homeViewModel.areacodeList.collectAsStateWithLifecycle(initialValue = mutableListOf<Pair<String, String>>())
var cultureDropdownExpandState = remember { mutableStateOf(false) }
var dropdownisenabled = remember { mutableStateOf(true) }
var dropdowndummy = remember { mutableStateOf("") }
var dummy = remember { mutableStateOf("") }

AppDropdown(
    expended = cultureDropdownExpandState,
    selectedOptionText = dropdowndummy,
    listdata = listOfAppCulture.value,
    enabled = dropdownisenabled,
    dropdownlabel = {
    Text(text = "Telefon numaranız", style = AppTextFieldDefaults.TextFieldTextStyle)},
    colors = AppTextFieldDefaults.TextFieldDefaultsColors()
)

 */