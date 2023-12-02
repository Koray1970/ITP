package com.isticpla.itp.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.database.Account
import com.isticpla.itp.database.AccountViewModel
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.AppDatePicker
import com.isticpla.itp.uimodules.AppDropdown
import com.isticpla.itp.uimodules.AppTextField
import com.isticpla.itp.uimodules.AppTextFieldDefaults
import com.isticpla.itp.uimodules.AppTextFieldDefaults.Companion.TextFieldKeyboardOptions
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun CreateUserAccount(navController: NavController) {
    val context = LocalContext.current.applicationContext
    val scope = rememberCoroutineScope()
    val homeviewmodel = hiltViewModel<HomeViewMode>()
    val accountViewModel = hiltViewModel<AccountViewModel>()
    val getaccountdb =
        accountViewModel.getAccount.collectAsStateWithLifecycle(initialValue = Account())

    var nameValue = rememberSaveable { mutableStateOf("") }
    var nameError = remember { mutableStateOf(false) }
    //val nameMaxLength = 60
    var lastNameValue = rememberSaveable { mutableStateOf("") }
    var lastNameError = remember { mutableStateOf(false) }
    //val lastNameMaxLength = 60

    var emailValue = rememberSaveable { mutableStateOf("") }
    var emailError = remember { mutableStateOf(false) }
    //val emailMaxLength = 360

    val birthdateValue = rememberSaveable { mutableStateOf("") }
    var birthdateError = remember { mutableStateOf(false) }
    var datepickerDialogState = remember { mutableStateOf(false) }


    val countryoptions =
        homeviewmodel.countryList.collectAsStateWithLifecycle(initialValue = emptyList<Pair<String, String>>())
    var countryexpanded = remember { mutableStateOf(false) }
    var countryselectedOption = remember { mutableStateOf<String>("") }
    var countryselectedKey = remember { mutableStateOf<String>("") }
    var countryError = remember { mutableStateOf(false) }

    var referanceCodeValue = rememberSaveable { mutableStateOf("") }
    /*var referanceCodeError = remember { mutableStateOf(false) }
    val referanceCodeMaxLength = 16*/

    LaunchedEffect(Unit) {
        delay(400)
        if (!getaccountdb.value.name.isNullOrEmpty())
            nameValue.value = getaccountdb.value.name!!
        if (!getaccountdb.value.lastname.isNullOrEmpty())
            lastNameValue.value = getaccountdb.value.lastname!!
        if (!getaccountdb.value.email.isNullOrEmpty())
            emailValue.value = getaccountdb.value.email!!
        if (!getaccountdb.value.birthdate.isNullOrEmpty())
            birthdateValue.value = getaccountdb.value.birthdate!!
        if (!getaccountdb.value.country.isNullOrEmpty()) {
            countryselectedKey.value = getaccountdb.value.country!!

            homeviewmodel.countryResult(countryselectedKey.value).collectLatest {
                countryselectedOption.value = it.first().second
            }
        }

    }

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
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        10.dp,
                        Alignment.CenterHorizontally
                    )
                ) {
                    AppTextField(
                        modifier = AppTextFieldDefaults.TextFieldDefaultModifier(
                            fillmaxwidth = .5f,
                            iserror = nameError
                        ),
                        txtvalue = nameValue,
                        label = {
                            Text(
                                text = "Adınız",
                                style = AppTextFieldDefaults.TextFieldTextStyle
                            )
                        },
                        isError = nameError,
                        singleLine = true,
                    )
                    AppTextField(
                        modifier = AppTextFieldDefaults.TextFieldDefaultModifier(
                            fillmaxwidth = 1f,
                            iserror = nameError
                        ),
                        txtvalue = lastNameValue,
                        label = {
                            Text(
                                text = "Soyadınız",
                                style = AppTextFieldDefaults.TextFieldTextStyle
                            )
                        },
                        isError = lastNameError,
                        singleLine = true,
                    )
                }
                AppTextField(
                    modifier = AppTextFieldDefaults.TextFieldDefaultModifier(fillmaxwidth = 1f),
                    txtvalue = emailValue,
                    label = {
                        Text(
                            text = "E-posta adresiniz",
                            style = AppTextFieldDefaults.TextFieldTextStyle
                        )
                    },
                    isError = emailError,
                    singleLine = true,
                    keyboardOptions = TextFieldKeyboardOptions(
                        keyboardtype = KeyboardType.Email,
                        capitalization = KeyboardCapitalization.None
                    )
                )
                AppTextField(
                    modifier = AppTextFieldDefaults.TextFieldDefaultModifier(fillmaxwidth = 1f),
                    txtvalue = birthdateValue,
                    label = {
                        Text(
                            text = "Doğum tarihiniz",
                            style = AppTextFieldDefaults.TextFieldTextStyle
                        )
                    },
                    leadingIcon = {
                        IconButton(onClick = { datepickerDialogState.value = true }) {
                            Icon(
                                painter = painterResource(id = R.drawable.round_calendar_month_24),
                                contentDescription = null
                            )
                        }
                    },
                    isError = birthdateError,
                    singleLine = true
                )
                AppDropdown(
                    selectedOptionText = countryselectedOption,
                    selectedOptionKey = countryselectedKey,
                    expended = countryexpanded,
                    listdata = countryoptions.value,
                    isError = countryError,
                    dropdownlabel = {
                        Text(
                            "Ülke",
                            style = AppTextFieldDefaults.TextFieldTextStyle
                        )
                    }
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
                AppTextField(
                    modifier = AppTextFieldDefaults.TextFieldDefaultModifier(fillmaxwidth = 1f),
                    txtvalue = referanceCodeValue,
                    label = {
                        Text(
                            text = "Referans kodunuz",
                            style = AppTextFieldDefaults.TextFieldTextStyle
                        )
                    },
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.round_warning_amber_24),
                            contentDescription = null
                        )
                    },
                    keyboardOptions = TextFieldKeyboardOptions(
                        keyboardtype = KeyboardType.Ascii,
                        capitalization = KeyboardCapitalization.Characters
                    )
                )
                Button(
                    onClick = {
                        if (nameValue.value.isEmpty() || lastNameValue.value.isEmpty() || emailValue.value.isEmpty() || countryselectedOption.value.isEmpty()) {
                            nameError.value = nameValue.value.isEmpty()
                            lastNameError.value = lastNameValue.value.isEmpty()
                            emailError.value = emailValue.value.isEmpty()
                            countryError.value = countryselectedOption.value.isEmpty()

                        } else {
                            scope.launch {
                                getaccountdb.value.name = nameValue.value
                                getaccountdb.value.lastname = lastNameValue.value
                                getaccountdb.value.email = emailValue.value
                                getaccountdb.value.birthdate = birthdateValue.value
                                getaccountdb.value.country = countryselectedKey.value
                                if (referanceCodeValue.value.isNotEmpty())
                                    getaccountdb.value.refcode = referanceCodeValue.value
                                accountViewModel.UpsertAccount(getaccountdb.value)
                                delay(200)
                                navController.navigate("addyourbusiness")
                            }
                        }
                    },
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
            }
            Spacer(modifier = Modifier.height(70.dp))
        }
    }
    AppDatePicker(
        onDateSelected = birthdateValue,
        onDatePickerDismissState = datepickerDialogState,
        title = {
            Text(
                text = "Doğum Tarihini Seçiniz",
                style = TextStyle(
                    fontFamily = poppinFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    )
}