package com.isticpla.itp

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.isticpla.itp.dummydata.listofMessages
import com.isticpla.itp.dummydata.listofNotifications
import com.isticpla.itp.dummydata.listofTasks
import com.isticpla.itp.home.*
import com.isticpla.itp.ui.theme.ITPTheme
import com.isticpla.itp.uimodules.AppColors
import dagger.hilt.android.lifecycle.HiltViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ITPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp)
                        .background(Color.White),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigate()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun Home(
    navController: NavController,
    homeViewMode: HomeViewMode = hiltViewModel()
) {
    val context = LocalContext.current.applicationContext
    val configuration = LocalConfiguration.current
    Log.v("MainActitivity", "${configuration.screenWidthDp}")
    Scaffold(modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = { HomeTopBar(context, navController) },
        bottomBar = { Bg() }) { innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerpadding)
                .padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeSectionHeader(homeViewMode)
            HomeSectionSectors(navController,homeViewMode)
            HomeSectionDesigns(navController,homeViewMode)
            HomeSectionCampaigns(navController,homeViewMode)
            HomeSectionInStockSales(navController,homeViewMode)
            HomeSectionSectorNews(navController,homeViewMode)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(context: Context, navController: NavController) =
    TopAppBar(title = { Text("") }, navigationIcon = {
        Icon(
            painter = painterResource(id = R.drawable.home_logo),
            contentDescription = null,
            tint = AppColors.grey_138
        )
    }, actions = {
        IconButton(onClick = { navController.navigate("home/messages") }) {
            Icon(
                painter = painterResource(id = R.drawable.ico_messages),
                contentDescription = null
            )
        }
        IconButton(onClick = { navController.navigate("home/jobs") }) {
            Icon(painter = painterResource(id = R.drawable.ico_task), contentDescription = null)
        }
        IconButton(onClick = { navController.navigate("home/notifications") }) {
            Icon(
                painter = painterResource(id = R.drawable.ico_notifications),
                contentDescription = null
            )
        }
    }, colors = TopAppBarDefaults.topAppBarColors(
        containerColor = Color.White,
        navigationIconContentColor = AppColors.grey_138,
        actionIconContentColor = AppColors.grey_138
    )
    )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBottomBar(context: Context) = BottomAppBar(containerColor = Color.White,
    modifier = Modifier
        .shadow(25.dp, RectangleShape, true)
        .padding(0.dp),
    actions = {
        IconButton(onClick = { }) {
            Image(painter = painterResource(id = R.drawable.menu_a_home), contentDescription = null)
        }
        IconButton(onClick = { }) {
            Image(
                painter = painterResource(id = R.drawable.menu_i_bookmark),
                contentDescription = null
            )
        }
        IconButton(onClick = { }) {
            Image(
                painter = painterResource(id = R.drawable.menu_i_notification),
                contentDescription = null
            )
        }
        IconButton(onClick = { }) {
            Image(
                painter = painterResource(id = R.drawable.menu_i_profile), contentDescription = null
            )
        }
    },
    floatingActionButton = {
        FloatingActionButton(
            onClick = { /*TODO*/ }, shape = CircleShape
        ) {
            Image(
                painter = painterResource(id = R.drawable.menu_red_add), contentDescription = null
            )
        }
    })

@Composable
fun Bg() = Column(
    modifier = Modifier
        .height(150.dp)
        .paint(
            painterResource(id = R.mipmap.beyazbg),
            contentScale = ContentScale.Crop
        )
) {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Notifications(navController: NavController) {
    val context = LocalContext.current.applicationContext
    Scaffold(containerColor = Color.White, topBar = {
        MediumTopAppBar(
            colors = TopAppBarColors(
                containerColor = Color.White, scrolledContainerColor = Color.White,
                navigationIconContentColor = AppColors.primaryGrey,
                titleContentColor = AppColors.primaryGrey,
                actionIconContentColor = AppColors.primaryGrey,
            ),
            title = { Text("Bildirimler", style = homeSubSectionTitle) },
            actions = {
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_home_24),
                        contentDescription = null
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = null
                    )
                }
            }
        )
    }) { innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .padding(horizontal = 10.dp)
        ) {
            Text("Yeni", style = homeSubSectionSubTitle)
            listofNotifications.take(4).forEach { b ->
                ListItem(
                    colors = ListItemColors(
                        containerColor = Color.White,
                        headlineColor = AppColors.primaryGrey,
                        supportingTextColor = AppColors.grey_130,
                        leadingIconColor = AppColors.grey_130,
                        trailingIconColor = AppColors.grey_130,
                        overlineColor = AppColors.grey_130,
                        disabledHeadlineColor = AppColors.primaryGrey,
                        disabledLeadingIconColor = AppColors.grey_130,
                        disabledTrailingIconColor = AppColors.grey_130
                    ),
                    headlineContent = { Text(b.title, style = notficationListHeader) },
                    supportingContent = {
                        Text(
                            text = b.subTitle ?: "", style = notficationListSupportingtext
                        )
                    },
                    leadingContent = {
                        Text(
                            b.date, style = notficationListDate, modifier = Modifier.width(60.dp)
                        )
                    },
                )
                HorizontalDivider(thickness = 1.dp, color = AppColors.primaryGreyDisabled)
            }
            Spacer(modifier = Modifier.height(26.dp))
            Text("Daha Öncekiler", style = homeSubSectionSubTitle)
            var lastone = listofNotifications.last()
            ListItem(
                headlineContent = { Text(lastone.title, style = notficationListHeader) },
                supportingContent = {
                    Text(
                        text = lastone.subTitle ?: "", style = notficationListSupportingtext
                    )
                },
                leadingContent = { Text(lastone.date, style = notficationListDate) },
            )
            HorizontalDivider(thickness = 1.dp, color = AppColors.primaryGreyDisabled)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Jobs(navController: NavController) {
    val context = LocalContext.current.applicationContext
    Scaffold(containerColor = Color.White, topBar = {
        MediumTopAppBar(
            colors = TopAppBarColors(
                containerColor = Color.White, scrolledContainerColor = Color.White,
                navigationIconContentColor = AppColors.primaryGrey,
                titleContentColor = AppColors.primaryGrey,
                actionIconContentColor = AppColors.primaryGrey,
            ),
            title = { Text("Görevler", style = homeSubSectionTitle) },
            actions = {
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_home_24),
                        contentDescription = null
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = null
                    )
                }
            }
        )
    }) { innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .padding(horizontal = 10.dp)
        ) {
            Text("Aşağıdaki Görevleri Tamamlayınız!", style = homeSubSectionSubTitle)
            listofTasks.forEach { b ->
                ListItem(
                    colors = ListItemColors(
                        containerColor = Color.White,
                        headlineColor = AppColors.primaryGrey,
                        supportingTextColor = AppColors.grey_130,
                        leadingIconColor = AppColors.grey_130,
                        trailingIconColor = AppColors.grey_130,
                        overlineColor = AppColors.grey_130,
                        disabledHeadlineColor = AppColors.primaryGrey,
                        disabledLeadingIconColor = AppColors.grey_130,
                        disabledTrailingIconColor = AppColors.grey_130
                    ),
                    headlineContent = { Text(b.title, style = notficationListHeader) },
                    supportingContent = {
                        Text(
                            text = b.subTitle ?: "", style = notficationListSupportingtext
                        )
                    },
                    leadingContent = {
                        Text(
                            b.date, style = notficationListDate, modifier = Modifier.width(60.dp)
                        )
                    },
                )
                HorizontalDivider(thickness = 1.dp, color = AppColors.primaryGreyDisabled)
            }
            Spacer(modifier = Modifier.height(26.dp))
            Text("Daha Öncekiler", style = homeSubSectionSubTitle)
            var lastone = listofNotifications.last()
            ListItem(
                headlineContent = { Text(lastone.title, style = notficationListHeader) },
                supportingContent = {
                    Text(
                        text = lastone.subTitle ?: "", style = notficationListSupportingtext
                    )
                },
                leadingContent = { Text(lastone.date, style = notficationListDate) },
            )
            HorizontalDivider(thickness = 1.dp, color = AppColors.primaryGreyDisabled)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Messages(navController: NavController) {
    val context = LocalContext.current.applicationContext
    Scaffold(containerColor = Color.White, topBar = {
        MediumTopAppBar(
            colors = TopAppBarColors(
                containerColor = Color.White, scrolledContainerColor = Color.White,
                navigationIconContentColor = AppColors.primaryGrey,
                titleContentColor = AppColors.primaryGrey,
                actionIconContentColor = AppColors.primaryGrey,
            ),
            title = { Text("Mesajlar", style = homeSubSectionTitle) },
            actions = {
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_home_24),
                        contentDescription = null
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = null
                    )
                }
            }
        )
    }) { innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .padding(horizontal = 10.dp)
        ) {
            Text("Yeni", style = homeSubSectionSubTitle)
            listofMessages.take(1).forEach { b ->
                ListItem(
                    colors = ListItemColors(
                        containerColor = Color.White,
                        headlineColor = AppColors.primaryGrey,
                        supportingTextColor = AppColors.grey_130,
                        leadingIconColor = AppColors.grey_130,
                        trailingIconColor = AppColors.grey_130,
                        overlineColor = AppColors.grey_130,
                        disabledHeadlineColor = AppColors.primaryGrey,
                        disabledLeadingIconColor = AppColors.grey_130,
                        disabledTrailingIconColor = AppColors.grey_130
                    ),
                    headlineContent = { Text(b.title, style = notficationListHeader) },
                    supportingContent = {
                        Text(
                            text = b.subTitle ?: "", style = notficationListSupportingtext
                        )
                    },
                    leadingContent = {
                        Text(
                            b.date, style = notficationListDate, modifier = Modifier.width(60.dp)
                        )
                    },
                )
                HorizontalDivider(thickness = 1.dp, color = AppColors.primaryGreyDisabled)
            }
            Spacer(modifier = Modifier.height(26.dp))
            Text("Daha Öncekiler", style = homeSubSectionSubTitle)
            var lastone = listofMessages.last()
            ListItem(
                headlineContent = { Text(lastone.title, style = notficationListHeader) },
                supportingContent = {
                    Text(
                        text = lastone.subTitle ?: "", style = notficationListSupportingtext
                    )
                },
                leadingContent = { Text(lastone.date, style = notficationListDate) },
            )
            HorizontalDivider(thickness = 1.dp, color = AppColors.primaryGreyDisabled)
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ITPTheme {
        Greeting("Android")
    }
}*/
