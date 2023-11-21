package com.isticpla.itp.uimodules

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

class AppTextFieldWithPhoneAreaDefaults() {
    companion object {
        @Composable
        fun PhoneTextFieldClearTextIcon(
            areacode: MutableState<String>,
            text: MutableState<String>,
            isenabled: Boolean = true
        ) {
            if (isenabled)
                if (text.value.length > 0) {
                    IconButton(
                        onClick = {
                            areacode.value = ""
                            text.value = ""
                        }
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

private val dropdownItemTextStyle =
    TextStyle(fontFamily = poppinFamily, fontWeight = FontWeight.Normal, fontSize = 12.sp)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextFieldWithPhoneArea(
    phonetextmodifier: MutableState<Modifier> = mutableStateOf(AppTextFieldDefaults.TextFieldDefaultModifier()),
    dropdowntextfieldmodifier: Modifier = Modifier,
    dropdownexpended: MutableState<Boolean> = mutableStateOf(false),
    dropdownselectedoptionvalue: MutableState<String> = mutableStateOf(""),
    dropdownlabel: @Composable (() -> Unit)? = { Text(text = "+00") },
    dropdowndata: MutableList<Pair<String, String>> = mutableStateListOf<Pair<String, String>>(),
    phonetextlabel: @Composable (() -> Unit)? = null,
    phonetextfieldvalue: MutableState<String> = mutableStateOf(""),
    phonetexttrailingIcon: @Composable (() -> Unit)? = {
        AppTextFieldWithPhoneAreaDefaults.PhoneTextFieldClearTextIcon(
            areacode = dropdownselectedoptionvalue,
            text = phonetextfieldvalue,
            isenabled = true
        )
    },
    phonetextiserror: MutableState<Boolean> = mutableStateOf(false),
    phonetextfieldcolors: TextFieldColors = AppTextFieldDefaults.TextFieldDefaultsColors()
) {
    var fieldiserror = remember { mutableStateOf(false) }
    fieldiserror.value = phonetextiserror.value
    phonetextmodifier.value =
        AppTextFieldDefaults.TextFieldDefaultModifier(iserror = fieldiserror)
    TextField(
        modifier =Modifier
            .border(
                width = 1.dp,
                color = if (phonetextiserror.value) AppColors.red_0xffe23e3e else AppColors.secondaryGrey,
                shape = RoundedCornerShape(10)
            ).then(phonetextmodifier.value),
        value = phonetextfieldvalue.value,
        onValueChange = {
            if (it.length > 0) {
                fieldiserror.value = false
                phonetextiserror.value=false
            }
            if (it.length <= 15)
                phonetextfieldvalue.value = it
        },
        label = phonetextlabel,
        isError = phonetextiserror.value,
        singleLine = true,
        trailingIcon = phonetexttrailingIcon,
        leadingIcon = {
            //region Exposed Dropdown Menu Box
            ExposedDropdownMenuBox(
                expanded = dropdownexpended.value,
                onExpandedChange = { dropdownexpended.value = !dropdownexpended.value },
            ) {
                TextField(
                    // The `menuAnchor` modifier must be passed to the text field for correctness.
                    modifier = Modifier
                        .fillMaxWidth(.30f)
                        .menuAnchor()
                        .then(dropdowntextfieldmodifier),
                    value = dropdownselectedoptionvalue.value,
                    onValueChange = {
                        if (it.length <= 3)
                            dropdownselectedoptionvalue.value = it
                    },
                    singleLine = true,
                    label = dropdownlabel,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropdownexpended.value) },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Characters,
                        autoCorrect = false
                    ),
                    colors = AppTextFieldDefaults.TextFieldDefaultsColors() //ExposedDropdownMenuDefaults.textFieldColors(),
                )
                val filteringOptions = dropdowndata.filter {
                    "${it.first} ${it.second}".contains(
                        dropdownselectedoptionvalue.value,
                        ignoreCase = true
                    )
                }
                if (filteringOptions.isNotEmpty()) {
                    ExposedDropdownMenu(
                        modifier = Modifier.background(Color.White),
                        expanded = dropdownexpended.value,
                        onDismissRequest = { dropdownexpended.value = false },
                    ) {
                        filteringOptions.forEach { selectionOption ->
                            DropdownMenuItem(
                                modifier = Modifier.background(Color.White),
                                text = {
                                    Text(
                                        text = "${selectionOption.first} ${selectionOption.second}",
                                        style = AppTextFieldWithPhoneAreaDefaults.TextFieldTextStyle
                                    )
                                },
                                onClick = {
                                    dropdownselectedoptionvalue.value = selectionOption.second
                                    dropdownexpended.value = false
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
            //endregion
        },
        colors = phonetextfieldcolors,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            autoCorrect = false,
            capitalization = KeyboardCapitalization.None
        )
    )
}