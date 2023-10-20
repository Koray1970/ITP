package com.isticpla.itp.signup

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.isticpla.itp.uimodules.AppColors

//start::change phone number annotate string
val changeCellNumber = "Değiştir"
fun sendCodeAgainString(phoneNum: String) = buildAnnotatedString {
    withStyle(
        style = signupHeaderSpanStyle
    )
    { append("Telefon Numaranızı Doğrulayın!") }
    append("\n")
    withStyle(
        style = SpanStyle(
            color = AppColors.primaryGrey,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal
        )
    )
    { append("SMS yoluyla gönderdiğimiz kodu giriniz!\n ${phoneNum}  ") }
    val end = length + changeCellNumber.length
    addStringAnnotation(
        tag = "changephonenumber",
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
    { append(changeCellNumber) }
}
//end::change phone number annotate string

//start::send code again annotate string
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
//end::send code again annotate string