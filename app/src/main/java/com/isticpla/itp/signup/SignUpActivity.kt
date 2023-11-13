package com.isticpla.itp.signup

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.isticpla.itp.AppNavigate
import com.isticpla.itp.R
import com.isticpla.itp.data.countryListDB
import com.isticpla.itp.dummydata.BusinessTypeItem
import com.isticpla.itp.dummydata.listofBusiness
import com.isticpla.itp.dummydata.listofEmployeePosition
import com.isticpla.itp.signup.ui.theme.ITPTheme
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.DropDownTextField
import com.isticpla.itp.uimodules.DropDowndTextFieldRequest
import com.isticpla.itp.uimodules.defaultTextFieldColor
import com.isticpla.itp.uimodules.dropdownMenuItemColors
import com.isticpla.itp.uimodules.dropdownTextFieldColors
import java.time.LocalDate

class SignUpActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ITPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize().padding(0.dp).background(Color.White),
                    color=MaterialTheme.colorScheme.background
                ) {
                    AppNavigate()
                }
            }
        }
    }
}

@Composable
fun SingUpHeader(context: Context, request: SignUpHeaderRequest) {
    if (request.title != null && request.subtitle != null) {
        Text(
            text = request.title!!,//context.getString(R.string.reg100),
            style = signupHeader(context),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = request.subtitle!!,//context.getString(R.string.reg102),
            style = signupSubTitle(context),
            modifier = Modifier.fillMaxWidth()
        )
    }
    if (request.annotatedStringRequest != null) {
        val anno = request.annotatedStringRequest!!.annotatedString
        ClickableText(
            text = anno,
            onClick = { offset ->
                anno.getStringAnnotations(
                    tag = request.annotatedStringRequest!!.tag,
                    start = offset,
                    end = offset
                )
                    .firstOrNull()
                    ?.let {
                        request.annotatedStringRequest!!.event.invoke()
                    }
            })
    }
    Spacer(modifier = Modifier.height(80.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(navController: NavController) {
    val context = LocalContext.current.applicationContext
    var phoneareaDropdownExpandState = remember { mutableStateOf(false) }
    var phoneNumberValue by remember { mutableStateOf("") }
    val (approveCheckedState, onStateChangeApprove) = remember { mutableStateOf(true) }
    val (pcontractCheckedState, onStateChangepContract) = remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        containerColor = Color.White
    ) { innerpadding ->

        Column(
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val headerReq = SignUpHeaderRequest()
            headerReq.title = context.getString(R.string.reg100)
            headerReq.subtitle = context.getString(R.string.reg102)
            SingUpHeader(context, headerReq)
            AreaPhoneTextField(modifier = Modifier, context)
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .toggleable(
                        value = approveCheckedState,
                        onValueChange = { onStateChangeApprove(!approveCheckedState) },
                        role = Role.Checkbox
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = approveCheckedState,
                    onCheckedChange = null, // null recommended for accessibility with screenreaders
                    colors = signCheckBoxColors(context)
                )
                Text(
                    text = "Özel bildirim, güncelleme ve haberler hakkında tarafımla e-posta ve SMS ile iletişime geçilmesini istiyorum.",
                    style = signupCheckboxLabel(context),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .toggleable(
                        value = pcontractCheckedState,
                        onValueChange = { onStateChangepContract(!pcontractCheckedState) },
                        role = Role.Checkbox
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = pcontractCheckedState,
                    onCheckedChange = null, // null recommended for accessibility with screenreaders
                    colors = signCheckBoxColors(context)
                )
                Text(
                    text = "Üyelik Sözleşmesini okudum ve kabul ediyorum.",
                    style = signupCheckboxLabel(context),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { navController.navigate("verifyphonenumber") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.primaryGrey,//Color(context.getColor(R.color.gray99))
                    contentColor = Color.White,
                    disabledContainerColor = AppColors.primaryGrey,//Color(context.getColor(R.color.gray99))
                    disabledContentColor = Color.White
                )
            ) {
                Text(text = "Devam Et", style = signupSubmitButton(context))
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Burada yer alan bilgilerinizi asla kimseyle paylaşmıyoruz, bilgilerinizi profilinizden değiştirebilirsiniz!",
                style = signupSegmentTitle(context),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Composable
fun VerifyPhoneNumber(
    navController: NavController,
) {
    val context = LocalContext.current.applicationContext
    var ph1 by rememberSaveable { mutableStateOf("") }
    val textFieldinactive = Pair<Color, Color>(
        AppColors.grey_109,//context.getColor(R.color.gray109),
        AppColors.grey_113//context.getColor(R.color.gray107)
    )
    val textFieldactive =
        Pair<Color, Color>(Color.White, AppColors.blue_104)//context.getColor(R.color.blue002)
    //color > first background, second border
    var ph1FocusColor by remember { mutableStateOf(textFieldinactive) }
    var ph2 by rememberSaveable { mutableStateOf("") }
    var ph2FocusColor by remember { mutableStateOf(textFieldinactive) }
    var ph3 by rememberSaveable { mutableStateOf("") }
    var ph3FocusColor by remember { mutableStateOf(textFieldinactive) }
    var ph4 by rememberSaveable { mutableStateOf("") }
    var ph4FocusColor by remember { mutableStateOf(textFieldinactive) }
    val maxChar = 1

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(Color.White)
            .padding(horizontal = 20.dp),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val headerReq = SignUpHeaderRequest()
            headerReq.annotatedStringRequest = AnnotatedStringRequest()
            headerReq.annotatedStringRequest!!.annotatedString =
                sendCodeAgainString("+905555555555")
            headerReq.annotatedStringRequest!!.tag = "changephonenumber"
            headerReq.annotatedStringRequest!!.event = { navController.navigate("signup") }
            SingUpHeader(context, headerReq)

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = ph1,
                    onValueChange = {
                        if (it.length <= 1)
                            ph1 = it
                        ph1FocusColor = if (it.isNotEmpty()) textFieldactive else textFieldinactive
                    },
                    textStyle = validatePhoneTextFieldTextStyle(context),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    modifier = Modifier
                        .width(67.dp)
                        .height(60.dp)
                        .border(
                            1.dp, ph1FocusColor.second,
                            RoundedCornerShape(8.dp)
                        ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next,
                        autoCorrect = false
                    ),
                    colors = defaultTextFieldColor(null, true)
                )
                Spacer(Modifier.weight(1f))
                TextField(
                    value = ph2,
                    onValueChange = {
                        if (it.length <= 1)
                            ph2 = it
                        ph2FocusColor = if (it.isNotEmpty()) textFieldactive else textFieldinactive
                    },
                    textStyle = validatePhoneTextFieldTextStyle(context),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    modifier = Modifier
                        .width(67.dp)
                        .height(60.dp)
                        .border(
                            1.dp, ph2FocusColor.second,
                            RoundedCornerShape(8.dp)
                        ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next,
                        autoCorrect = false
                    ),
                    colors = defaultTextFieldColor(null, true)
                )
                Spacer(Modifier.weight(1f))
                TextField(
                    value = ph3,
                    onValueChange = {
                        if (it.length <= 1)
                            ph3 = it
                        ph3FocusColor = if (it.isNotEmpty()) textFieldactive else textFieldinactive
                    },
                    textStyle = validatePhoneTextFieldTextStyle(context),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    modifier = Modifier
                        .width(67.dp)
                        .height(60.dp)
                        .border(
                            1.dp, ph3FocusColor.second,
                            RoundedCornerShape(8.dp)
                        ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next,
                        autoCorrect = false
                    ),
                    colors = defaultTextFieldColor(null, true)
                )
                Spacer(Modifier.weight(1f))
                TextField(
                    value = ph4,
                    onValueChange = {
                        if (it.length <= 1)
                            ph4 = it
                        ph4FocusColor = if (it.isNotEmpty()) textFieldactive else textFieldinactive
                    },
                    textStyle = validatePhoneTextFieldTextStyle(context),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    modifier = Modifier
                        .width(67.dp)
                        .height(60.dp)
                        .border(
                            1.dp, ph4FocusColor.second,
                            RoundedCornerShape(8.dp)
                        ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        autoCorrect = false
                    ),
                    colors = defaultTextFieldColor(null, true)
                )

            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { navController.navigate("createuseraccount") },
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
                Text(text = "Doğrula", style = signupSubmitButton(context))
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            val sendCodeAgain = "Tekrar Gönder"
            val sendCodeAgainString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = AppColors.primaryGrey,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
                { append("Kod almadınız mı?\n") }
                val end = length + sendCodeAgain.length
                addStringAnnotation(
                    tag = "sendcodeagain",
                    annotation = "",
                    start = length,
                    end = end
                )
                withStyle(
                    style = SpanStyle(
                        color = AppColors.blue_104,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
                { append(sendCodeAgain) }

            }
            ClickableText(
                text = sendCodeAgainString,
                onClick = { offset ->
                    sendCodeAgainString.getStringAnnotations(
                        tag = "sendcodeagain",
                        start = offset,
                        end = offset
                    )
                        .firstOrNull()
                        ?.let { navController.navigate("signup") }
                })

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
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
                Text(text = "Devam Et", style = signupSubmitButton(context))
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

@Composable
fun AddYourBusiness(navController: NavController) {
    val context = LocalContext.current.applicationContext
    var companynameValue by rememberSaveable { mutableStateOf("") }
    var companynameError by remember { mutableStateOf(false) }

    var positionValue = rememberSaveable { mutableStateOf("") }
    var positionExpand = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
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
                        .background(color = AppColors.yellow_100)
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
            headerReq.title = "İşletmenizi ekleyin"
            headerReq.subtitle = "İşletmenizin bilgilerini giriniz!"
            SingUpHeader(context = context, request = headerReq)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .border(5.dp, Color.White, RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .shadow(4.dp, RoundedCornerShape(10.dp), false)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_insert_photo_24),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "İşletme Logo",
                        style = TextStyle(
                            lineHeight = 14.em,
                            fontSize = 12.sp,
                            color = AppColors.grey_118
                        )
                    )
                    Text(
                        text = "1024 x 1024, .png, .jpg",
                        style = TextStyle(
                            lineHeight = 15.em,
                            fontSize = 14.sp,
                            color = AppColors.primaryGrey
                        ),
                        modifier = Modifier.padding(vertical = 3.dp)
                    )
                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppColors.blue_104,
                            contentColor = Color.White,
                            disabledContainerColor = AppColors.blue_104,
                            disabledContentColor = Color.White
                        ),
                        modifier = Modifier.requiredHeight(48.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_edit_24),
                            contentDescription = null
                        )
                        Text(
                            text = "Logo Yükle",
                            style = TextStyle(fontSize = 16.sp),
                            modifier = Modifier.padding(horizontal = 6.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            TextField(
                value = companynameValue,
                onValueChange = {
                    companynameValue = it
                },
                label = { Text("İşletme Adı (zorunlu)") },
                colors = defaultTextFieldColor(null, true),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, AppColors.grey_130, RoundedCornerShape(5.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))
            DropDownTextField(
                request = DropDowndTextFieldRequest(
                    exposedDropdownMenuBoxModifier = Modifier.fillMaxWidth(),
                    label = "İşletmediki Pozisyonunuz",
                    selectedOptionText = positionValue,
                    expended = positionExpand,
                    listOfOptions = listofEmployeePosition,
                    textFieldModifier = Modifier
                        .fillMaxWidth()
                        .border(
                            1.dp,
                            AppColors.grey_130,
                            RoundedCornerShape(5.dp)
                        ),
                    textFieldReadOnly = true,
                    textfieldColors = dropdownTextFieldColors(null, true),
                    menuItemColors = dropdownMenuItemColors(null, true),
                )
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = { navController.navigate("choosebusinesssalesareas") },
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
                Text(text = "Devam Et", style = signupSubmitButton(context))
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = null
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseBusinessSalesAreas(navController: NavController) {
    val context = LocalContext.current.applicationContext
    var selectedBusinessTypes = mutableListOf<BusinessTypeItem>()
    //var flowselectedBusinessTypes = Flow<ArrayList<BusinessTypeItem>>()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
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
                        .background(color = AppColors.yellow_100)
                )
                Spacer(modifier = Modifier.weight(.05f))
                Box(
                    modifier = Modifier
                        .weight(.33f)
                        .height(10.dp)
                        .background(color = AppColors.yellow_100)
                )
            }
            val headerReq = SignUpHeaderRequest()
            headerReq.title = "İşletmenizin satış alanlarını seçin"
            headerReq.subtitle =
                "Bu bilgileri işletmenizi daha yakından tanımak ve daha doğru tavsiyeler paylaşmak için istiyoruz"
            SingUpHeader(context = context, request = headerReq)
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Sektörler",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AppColors.grey_124
                ),
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .horizontalScroll(
                        rememberScrollState()
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(3.dp, Alignment.Start)
            ) {
                listofBusiness.forEach { b ->
                    var isDisabledBussing by remember { mutableStateOf(true) }
                    Card(
                        colors = CardColors(
                            containerColor = AppColors.grey_133,
                            contentColor = AppColors.primaryGrey,
                            disabledContainerColor = AppColors.grey_133,
                            disabledContentColor = AppColors.grey_135
                        ),
                        enabled = isDisabledBussing,
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .width(70.dp)
                            .height(92.dp)
                            .alpha(if (!isDisabledBussing) .25f else 1f),
                        onClick = {
                            selectedBusinessTypes.add(b)
                            isDisabledBussing = false
                        }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = b.icon!!),
                                contentDescription = null,
                                tint = AppColors.primaryGrey
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = b.label!!,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AppColors.primaryGrey
                                )
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Seçilen sektörler",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AppColors.grey_124
                ),
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .horizontalScroll(
                        rememberScrollState()
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(3.dp, Alignment.Start)
            ) {
                selectedBusinessTypes.forEach { b ->
                    Card(
                        colors = CardColors(
                            containerColor = AppColors.primaryGrey,
                            contentColor = Color.White,
                            disabledContainerColor = AppColors.primaryGrey,
                            disabledContentColor = Color.White
                        ),
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .width(70.dp)
                            .height(92.dp),
                        onClick = {

                        }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = b.icon!!),
                                contentDescription = null,
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = b.label!!,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = { navController.navigate("home")},//
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
                Text(text = "Devam Et", style = signupSubmitButton(context))
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
