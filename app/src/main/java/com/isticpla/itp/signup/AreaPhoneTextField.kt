package com.isticpla.itp.signup

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.isticpla.itp.R
import com.isticpla.itp.uimodules.defaultTextFieldColor

@Composable
fun AreaPhoneTextField(modifier: Modifier = Modifier, context: Context) {

    var fieldValue by rememberSaveable { mutableStateOf("") }
    val mMaxLength = 15
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .requiredWidth(width = 331.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .background(color = Color.White)
            .border(
                border = BorderStroke(1.dp, Color(0xff9ba5b7)),
                shape = RoundedCornerShape(5.dp)
            )
            .padding(
                horizontal = 16.dp,
                vertical = 7.dp
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "US +1",
                color = Color(0xff545f71),
                lineHeight = 10.em,
                style = TextStyle(
                    fontSize = 14.sp
                )
            )
            Icon(
                painter = painterResource(id = R.drawable.selector_updown),
                contentDescription = null
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .requiredWidth(width = 28.dp)
                .rotate(degrees = 90f), color = Color(0xff9ba5b7)
        )
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextField(
                value = fieldValue,
                onValueChange = {
                    if (it.length <= mMaxLength)
                        fieldValue = it
                },
                label = { Text("Telefon numaranız") },
                placeholder = { Text("") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    autoCorrect = false
                ),
                colors = defaultTextFieldColor(context.getColor(R.color.grayprimary))
            )
            /*Text(
                text = "Phone Number",
                color = Color(0xffc1c1c1),
                lineHeight = 11.67.em,
                style = TextStyle(
                    fontSize = 12.sp),
                modifier = Modifier
                    .fillMaxWidth())
            Text(
                text = "Phone Number",
                color = Color(0xff545f71),
                lineHeight = 10.em,
                style = TextStyle(
                    fontSize = 14.sp),
                modifier = Modifier
                    .fillMaxWidth())*/
        }
    }
}

