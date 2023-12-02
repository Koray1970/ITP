package com.isticpla.itp.signup

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.Screen
import com.isticpla.itp.dummydata.AppIntroData
import com.isticpla.itp.helpers.GetScreenSize
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.AppProgress
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppIntro(navController: NavController) {
    val context = LocalContext.current.applicationContext
    val homeviewmodel = hiltViewModel<HomeViewMode>()
    val progressState = remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    val screenSize = GetScreenSize()
    val listofIntro = homeviewmodel.appIntroList.collectAsState(initial = emptyList())
    val sizeofAppIntro by remember{ mutableStateOf(listofIntro.value.size) }
    val pagerState = rememberPagerState(pageCount = { sizeofAppIntro })

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(Color.White),
        /* floatingActionButton = {
             FloatingActionButton(onClick = { navController.navigate("signup") }) {
                 Icon(
                     painter = painterResource(id = R.drawable.baseline_skip_next_24),
                     contentDescription = null
                 )
             }
         },
         floatingActionButtonPosition = FabPosition.EndOverlay*/
    ) { innerpadding ->
        SideEffect {
            progressState.value = false
        }
        Box() {
            Column(
                Modifier
                    .padding(innerpadding)
                    .background(Color.White)
            ) {
                HorizontalPager(
                    state = pagerState,
                ) { ix ->
                    val appIntro = listofIntro.value[ix]
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
                    var wsize = 20.dp
                    var hsize = 20.dp
                    var _shape = CircleShape
                    var color = AppColors.greyLight
                    repeat(sizeofAppIntro) { iteration ->

                        if (pagerState.currentPage == iteration) {
                            color = AppColors.blue_104
                            _shape = RoundedCornerShape(24.dp)
                            wsize = 44.dp
                        } else {
                            color = AppColors.greyLight
                            _shape = CircleShape
                            wsize = 20.dp
                        }

                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .clip(_shape)
                                .background(color)
                                .size(wsize, hsize)

                        )
                    }

                }
            }
        }
        Box(
            modifier = Modifier
                .offset(0.dp, 40.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage > 0)
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    },
                    enabled = (pagerState.currentPage > 0),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = AppColors.secondaryGrey,
                        disabledContainerColor = Color.White,
                        disabledContentColor = AppColors.secondaryGrey.copy(.5f),
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = null
                    )
                }
                Button(
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage <= sizeofAppIntro)
                                if (pagerState.currentPage == sizeofAppIntro - 1) {
                                    progressState.value = true
                                    delay(300L)
                                    navController.navigate(Screen.SignUp.route)
                                } else
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = AppColors.secondaryGrey
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_right),
                        contentDescription = null
                    )
                }
            }
        }
    }
    AppProgress(progressState)
}