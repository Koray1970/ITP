package com.isticpla.itp.uimodules

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.isticpla.itp.R

@Composable
fun DefaultRoundedCornerButton(
    context: Context,
    modifier: Modifier,
    title: String,
    onclick: () -> Unit,
): Unit {
    val button =
        Button(
            modifier = modifier,
            onClick = onclick ,
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppColors.primaryGrey,//Color(context.getColor(R.color.startbuttonbackground))
                disabledContainerColor = AppColors.primaryGreyDisabled,//Color(context.getColor(R.color.startbuttonbackgrounddisabled))
                contentColor = Color.White,
                disabledContentColor = Color.White
            )
        ) {
            Text(
                text = title,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_forward_24),
                contentDescription = null
            )
        }
    return button
}