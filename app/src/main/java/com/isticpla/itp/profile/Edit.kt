package com.isticpla.itp.profile

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.data.countryListDB
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.offers.*
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.AppDatePicker
import com.isticpla.itp.uimodules.DropDownTextField
import com.isticpla.itp.uimodules.DropDowndTextFieldRequest
import com.isticpla.itp.uimodules.defaultTextFieldColor
import com.isticpla.itp.uimodules.dropdownMenuItemColors
import com.isticpla.itp.uistyles.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Edit(
    navController: NavController,
) {
    val homeviewModel = hiltViewModel<HomeViewMode>()

    val textfieldColor=TextFieldDefaults.colors(
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.White,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        focusedLabelColor = AppColors.primaryGrey,
        unfocusedLabelColor = AppColors.grey_118,
    )

    var txtPName = rememberSaveable { mutableStateOf("") }
    var txtPLastName = rememberSaveable { mutableStateOf("") }
    var txtPDateofBirth = rememberSaveable { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    var txtPEmail = rememberSaveable { mutableStateOf("") }
    var txtPAreaCode = rememberSaveable { mutableStateOf("") }
    var txtPAreaCodeExpended = remember { mutableStateOf(false) }
    var txtPPhone = rememberSaveable { mutableStateOf("") }
    val phoneareacodes by homeviewModel.areacodeList.collectAsStateWithLifecycle(initialValue = emptyList<Pair<String, String>>())
    var txtPCountry = rememberSaveable { mutableStateOf("") }


    val countryoptions = homeviewModel.countryList
    var countryexpanded = remember { mutableStateOf(false) }
    var countryselectedOptionText = remember { mutableStateOf("") }


    val dateofBirthPickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)
    Scaffold(
        containerColor = Color.White,
        topBar = { ProfileTopBar(navController, "Profili Düzenle", "profile/dashboard", "home") }
    ) { innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .padding(top = 30.dp)
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
                        null,
                        "Adınız",
                        false,
                        true,
                        false,
                        true,
                        1,
                        minLines = 1,
                        textfieldColor,
                        txtFKeyboardOptionsCapWord
                    )
                )
                appTextField(
                    itms = appTextFieldItems(
                        Modifier.weight(.5f),
                        Modifier,
                        txtPLastName,
                        null,
                        null,
                        "Soyadınız",
                        false,
                        true,
                        false,
                        true,
                        1,
                        minLines = 1,
                        textfieldColor,
                        txtFKeyboardOptionsCapWord
                    )
                )
            }
            appTextField(
                itms = appTextFieldItems(
                    Modifier,
                    Modifier.fillMaxWidth(),
                    txtPDateofBirth,
                    {
                        IconButton(onClick = { showDatePicker = true }) {
                            Icon(
                                painter = painterResource(id = R.drawable.round_calendar_month_24),
                                contentDescription = null,
                                tint = AppColors.secondaryGrey
                            )
                        }
                    },
                    null,
                    "Doğum Tarihi",
                    false,
                    true,
                    false,
                    true,
                    1,
                    minLines = 1,
                    textfieldColor,
                    txtFKeyboardOptionsCapWord
                )
            )
            appTextField(
                itms = appTextFieldItems(
                    Modifier,
                    Modifier.fillMaxWidth(),
                    txtPEmail,
                    null,
                    {
                        Icon(
                            painter = painterResource(id = R.drawable.round_near_me_24),
                            contentDescription = null,
                            tint = AppColors.secondaryGrey
                        )
                    },
                    "E-posta adresiniz",
                    false,
                    true,
                    false,
                    true,
                    1,
                    minLines = 1,
                    textfieldColor,
                    txtFKeyboardOptionsCapWord.copy(keyboardType = KeyboardType.Email)
                )
            )
            appPhoneAreaAndNumberTextFieldGroup(
                PANTFItem<String, String>(
                    dropdownExpended = txtPAreaCodeExpended,
                    expMenuTFValue = txtPAreaCode,
                    menuItems = phoneareacodes,
                    phoneTextFieldItem = PANTFPhoneTextFieldItem(
                        fieldValue = txtPPhone,
                        label = { Text(text = "Telefon numaranız") }
                    )
                )
            )
            DropDownTextField(
                request = DropDowndTextFieldRequest(
                    exposedDropdownMenuBoxModifier = Modifier.fillMaxWidth(),
                    label = "Ülke",
                    selectedOptionText = countryselectedOptionText,
                    expended = countryexpanded,
                    listOfOptions = countryListDB,
                    textFieldModifier = Modifier
                        //.menuAnchor()
                        .fillMaxWidth()
                        .border(1.dp, AppColors.grey_133, RoundedCornerShape(8.dp)),
                    textFieldReadOnly = true,
                    textfieldColors = textfieldColor,
                    menuItemColors = MenuItemColors(
                        textColor = AppColors.primaryGrey,
                        leadingIconColor = AppColors.primaryGrey,
                        trailingIconColor = AppColors.primaryGrey,
                        disabledTextColor = AppColors.primaryGrey,
                        disabledLeadingIconColor = AppColors.primaryGrey,
                        disabledTrailingIconColor = AppColors.primaryGrey
                    ),
                    menuItemModifier =Modifier
                )
            )
            Spacer(modifier=Modifier.height(26.dp))
            Button(
                onClick = { navController.navigate("offer/create/publish") },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.blue_102,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(48.dp)
            ) {
                Text(text = "Güncelle", style = offerStagePublishButton)
                Icon(
                    painter = painterResource(id = R.drawable.round_arrow_right_alt_24),
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Spacer(modifier=Modifier.height(30.dp))
        }
    }
    if (showDatePicker) {
        AppDatePicker(
            onDateSelected = { txtPDateofBirth.value = it },
            onDismiss = { showDatePicker = false })
    }
}