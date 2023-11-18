package com.isticpla.itp.splash

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.AppIntroData
import com.isticpla.itp.helpers.GetScreenSize

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