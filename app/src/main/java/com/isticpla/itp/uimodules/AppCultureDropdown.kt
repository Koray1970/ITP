package com.isticpla.itp.uimodules

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.isticpla.itp.dummydata.AppCultureDataModel
import com.isticpla.itp.dummydata.listOfAppCulture
import com.isticpla.itp.poppinFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppCultureDropdown(
    textfieldmodifier: Modifier = AppTextFieldDefaults.TextFieldDefaultModifier(),
    expanded: MutableState<Boolean> = mutableStateOf(false),
    selectedOptionValue: MutableState<AppCultureDataModel> = mutableStateOf<AppCultureDataModel>(
        listOfAppCulture.first { a -> a.isdefault }),
    options: MutableList<AppCultureDataModel> = mutableStateListOf<AppCultureDataModel>(),
    colors: TextFieldColors = AppTextFieldDefaults.TextFieldDefaultsColors()
) {
    var selectedOptionText by remember { mutableStateOf("") }
    ExposedDropdownMenuBox(
        expanded = expanded.value,
        onExpandedChange = { expanded.value = it },
    ) {
        TextField(
            colors = colors,
            readOnly = true,
            modifier = Modifier
                .menuAnchor().then(textfieldmodifier),
            value = if (selectedOptionValue.value.id > 0)
                selectedOptionValue.value.name
            else
                selectedOptionText,
            onValueChange = { },
            leadingIcon = {
                if (selectedOptionValue.value.id > 0) {
                    Image(
                        painter = painterResource(id = selectedOptionValue.value.icon),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )
                }
            },
            label = {

                Text("Uygulama Lisanı Seçiniz")

            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded.value
                )
            }

        )

        ExposedDropdownMenu(
            modifier=Modifier.background(Color.White),
            expanded = expanded.value,
            onDismissRequest = {
                expanded.value = false
            }
        ) {
            options.forEach { so ->
                DropdownMenuItem(
                    modifier = Modifier.background(Color.White),
                    colors = MenuDefaults.itemColors(
                        textColor = AppColors.primaryGrey
                    ),
                    onClick = {
                        selectedOptionText = so.name
                        selectedOptionValue.value = so
                        expanded.value = false
                    },
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = so.icon),
                            contentDescription = null,
                            modifier = Modifier.size(28.dp)
                        )
                    },
                    text = {
                        Text(
                            text = "${so.name}",
                            style = AppTextFieldWithPhoneAreaDefaults.TextFieldTextStyle
                        )
                    }
                )
            }
        }

    }
}