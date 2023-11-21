package com.isticpla.itp.uimodules

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.material3.*
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AppDatePicker(
    onDateSelected: MutableState<String> = mutableStateOf(""),
    onDatePickerDismissState: MutableState<Boolean> = mutableStateOf(false),
    title: @Composable (() -> Unit)? = null,
) {
    var selectedDate by remember { mutableStateOf("") }
    val datePickerState = rememberDatePickerState(
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis <= System.currentTimeMillis() //super.isSelectableDate(utcTimeMillis)
            }
        }
    )

    selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""
    if(onDatePickerDismissState.value) {
        DatePickerDialog(
            onDismissRequest = { onDatePickerDismissState.value = false },
            confirmButton = {
                Button(onClick = {
                    onDateSelected.value = selectedDate
                    onDatePickerDismissState.value = false
                }

                ) {
                    Text(text = "Tamam")
                }
            },
            dismissButton = {
                Button(onClick = {
                    onDatePickerDismissState.value = false
                }) {
                    Text(text = "Vazgeç")
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                title = title,
                colors = DatePickerDefaults.colors(
                    containerColor = Color.White,
                    selectedYearContainerColor = Color.White,
                    titleContentColor = AppColors.primaryGrey,
                    headlineContentColor = AppColors.primaryGrey,
                    dividerColor = AppColors.primaryGrey
                )
            )
        }
    }
}

private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(Date(millis))
}