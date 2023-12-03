package com.isticpla.itp.uimodules

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.isticpla.itp.R
import com.isticpla.itp.offerdetails.OfferViewModel
import com.isticpla.itp.poppinFamily
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSupplierDropdown(
    modifier: Modifier,
    label: @Composable (() -> Unit)? = null,
    txtvalue: MutableState<String> = mutableStateOf(""),
    enabled: MutableState<Boolean> = mutableStateOf(false),
    isError: MutableState<Boolean> = mutableStateOf(false)
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    val offerviewModel = hiltViewModel<OfferViewModel>()
    var supplierList =
        offerviewModel.getSupplierList.collectAsStateWithLifecycle(initialValue = mutableStateListOf<Pair<String, MutableState<Boolean>>>())
    var selectedSuppliers = remember { mutableStateListOf<String>() }
    //Log.v("MainActivity2", "enabled : ${enabled.value}")
    if (!enabled.value) {
        txtvalue.value = ""
        selectedSuppliers.clear()
        supplierList.value.forEach { a -> a.second.value = false }
        //Log.v("MainActivity2", "selectedSuppliers1 : ${selectedSuppliers.size}")
    } else {
        if (selectedSuppliers.isEmpty())
            supplierList.value.forEach { a -> a.second.value = false }
        //Log.v("MainActivity2", "selectedSuppliers2 : ${selectedSuppliers.size}")
    }

    TextField(
        value = txtvalue.value,
        onValueChange = {
            txtvalue.value = it

        },
        readOnly = true,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = if (isError.value) AppColors.red_0xffe23e3e else AppColors.secondaryGrey,
                shape = RoundedCornerShape(10)
            )
            .then(modifier),
        label = label,
        enabled = enabled.value,
        singleLine = false,
        minLines = 2,
        maxLines = 5,
        colors = AppTextFieldDefaults.TextFieldDefaultsColors(),
        trailingIcon = {
            Column(
                verticalArrangement = Arrangement.Top
            ) {
                IconButton(onClick = {
                    if (enabled.value) {
                        scope.launch {
                            showBottomSheet = !showBottomSheet
                        }
                    }
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.round_expand_more_24),
                        contentDescription = null
                    )
                }
            }
        }
    )
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            // Sheet content
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (supplierList.value.isNotEmpty())
                    items(supplierList.value) { a ->
                        var (checkedState, onStateChange) = remember { mutableStateOf(a.second.value) }
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .toggleable(
                                    value = checkedState,
                                    onValueChange = {
                                        onStateChange(!checkedState)
                                        if (!checkedState) {
                                            selectedSuppliers.add(a.first)
                                            a.second.value = true
                                        } else {
                                            a.second.value = false
                                            selectedSuppliers.remove(a.first)
                                        }
                                        txtvalue.value = ""
                                        if (selectedSuppliers.isNotEmpty()) {
                                            txtvalue.value = selectedSuppliers.joinToString("\n")
                                            //println(selectedSuppliers.joinToString("\n"))
                                        } else {
                                            selectedSuppliers.clear()
                                            txtvalue.value = ""
                                        }
                                    },
                                    role = Role.Checkbox
                                )
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = checkedState,
                                onCheckedChange = null, // null recommended for accessibility with screenreaders
                                colors = CheckboxDefaults.colors(
                                    checkedColor = AppColors.green_103,
                                    uncheckedColor = AppColors.green_103.copy(.7f)

                                )
                            )
                            Text(
                                text = a.first,
                                style = TextStyle(
                                    fontFamily = poppinFamily,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                ),
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                    }
            }
        }
    }
}