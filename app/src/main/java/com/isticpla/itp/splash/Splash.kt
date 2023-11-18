package com.isticpla.itp.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.uimodules.AppColors
import kotlinx.coroutines.delay

@Composable
fun Splash(
    navController: NavController,
) {
    val context = LocalContext.current.applicationContext
    val backgroundcolor = Color(0x0493F060)

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(Color.White)
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(
                            AppColors.blue_100,//Color(context.getColor(R.color.splashbackground1))
                            AppColors.blue_102 //Color(context.getColor(R.color.splashbackground2))
                        ),
                        startY = size.height / 3,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.Multiply)
                    }
                },
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.splash_background),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.logo_white), contentDescription = null)
        }
    }
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("startselectculture")
    }
}