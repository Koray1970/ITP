package com.isticpla.itp.mediaworks

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppMediaGallery(
    showSheetState: MutableState<Boolean> = mutableStateOf(false)
) {
    
    val sheetState = rememberModalBottomSheetState()
    if (showSheetState.value) {
        ModalBottomSheet(
            onDismissRequest = { /*TODO*/ },
            sheetState = sheetState
        ) {

        }
    }
}