package com.isticpla.itp.signup

import android.content.Context
import androidx.compose.ui.text.AnnotatedString

class SignUpHeaderRequest {
    var title: String? = null
    var subtitle: String? = null
    var annotatedStringRequest: AnnotatedStringRequest? = null
}

class AnnotatedStringRequest {
    lateinit var annotatedString: AnnotatedString
    lateinit var annotatedText:String
    lateinit var tag: String
    lateinit var event: () -> Unit
}