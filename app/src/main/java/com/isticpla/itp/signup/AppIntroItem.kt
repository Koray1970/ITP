package com.isticpla.itp.signup

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
internal fun AppIntroItem(
    context: Context,
    spotImage: Int,
    title: String,
    screenHeight: Int,
    content: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height((screenHeight * .9).dp)
            .padding(10.dp, 0.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = spotImage),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = title,
            style = defaultTextTitle,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = content,
            style = defaultText, textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        //Image(painter = painterResource(id = R.drawable.wizardpgr01), contentDescription = null)
    }
}