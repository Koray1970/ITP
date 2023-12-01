package com.isticpla.itp.uimodules

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.isticpla.itp.R
import com.isticpla.itp.poppinFamily
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

private const val BlinkTime = 50
@Composable
fun AppProgress(
    displaystatus:MutableState<Boolean> = mutableStateOf(false)
) {
    if(displaystatus.value) {
        var visible by remember { mutableStateOf(true) }
        val text="Lütfen Bekleyin..."
        LaunchedEffect(key1 = text) {
            visible = false
            delay(BlinkTime.milliseconds)
            visible = true
        }

        Scaffold(
            containerColor = Color.White.copy(.86f)
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(30.dp,Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_blue),
                    contentDescription = null,
                    modifier = Modifier.alpha(.8f)
                )
                AnimatedVisibility(
                    modifier = Modifier,
                    visible = visible,
                    enter = fadeIn(animationSpec = TweenSpec(durationMillis = 0)),
                    exit = fadeOut(animationSpec = TweenSpec(durationMillis = 0))
                ) {
                    Text(text=text,
                        style = TextStyle(
                            fontFamily = poppinFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = AppColors.blue_0xFF0495f1
                        )
                    )
                }

            }
        }
    }
}