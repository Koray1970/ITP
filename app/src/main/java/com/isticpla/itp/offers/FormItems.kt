package com.isticpla.itp.offers

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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
fun DropDownMenuWithAddButton(
    itms: DropdownMenuItems,
    productFeatureItems: List<ProductFeatureItem>,
    expendedMenuViewModel: ExpendedMenuViewModel = hiltViewModel()
) = Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    val gson = Gson()
    val scope = rememberCoroutineScope()
    val listofItems = remember{ mutableStateListOf<ExpendedMenuSelectedCollectionItem>() }
    val listofSelectedDataItems by expendedMenuViewModel.listOfSelectedCollections.collectAsState()
    //listofItems=

    /* val pItemState by
     expendedMenuViewModel.listOfSelectedCollections.collectAsStateWithLifecycle()
     LaunchedEffect(Unit) {
         when (pItemState) {
             is MutableList<*> -> {
                 listofItems = pItemState as MutableList<ExpendedMenuSelectedCollectionItem>
                 Log.v("FormItems","1")
                 Log.v("FormItems","listofItems : ${gson.toJson(listofItems)}")
             }

             else -> {
                 Log.v("FormItems","2")
                 //println(pItemState)
             }
         }
     }
     println("Sonuç : ${gson.toJson(pItemState)}")*/

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
                    it.second.contains(
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
                            DropdownMenuItem(
                                text = { Text(text = selectionOption.second) },
                                onClick = {
                                    itms.txfItems.fieldValue.value = selectionOption.second
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
                println("Sonuç1 : ${gson.toJson(listofItems)}")
                itms.expanded.value = false
            },
            shape = RoundedCornerShape(8.dp),
            modifier = itms.buttonModifier.then(Modifier.requiredHeight(56.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppColors.primaryGrey,
                contentColor = Color.White
            )
        ) {
            Icon(painter = painterResource(id = R.drawable.round_add_24), contentDescription = null)
            Text(text = "EKLE")
        }
    }
    Card(){

        when(listofItems){
            is List<*> ->{
                (listofItems as List<ExpendedMenuSelectedCollectionItem>).forEach {item->
                    Text(item.productFeatureItem.label)
                }
            }
            else->{
                println("Sonuç2 : ${gson.toJson(listofItems)}")
                Text("Lütfen bekleyiniz")
            }
        }

    }

    /*Column(
        modifier=Modifier
            .requiredHeight(IntrinsicSize.Max)
    ) {
        listofItems.forEach { item ->
            Text(item.productFeatureItem.label)
        }
        *//*pItemState.forEach { item->
            Text(item.productFeatureItem.label)
            val rm = remember { mutableStateOf(item.collectionData.first().second.toString()) }
            when (item.productFeatureItem.formItemType) {
                FormItemTypes.MULTILINETEXTFIELD -> {
                    appTextField(
                        itms = appTextFieldItems(
                            Modifier,
                            Modifier
                                .fillMaxWidth(),
                            rm,
                            null,
                            item.productFeatureItem.label,
                            false,
                            true,
                            false,
                            false,
                            Int.MAX_VALUE,
                            2,
                            txtFColors(),
                            txtFKeyboardOptionsCapSentence
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }

                else -> {
                    appTextField(
                        itms = appTextFieldItems(
                            Modifier,
                            Modifier
                                .fillMaxWidth(),
                            rm,
                            null,
                            item.productFeatureItem.label,
                            false,
                            true,
                            false,
                            true,
                            1,
                            minLines = 1,
                            txtFColors(),
                            txtFKeyboardOptionsCapWord
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }*//*
    }*/


    /*LazyColumn(
        state = rememberLazyListState(),
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
    ) {
        itemsIndexed(pItemState.value) { index, item ->
            val rm = remember { mutableStateOf(item.collectionData.first().second.toString()) }
            when (item.productFeatureItem.formItemType) {
                FormItemTypes.MULTILINETEXTFIELD -> {
                    appTextField(
                        itms = appTextFieldItems(
                            Modifier,
                            Modifier
                                .fillMaxWidth(),
                            rm,
                            null,
                            item.productFeatureItem.label,
                            false,
                            true,
                            false,
                            false,
                            Int.MAX_VALUE,
                            2,
                            txtFColors(),
                            txtFKeyboardOptionsCapSentence
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }

                else -> {
                    appTextField(
                        itms = appTextFieldItems(
                            Modifier,
                            Modifier
                                .fillMaxWidth(),
                            rm,
                            null,
                            item.productFeatureItem.label,
                            false,
                            true,
                            false,
                            true,
                            1,
                            minLines = 1,
                            txtFColors(),
                            txtFKeyboardOptionsCapWord
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }*/
}