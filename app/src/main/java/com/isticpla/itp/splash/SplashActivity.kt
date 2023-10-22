package com.isticpla.itp.splash

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import com.isticpla.itp.AppNavigate
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.AppIntroData
import com.isticpla.itp.helpers.GetScreenSize
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.signup.signupSubmitButton
import com.isticpla.itp.splash.ui.theme.ITPTheme
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.DefaultRoundedCornerButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ITPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .safeDrawingPadding()
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(0.dp),
                    color = Color.White
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartSelectCulture(
    navController: NavController,
) {
    val context = LocalContext.current.applicationContext
    var cultureDropdownExpandState by remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("home") }) {
                Icon(painter = painterResource(id = R.drawable.baseline_arrow_forward_24), contentDescription = null)
            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay
    ) { innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.logo_blue), contentDescription = null)
            Spacer(modifier = Modifier.height(40.dp))
            Box(
                modifier = Modifier
                    .width(265.dp)
                    .wrapContentSize(Alignment.TopStart)
            ) {
                OutlinedCard(
                    modifier = Modifier
                        .width(265.dp)
                        .height(51.dp)
                        .border(1.dp, Color(0x9BA5B7FF), RoundedCornerShape(6.dp)),
                    shape = RoundedCornerShape(6.dp),
                    onClick = { cultureDropdownExpandState = true }) {
                    ListItem(
                        headlineContent = {
                            Text(
                                "Lisan Seçiniz",
                                style = TextStyle(color = Color.Gray, fontWeight = FontWeight.Bold)
                            )
                        },
                        supportingContent = {
                            Text(
                                "Türkçe",
                                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
                            )
                        },
                        leadingContent = {
                            Image(
                                painter = painterResource(id = R.drawable.flg_tr),
                                modifier = Modifier.size(48.dp),
                                contentDescription = null
                            )
                        },
                        trailingContent = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_expand_more_24),
                                contentDescription = null
                            )
                        }
                    )
                }
                DropdownMenu(
                    modifier = Modifier.width(265.dp),
                    expanded = cultureDropdownExpandState,
                    onDismissRequest = { cultureDropdownExpandState = false }) {
                    DropdownMenuItem(
                        text = { Text("Türkçe") },
                        onClick = { cultureDropdownExpandState = false },
                        leadingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.flg_tr),
                                modifier = Modifier.size(38.dp),
                                contentDescription = null
                            )
                        })
                    DropdownMenuItem(
                        text = { Text("English") },
                        onClick = { cultureDropdownExpandState = false },
                        leadingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.flg_uk),
                                modifier = Modifier.size(38.dp),
                                contentDescription = null
                            )
                        })
                    DropdownMenuItem(
                        text = { Text("Français") },
                        onClick = { cultureDropdownExpandState = false },
                        leadingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.flg_fr),
                                modifier = Modifier.size(38.dp),
                                contentDescription = null
                            )
                        })
                    DropdownMenuItem(
                        text = { Text("Deutsch") },
                        onClick = { cultureDropdownExpandState = false },
                        leadingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.flg_de),
                                modifier = Modifier.size(38.dp),
                                contentDescription = null
                            )
                        })

                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("appintro") },
                modifier = Modifier
                    .width(265.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.primaryGrey,//context.getColor(R.color.gray99)
                    contentColor = Color.White,
                    disabledContainerColor = AppColors.primaryGrey,//context.getColor(R.color.gray99)
                    disabledContentColor = Color.White
                )
            ) {
                Text(text = "Başla", style = signupSubmitButton(context))
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(26.dp))
            Text(
                text = "Kaydolarak Şartlarımızı kabul etmiş olursunuz. Verilerinizi nasıl kullandığımızı Gizlilik Politikamızda görün.",
                style = TextStyle(
                    fontFamily = poppinFamily,
                    fontSize = 16.sp,
                    color = AppColors.primaryGrey,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.width(320.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppIntro(navController: NavController) {
    val context = LocalContext.current.applicationContext
    val screenSize = GetScreenSize()
    val listofIntro = AppIntroData()
    val pagerState = rememberPagerState(pageCount = { listofIntro.size })

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(Color.White),
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("signup") }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_skip_next_24),
                    contentDescription = null
                )
            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay
    ) {
        Column(
            Modifier
                .padding(it)
                .background(Color.White)
        ) {
            HorizontalPager(
                state = pagerState,
            ) { ix ->
                val appIntro = listofIntro[ix]
                AppIntroItem(
                    context,
                    appIntro.img,
                    appIntro.title,
                    screenSize.height,
                    appIntro.content
                )
            }
            Row(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(listofIntro.size) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(20.dp)

                    )
                }

            }
        }
    }
}

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
            style = defaultTextTitle(context = context),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = content,
            style = defaultText(context = context), textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        //Image(painter = painterResource(id = R.drawable.wizardpgr01), contentDescription = null)
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
