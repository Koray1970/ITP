package com.isticpla.itp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.isticpla.itp.dummydata.BusinessTypeItem
import com.isticpla.itp.dummydata.listofBusiness
import com.isticpla.itp.dummydata.listofHomeSectorNews
import com.isticpla.itp.dummydata.listofMessages
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.home.homeSubSectionSubTitle
import com.isticpla.itp.home.homeSubSectionTitle
import com.isticpla.itp.home.notficationListDate
import com.isticpla.itp.home.notficationListHeader
import com.isticpla.itp.home.notficationListSupportingtext
import com.isticpla.itp.ui.theme.ITPTheme
import com.isticpla.itp.uimodules.AppColors

class FeedActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ITPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigate()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedDashboard(
    navController: NavController,
    homeViewMode: HomeViewMode = hiltViewModel(),
) {
    val context = LocalContext.current.applicationContext
    var sectorList = homeViewMode.sectorList.collectAsState(initial = emptyList<BusinessTypeItem>())
    Scaffold(containerColor = Color.White, topBar = {
        MediumTopAppBar(
            colors = TopAppBarColors(
                containerColor = Color.White, scrolledContainerColor = Color.White,
                navigationIconContentColor = AppColors.primaryGrey,
                titleContentColor = AppColors.primaryGrey,
                actionIconContentColor = AppColors.primaryGrey,
            ),
            title = { Text("Sektörler", style = homeSubSectionTitle) },
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                sectorList.value.forEach { b ->
                    Button(
                        onClick = { /*TODO*/ },
                        shape= RoundedCornerShape(5.dp),
                        modifier=Modifier
                    ) {
                        Icon(painter = painterResource(id = b.icon), contentDescription = null)
                        Text(text = b.label)
                    }
                }

            }
        }
    }
}

@Composable
fun FeedProductDetail(navController: NavController) {

}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ITPTheme {
        Greeting("Android")
    }
}*/
