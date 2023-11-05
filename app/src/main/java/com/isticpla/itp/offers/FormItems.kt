package com.isticpla.itp.offers

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
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
    val label: String,
    val isError: Boolean,
    var enabled: Boolean,
    var readonly: Boolean,
    var isSingleLine: Boolean,
    var maxLines: Int,
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
        colors = itms.appTextFieldColor,
        keyboardOptions = itms.appkeyboardOptions,
        modifier = itms.textFieldModifier
    )
}