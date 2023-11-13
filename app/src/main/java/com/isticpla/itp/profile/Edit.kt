package com.isticpla.itp.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.isticpla.itp.offers.appTextField
import com.isticpla.itp.offers.appTextFieldItems
import com.isticpla.itp.offers.txtFColors
import com.isticpla.itp.offers.txtFKeyboardOptionsCapWord
import com.isticpla.itp.uimodules.AppDatePicker
import com.isticpla.itp.uistyles.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Edit(
    navController: NavController,
) {
    var txtPName = rememberSaveable { mutableStateOf("") }
    var txtPLastName = rememberSaveable { mutableStateOf("") }
    var txtPDateofBirth = rememberSaveable { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }

    val dateofBirthPickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)
    Scaffold(
        containerColor = Color.White,
        topBar = { ProfileTopBar(navController, "Profili Düzenle", "profile/dashboard", "home") }
    ) { innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .padding(top = 2.dp)
                .padding(horizontal = 10.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier

                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally)
            ) {
                appTextField(
                    itms = appTextFieldItems(
                        Modifier.weight(.5f),
                        Modifier,
                        txtPName,
                        null,
                        "Adınız",
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
                appTextField(
                    itms = appTextFieldItems(
                        Modifier.weight(.5f),
                        Modifier,
                        txtPLastName,
                        null,
                        "Soyadınız",
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
            }
            appTextField(
                itms = appTextFieldItems(
                    Modifier.fillMaxWidth(),
                    Modifier.clickable {
                        showDatePicker=true
                    },
                    txtPDateofBirth,
                    null,
                    "Doğum Tarihi",
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

        }
    }
    if (showDatePicker) {
        AppDatePicker(
            onDateSelected = { txtPDateofBirth.value = it },
            onDismiss = { showDatePicker = false })
    }
}