package com.isticpla.itp.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.defaultTextFieldColor

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
                    textStyle = validatePhoneTextFieldTextStyle,
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
                    textStyle = validatePhoneTextFieldTextStyle,
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
                    textStyle = validatePhoneTextFieldTextStyle,
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
                    textStyle = validatePhoneTextFieldTextStyle,
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
                Text(text = "Doğrula", style = signupSubmitButton)
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