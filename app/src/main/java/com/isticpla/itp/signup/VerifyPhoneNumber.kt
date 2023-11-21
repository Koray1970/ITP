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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
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
    val codesize = 4
    val textFieldInactive = Pair<Color, Color>(
        AppColors.grey_0xFFF7F8F9,
        AppColors.grey_0xFFE8ECF4
    )
    val textFieldActive = Pair<Color, Color>(
        Color.White, AppColors.blue_0xFF0495f1
    )
    val textFieldIsError = Pair<Color, Color>(
        AppColors.grey_0xFFF7F8F9,
        AppColors.red_0xffe23e3e
    )
    var isFormValid by remember { mutableStateOf(true) }
    val smsvalidatecode = remember { mutableStateListOf<Int?>() }
    val maxChar = 1
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

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
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(codesize) { t ->
                    var textfieldColorize by remember { mutableStateOf(textFieldInactive) }
                    BasicTextField(
                        value = if (smsvalidatecode.isNotEmpty()) {
                            if (smsvalidatecode.size > t)
                                if (smsvalidatecode.get(t) != null)
                                    smsvalidatecode.get(t).toString()
                                else
                                    ""
                            else
                                ""
                        } else "",
                        onValueChange = { v ->
                            if (v.length == 1) {
                                if (smsvalidatecode.isNotEmpty()) {
                                    if (smsvalidatecode.size > t) {
                                        smsvalidatecode?.set(t, v.toInt())
                                    } else {
                                        smsvalidatecode.add(v.toInt())
                                    }
                                } else
                                    smsvalidatecode.add(v.toInt())

                                if (t < codesize - 1)
                                    focusManager.moveFocus(focusDirection = FocusDirection.Next)
                            } else {
                                smsvalidatecode?.set(t, null)
                            }

                            if (smsvalidatecode[t] != null)
                                textfieldColorize = textFieldActive
                            else {
                                textfieldColorize = if (!isFormValid)
                                    textFieldIsError
                                else
                                    textFieldInactive
                            }
                        },
                        textStyle = validatePhoneTextFieldTextStyle,
                        singleLine = true,
                        modifier = Modifier
                            .background(textfieldColorize.first)
                            .wrapContentHeight(align = Alignment.Bottom)
                            .width(67.dp)
                            .height(60.dp)
                            .border(
                                1.dp, textfieldColorize.second,
                                RoundedCornerShape(8.dp)
                            )
                            .focusRequester(focusRequester),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.NumberPassword,
                            imeAction = if (t < codesize) ImeAction.Next else ImeAction.None,
                            autoCorrect = false,
                        ),
                        decorationBox = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                it()
                            }
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    if (smsvalidatecode.isNullOrEmpty()) {
                        isFormValid = false
                    } else {
                        if (smsvalidatecode.size < codesize) {
                            isFormValid = false
                        } else {
                            if (smsvalidatecode.any { a -> a == null }) {
                                isFormValid = false
                            } else
                                navController.navigate("createuseraccount")
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