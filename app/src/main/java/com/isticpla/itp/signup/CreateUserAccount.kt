package com.isticpla.itp.signup

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.data.countryListDB
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.DropDownTextField
import com.isticpla.itp.uimodules.DropDowndTextFieldRequest
import com.isticpla.itp.uimodules.defaultTextFieldColor
import com.isticpla.itp.uimodules.dropdownMenuItemColors
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateUserAccount(navController: NavController) {
    val context = LocalContext.current.applicationContext
    var nameValue by rememberSaveable { mutableStateOf("") }
    var nameError by remember { mutableStateOf(false) }
    val nameMaxLength = 60
    var lastNameValue by rememberSaveable { mutableStateOf("") }
    var lastNameError by remember { mutableStateOf(false) }
    val lastNameMaxLength = 60
    var emailValue by rememberSaveable { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    val emailMaxLength = 360

    val birthDateoptions = arrayListOf<Pair<String, String>>()
    (1..31).forEach {
        birthDateoptions.add(Pair(it.toString(), it.toString()))
    }
    var birthDateexpanded = remember { mutableStateOf(false) }
    var birthDateselectedOptionText = remember { mutableStateOf("") }

    val birthMonthoptions = arrayListOf<Pair<String, String>>()
    (1..12).forEach {
        birthMonthoptions.add(Pair(it.toString(), it.toString()))
    }
    var birthMonthexpanded = remember { mutableStateOf(false) }
    var birthMonthselectedOptionText = remember { mutableStateOf("") }

    val pastyear = LocalDate.now().minusYears(80L).year
    val thisyear = LocalDate.now().year
    val birthYearoptions = arrayListOf<Pair<String, String>>()
    (pastyear..thisyear).forEach {
        birthYearoptions.add(Pair(it.toString(), it.toString()))
    }
    var birthYearexpanded = remember { mutableStateOf(false) }
    var birthYearselectedOptionText = remember { mutableStateOf("") }


    val countryoptions = countryListDB
    var countryexpanded = remember { mutableStateOf(false) }
    var countryselectedOptionText = remember { mutableStateOf("") }

    var referanceCodeValue by rememberSaveable { mutableStateOf("") }
    var referanceCodeError by remember { mutableStateOf(false) }
    val referanceCodeMaxLength = 16

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(0.dp),
        containerColor = Color.White
    ) { innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 72.dp, bottom = 30.dp)
                    .height(10.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .weight(.33f)
                        .height(10.dp)
                        .background(color = AppColors.yellow_100)
                )
                Spacer(modifier = Modifier.weight(.05f))
                Box(
                    modifier = Modifier
                        .weight(.33f)
                        .height(10.dp)
                        .background(color = AppColors.grey_127)
                )
                Spacer(modifier = Modifier.weight(.05f))
                Box(
                    modifier = Modifier
                        .weight(.33f)
                        .height(10.dp)
                        .background(color = AppColors.grey_127)
                )
            }
            val headerReq = SignUpHeaderRequest()
            headerReq.title = "Hesabınızı oluşturun"
            headerReq.subtitle = "Başlamak için bilgilerinizi tamamlayın!"
            SingUpHeader(context = context, request = headerReq)
            Row() {
                TextField(
                    value = nameValue,
                    onValueChange = {
                        if (it.length <= nameMaxLength)
                            nameValue = it
                    },
                    isError = nameError,
                    label = { Text(text = "Adınız") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        autoCorrect = false,
                        capitalization = KeyboardCapitalization.Words
                    ),
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .weight(1f)
                        .border(1.dp, AppColors.grey_130, RoundedCornerShape(5.dp)),
                    colors = defaultTextFieldColor(null, true)
                )
                Spacer(modifier = Modifier.width(10.dp))
                TextField(
                    value = lastNameValue,
                    onValueChange = {
                        if (it.length <= lastNameMaxLength)
                            lastNameValue = it
                    },
                    isError = lastNameError,
                    label = { Text(text = "Soyadınız") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        autoCorrect = false,
                        capitalization = KeyboardCapitalization.Words
                    ),
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .weight(1f)
                        .border(1.dp, AppColors.grey_130, RoundedCornerShape(5.dp)),
                    colors = defaultTextFieldColor(null, true)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = emailValue,
                onValueChange = {
                    if (it.length <= emailMaxLength)
                        emailValue = it
                },
                isError = emailError,
                label = { Text(text = "E-posta adresiniz") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    autoCorrect = false,
                    capitalization = KeyboardCapitalization.None
                ),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, AppColors.grey_130, RoundedCornerShape(5.dp)),
                colors = defaultTextFieldColor(null, true)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                DropDownTextField(
                    request = DropDowndTextFieldRequest(
                        exposedDropdownMenuBoxModifier = Modifier.weight(.33f),
                        label = "Gün",
                        selectedOptionText = birthDateselectedOptionText,
                        expended = birthDateexpanded,
                        listOfOptions = birthDateoptions,
                        textFieldModifier = Modifier
                            .weight(.33f)
                            .border(1.dp, AppColors.grey_130, RoundedCornerShape(5.dp)),
                        textFieldReadOnly = true,
                        textfieldColors = defaultTextFieldColor(null, true),
                        menuItemColors = dropdownMenuItemColors(null, true),
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))
                DropDownTextField(
                    request = DropDowndTextFieldRequest(
                        exposedDropdownMenuBoxModifier = Modifier.weight(.33f),
                        label = "Ay",
                        selectedOptionText = birthMonthselectedOptionText,
                        expended = birthMonthexpanded,
                        listOfOptions = birthMonthoptions,
                        textFieldModifier = Modifier
                            .weight(.33f)
                            .border(1.dp, AppColors.grey_130, RoundedCornerShape(5.dp)),
                        textFieldReadOnly = true,
                        textfieldColors = defaultTextFieldColor(null, true),
                        menuItemColors = dropdownMenuItemColors(null, true),
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))
                DropDownTextField(
                    request = DropDowndTextFieldRequest(
                        exposedDropdownMenuBoxModifier = Modifier.weight(.33f),
                        label = "Yıl",
                        selectedOptionText = birthYearselectedOptionText,
                        expended = birthYearexpanded,
                        listOfOptions = birthYearoptions,
                        textFieldModifier = Modifier
                            .weight(.33f)
                            .border(1.dp, AppColors.grey_130, RoundedCornerShape(5.dp)),
                        textFieldReadOnly = true,
                        textfieldColors = defaultTextFieldColor(null, true),
                        menuItemColors = dropdownMenuItemColors(null, true),
                    )
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
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
                        .border(1.dp, AppColors.grey_130, RoundedCornerShape(5.dp)),
                    textFieldReadOnly = true,
                    textfieldColors = defaultTextFieldColor(null, true),
                    menuItemColors = dropdownMenuItemColors(null, true),
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                HorizontalDivider(
                    modifier = Modifier.weight(.33f),
                    thickness = 1.dp,
                    color = AppColors.grey_113
                )

                Text(
                    text = "Referans Kodu",
                    modifier = Modifier
                        .weight(.33f)
                        .padding(10.dp)
                        .background(Color.White),
                    style = TextStyle(fontSize = 14.sp),
                    textAlign = TextAlign.Center,
                    color = AppColors.primaryGrey
                )
                HorizontalDivider(
                    modifier = Modifier.weight(.33f),
                    thickness = 1.dp,
                    color = AppColors.grey_113
                )
            }
            TextField(
                value = referanceCodeValue,
                onValueChange = { referanceCodeValue = it },
                label = { Text(text = "Referans kodunuz") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_warning_24),
                        contentDescription = null
                    )
                },
                colors = defaultTextFieldColor(null, true),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, AppColors.grey_130, RoundedCornerShape(5.dp))
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = { navController.navigate("addyourbusiness") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.primaryGrey,//context.getColor(R.color.gray99)
                    contentColor = Color.White,
                    disabledContainerColor = AppColors.primaryGrey,//context.getColor(R.color.gray99)
                    disabledContentColor = Color.White
                )
            ) {
                Text(text = "Devam Et", style = signupSubmitButton)
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(70.dp))
        }
    }
}