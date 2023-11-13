package com.isticpla.itp.offers

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.gson.Gson
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.ExpendedMenuSelectedCollectionItem
import com.isticpla.itp.dummydata.ExpendedMenuViewModel
import com.isticpla.itp.dummydata.FormItemTypes
import com.isticpla.itp.dummydata.ProductFeatureItem
import com.isticpla.itp.uimodules.AppColors
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Objects

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
    var trialingIcon: @Composable (() -> Unit)? = null,
    var leadingIcon: @Composable (() -> Unit)? = null,
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
        modifier = itms.textFieldModifier,
        trailingIcon = itms.trialingIcon,
        leadingIcon = itms.leadingIcon
    )
}

data class DropdownMenuItems<K, V>(
    var txfItems: appTextFieldItems,
    var expanded: MutableState<Boolean>,
    val menuitems: List<Pair<K, V>>,
    val buttonModifier: Modifier? = null,
    val buttonLabelText: String? = null,
    val buttonLabelTextStyle: TextStyle? = null,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <K, V> DropDownMenu(
    itms: DropdownMenuItems<K, V>
) = Column(
    modifier = Modifier
        .fillMaxWidth(),
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    val scope = rememberCoroutineScope()
    Row(
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
                        .fillMaxWidth()
                        .then(itms.txfItems.textFieldModifier),
                    label = { Text(text = itms.txfItems.label) },
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
                    it.second.toString().contains(
                        itms.txfItems.fieldValue.value,
                        ignoreCase = true
                    )
                }
                if (filteringOptions.isNotEmpty()) {
                    ExposedDropdownMenu(
                        expanded = itms.expanded.value,
                        onDismissRequest = { itms.expanded.value = false },
                        modifier = Modifier.background(Color.White)
                    ) {
                        filteringOptions.forEach { selectionOption ->
                            val soS = selectionOption.second.toString()
                            DropdownMenuItem(
                                text = { Text(text = soS) },
                                onClick = {
                                    itms.txfItems.fieldValue.value = soS
                                    itms.expanded.value = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                colors = MenuDefaults.itemColors()
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <K, V> DropDownMenuWithAddButton(
    itms: DropdownMenuItems<K, V>,
    productFeatureItems: List<ProductFeatureItem>,
    expendedMenuViewModel: ExpendedMenuViewModel
) = Column(
    modifier = Modifier
        .fillMaxWidth(),
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    val scope = rememberCoroutineScope()
    Row(
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
                        .fillMaxWidth(.71f)
                        .then(itms.txfItems.textFieldModifier),
                    label = { Text(text = itms.txfItems.label) },
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
                    it.second.toString().contains(
                        itms.txfItems.fieldValue.value,
                        ignoreCase = true
                    )
                }
                if (filteringOptions.isNotEmpty()) {
                    ExposedDropdownMenu(
                        expanded = itms.expanded.value,
                        onDismissRequest = { itms.expanded.value = false },
                        modifier = Modifier.background(Color.White)
                    ) {
                        filteringOptions.forEach { selectionOption ->
                            val soS = selectionOption.second.toString()
                            DropdownMenuItem(
                                text = { Text(text = soS) },
                                onClick = {
                                    itms.txfItems.fieldValue.value = soS
                                    itms.expanded.value = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                colors = MenuDefaults.itemColors()
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.width(6.dp))
        Button(
            onClick = {
                var newitem =
                    productFeatureItems.first { a -> a.label.toString() == itms.txfItems.fieldValue.value }
                expendedMenuViewModel.AddSelectedCollection(newitem)
                itms.expanded.value = false
            },
            shape = RoundedCornerShape(8.dp),
            modifier = itms.buttonModifier!!.then(Modifier.requiredHeight(56.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppColors.primaryGrey,
                contentColor = Color.White
            )
        ) {
            Icon(painter = painterResource(id = R.drawable.round_add_24), contentDescription = null)
            Text(text = "EKLE")
        }
    }
}

@Composable
fun AppSwitch(
    cChecked: MutableState<Boolean>
) = Switch(
    checked = cChecked.value,
    onCheckedChange = { cChecked.value = it },
    colors = SwitchDefaults.colors(
        checkedBorderColor = Color.Transparent,
        uncheckedBorderColor = Color.Transparent,
        checkedTrackColor = AppColors.grey_133,
        uncheckedTrackColor = AppColors.grey_133,
        checkedThumbColor = Color.White,
        uncheckedThumbColor = AppColors.grey_135,
        uncheckedIconColor = Color.White,
        checkedIconColor = Color.Black
    ),
    thumbContent = if (cChecked.value) {
        {
            Icon(
                painter = painterResource(id = R.drawable.round_check_24),
                contentDescription = null,
                modifier = Modifier.size(SwitchDefaults.IconSize),
            )
        }
    } else {
        null
    }
)

data class PANTFPhoneTextFieldItem (
    var fieldValue: MutableState<String> = mutableStateOf(""),
    val label: @Composable (() -> Unit)? = null,
    var isError:MutableState<Boolean> = mutableStateOf(false),
    val modifier: Modifier = Modifier
)

data class PANTFItem<K, V> (
    var rootCardModifier: Modifier? = null,
    var dropdownExpended:MutableState<Boolean> = mutableStateOf(false),
    var expMenuTFValue: MutableState<String> = mutableStateOf(""),
    var expMenuTFModifier: Modifier = Modifier,
    val menuItems: List<Pair<K, V>> = emptyList<Pair<K, V>>(),
    val phoneTextFieldItem: PANTFPhoneTextFieldItem = PANTFPhoneTextFieldItem()
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <K, V> appPhoneAreaAndNumberTextFieldGroup(itms: PANTFItem<K, V>) = Card(
    colors = CardDefaults.cardColors(
        containerColor = Color.Transparent
    ),
    shape = RoundedCornerShape(8.dp),
    modifier = Modifier
        .border(1.dp, AppColors.grey_133, RoundedCornerShape(8.dp))
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            expanded = itms.dropdownExpended.value,
            onExpandedChange = { itms.dropdownExpended.value = !itms.dropdownExpended.value }
        ) {
            TextField(
                value = itms.expMenuTFValue.value,
                onValueChange = { itms.expMenuTFValue.value = it },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(.34f)
                    .then(itms.expMenuTFModifier),
                label = { Text(text = "") },
                singleLine = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = itms.dropdownExpended.value) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
            )
            // filter options based on text field value
            val filteringOptions = itms.menuItems.filter { mi ->
                mi.second.toString().contains(
                    itms.expMenuTFValue.value,
                    ignoreCase = true
                )
            }
            if (filteringOptions.isNotEmpty()) {
                ExposedDropdownMenu(
                    expanded = itms.dropdownExpended.value,
                    onDismissRequest = {

                        itms.dropdownExpended.value = false
                    },
                    modifier = Modifier.background(Color.White)
                ) {
                    filteringOptions.forEach { selectionOption ->
                        val soS = "${selectionOption.first.toString()} ${selectionOption.second.toString()}"
                        DropdownMenuItem(
                            text = { Text(text = soS) },
                            onClick = {
                                itms.expMenuTFValue.value = soS
                                itms.dropdownExpended.value = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            colors = MenuDefaults.itemColors()
                        )
                    }
                }
            }
        }

        VerticalDivider(thickness = 1.dp, color = AppColors.secondaryGrey , modifier = Modifier.requiredWidth(1.dp).requiredHeight(IntrinsicSize.Max))
        TextField(
            value = itms.phoneTextFieldItem.fieldValue.value,
            onValueChange = { itms.phoneTextFieldItem.fieldValue.value = it },
            isError = itms.phoneTextFieldItem.isError.value,
            label = itms.phoneTextFieldItem.label,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedLabelColor = AppColors.primaryGrey,
                unfocusedLabelColor = AppColors.grey_118,
                ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                autoCorrect = false,
                capitalization = KeyboardCapitalization.None
            ),
            modifier = itms.phoneTextFieldItem.modifier
        )
    }
}