package com.isticpla.itp.signup

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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