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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.isticpla.itp.home.*
import com.isticpla.itp.ui.theme.ITPTheme
import com.isticpla.itp.uimodules.AppColors

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

@Composable
fun Home(navController: NavController) {
    val context = LocalContext.current.applicationContext
    val configuration = LocalConfiguration.current
    Log.v("MainActitivity", "${configuration.screenWidthDp}")
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.White,
        topBar = { HomeTopBar(context) },
        bottomBar = { HomeBottomBar(context) }
    )
    { innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerpadding).padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeSectionHeader()
            HomeSectionSectors()
            HomeSectionDesigns()
            HomeSectionCampains()
            HomeSectionInStockSales()
            HomeSectionSectorNews()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(context: Context) = TopAppBar(
    title = { Text("") },
    navigationIcon = {
        Icon(
            painter = painterResource(id = R.drawable.home_logo),
            contentDescription = null,
            tint = AppColors.grey_138
        )
    },
    actions = {
        IconButton(onClick = { }) {
            Icon(painter = painterResource(id = R.drawable.ico_messages), contentDescription = null)
        }
        IconButton(onClick = { }) {
            Icon(painter = painterResource(id = R.drawable.ico_task), contentDescription = null)
        }
        IconButton(onClick = { }) {
            Icon(
                painter = painterResource(id = R.drawable.ico_notifications),
                contentDescription = null
            )
        }
    },
    colors = TopAppBarDefaults.topAppBarColors(
        containerColor = Color.White,
        navigationIconContentColor = AppColors.grey_138,
        actionIconContentColor = AppColors.grey_138
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBottomBar(context: Context) = BottomAppBar(
    containerColor = Color.White,
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
                painter = painterResource(id = R.drawable.menu_i_profile),
                contentDescription = null
            )
        }
    },
    floatingActionButton = {
        FloatingActionButton(
            onClick = { /*TODO*/ },
            shape = CircleShape
        ) {
            Image(
                painter = painterResource(id = R.drawable.menu_red_add),
                contentDescription = null
            )
        }
    }
)

@Composable
fun Notifications(navController: NavController) {
    val context = LocalContext.current.applicationContext
}

@Composable
fun Jobs(navController: NavController) {
    val context = LocalContext.current.applicationContext
}

@Composable
fun Messages(navController: NavController) {
    val context = LocalContext.current.applicationContext
}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ITPTheme {
        Greeting("Android")
    }
}*/
