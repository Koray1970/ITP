package com.isticpla.itp.offers

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.isticpla.itp.R
import com.isticpla.itp.uimodules.AppColors

@Composable
fun txtFColors() = TextFieldDefaults.colors(
    focusedContainerColor = Color.Transparent,
    unfocusedContainerColor = Color.Transparent,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent
)

val txtFKeyboardOptionsCapWord = KeyboardOptions(
    autoCorrect = false,
    capitalization = KeyboardCapitalization.Words
)
val txtFKeyboardOptionsCapSentence = KeyboardOptions(
    autoCorrect = false,
    capitalization = KeyboardCapitalization.Sentences
)

data class appTextFieldItems(
    val cardModifier: Modifier,
    val textFieldModifier: Modifier,
    var fieldValue: MutableState<String>,
    var trialingIcon: Int?,
    val label: String,
    val isError: Boolean,
    var enabled: Boolean,
    var readonly: Boolean,
    var isSingleLine: Boolean,
    var maxLines: Int,
    var minLines: Int = 1,
    val appTextFieldColor: TextFieldColors,
    val appkeyboardOptions: KeyboardOptions
)

@Composable
fun appTextField(itms: appTextFieldItems) = Card(
    colors = CardDefaults.cardColors(
        containerColor = Color.Transparent
    ),
    shape = RoundedCornerShape(8.dp),
    modifier = itms.cardModifier
        .border(1.dp, AppColors.grey_133, RoundedCornerShape(8.dp))
) {
    TextField(
        value = itms.fieldValue.value,
        onValueChange = { itms.fieldValue.value = it },
        isError = itms.isError,
        enabled = itms.enabled,
        readOnly = itms.readonly,
        label = { Text(text = itms.label) },
        singleLine = itms.isSingleLine,
        //maxLines = if (itms.isSingleLine) 1 else itms.maxLines,
        minLines = itms.minLines,
        colors = itms.appTextFieldColor,
        keyboardOptions = itms.appkeyboardOptions,
        modifier = itms.textFieldModifier
    )
}

data class DropdownMenuItems(
    var txfItems: appTextFieldItems,
    var expanded: MutableState<Boolean>,
    val menuitems: List<Pair<String, String>>,
    val buttonModifier: Modifier,
    val buttonLabelText: String,
    val buttonLabelTextStyle: TextStyle,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenuWithAddButton(itms: DropdownMenuItems) = Row(
    modifier = Modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = itms.txfItems.cardModifier
            .border(1.dp, AppColors.grey_133, RoundedCornerShape(8.dp))
    ) {
        ExposedDropdownMenuBox(
            expanded = itms.expanded.value,
            onExpandedChange = { itms.expanded.value = !itms.expanded.value }
        ) {
            TextField(
                value = itms.txfItems.fieldValue.value,
                onValueChange = { itms.txfItems.fieldValue.value = it },
                modifier = Modifier
                    .menuAnchor()
                    .then(itms.txfItems.textFieldModifier),
                label = { Text(text = "Label") },
                singleLine = itms.txfItems.isSingleLine,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = itms.expanded.value) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
            )
            // filter options based on text field value
            val filteringOptions = itms.menuitems.filter {
                it.second.contains(
                    itms.txfItems.fieldValue.value,
                    ignoreCase = true
                )
            }
            if (filteringOptions.isNotEmpty()) {
                ExposedDropdownMenu(
                    expanded = itms.expanded.value,
                    onDismissRequest = { itms.expanded.value = false },
                ) {
                    filteringOptions.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(text = selectionOption.second) },
                            onClick = {
                                itms.txfItems.fieldValue.value = selectionOption.second
                                itms.expanded.value = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.width(5.dp))
    Button(
        onClick = {
            itms.expanded.value = false
        },
        shape = RoundedCornerShape(8.dp),
        modifier = itms.buttonModifier.then(Modifier.requiredHeight(56.dp))
    ) {
        Icon(painter = painterResource(id = R.drawable.round_add_24), contentDescription = null)
        Text(text = "Ekle")
    }
}