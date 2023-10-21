package com.isticpla.itp.signup

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.isticpla.itp.AppNavigate
import com.isticpla.itp.R
import com.isticpla.itp.data.countryListDB
import com.isticpla.itp.signup.ui.theme.ITPTheme
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.DropDownTextField
import com.isticpla.itp.uimodules.DropDowndTextFieldRequest
import com.isticpla.itp.uimodules.defaultTextFieldColor
import com.isticpla.itp.uimodules.dropdownMenuItemColors
import com.isticpla.itp.uimodules.dropdownTextFieldColors
import java.time.LocalDate

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ITPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                ) {
                    AppNavigate()
                }
            }
        }
    }
}

lateinit var context: Context

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
    context = LocalContext.current.applicationContext
    var phoneareaDropdownExpandState = remember { mutableStateOf(false) }
    var phoneNumberValue by remember { mutableStateOf("") }
    val (approveCheckedState, onStateChangeApprove) = remember { mutableStateOf(true) }
    val (pcontractCheckedState, onStateChangepContract) = remember { mutableStateOf(false) }
    Scaffold(
        modifier=Modifier.fillMaxSize(),
        containerColor=Color.White
    ) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .background(Color.White)
        ,
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
            Icon(painter = painterResource(id = R.drawable.arrow_right), contentDescription = null)
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
    context = LocalContext.current.applicationContext
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
            .padding(horizontal = 20.dp),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
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
    context = LocalContext.current.applicationContext
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
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        containerColor = Color.White
    ) { innerpadding ->
        Column(
            modifier = Modifier.padding(innerpadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 72.dp)
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
                colors =defaultTextFieldColor(null, true)
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
                        menuItemModifier = null
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
                        menuItemModifier = null
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
                        menuItemModifier = null
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
                    menuItemModifier = null
                )
            )

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun AddYourBusiness(navController: NavController) {

}

@Composable
fun ChooseBusinessSalesAreas(navController: NavController) {

}
