package com.isticpla.itp.splash

import android.media.Image
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.isticpla.itp.AppNavigate
import com.isticpla.itp.R
import com.isticpla.itp.splash.ui.theme.ITPTheme
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ITPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigate()
                }
            }
        }
    }
}

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


    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(
                            Color(context.getColor(R.color.splashbackground1)),
                            Color(context.getColor(R.color.splashbackground2))
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartSelectCulture(
    navController: NavController,
) {
    var cultureDropdownExpandState by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.logo_blue), contentDescription = null)
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentSize(Alignment.TopStart)
        ) {
            OutlinedCard(
                modifier = Modifier.width(300.dp),
                onClick = { cultureDropdownExpandState = true }) {
                ListItem(
                    headlineContent = { Text("Lisan Seçiniz") },
                    supportingContent = {
                        Text("Secondary text that is long and perhaps goes onto another line")
                    },
                    leadingContent = {
                        Icon(
                            Icons.Filled.ArrowDropDown,
                            contentDescription = null,
                        )
                    },
                    trailingContent = { Text("meta") }
                )
            }
            DropdownMenu(
                expanded = cultureDropdownExpandState,
                onDismissRequest = { cultureDropdownExpandState = false }) {
                DropdownMenuItem(
                    text = { Text("Edit") },
                    onClick = { /* Handle edit! */ },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Edit,
                            contentDescription = null
                        )
                    })
                DropdownMenuItem(
                    text = { Text("Settings") },
                    onClick = { /* Handle settings! */ },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Settings,
                            contentDescription = null
                        )
                    })
            }
        }

    }
}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ITPTheme {
        Greeting2("Android")
    }
}*/
