package com.isticpla.itp.offers

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.isticpla.itp.AppNavigate
import com.isticpla.itp.R
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.home.homeSubSectionTitle
import com.isticpla.itp.offers.ui.*
import com.isticpla.itp.offers.ui.theme.ITPTheme
import com.isticpla.itp.uimodules.AppColors

class OfferActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
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
fun CreateOfferDashboard(
    navController: NavController,
    homeViewMode: HomeViewMode = hiltViewModel(),
) {
    Scaffold(
        containerColor = AppColors.grey_133,
        topBar = {
            MediumTopAppBar(
                colors = TopAppBarColors(
                    containerColor = Color.Transparent, scrolledContainerColor = Color.Transparent,
                    navigationIconContentColor = AppColors.primaryGrey,
                    titleContentColor = AppColors.primaryGrey,
                    actionIconContentColor = AppColors.primaryGrey,
                ),
                title = { Text("Teklif Talebi Oluştur", style = homeSubSectionTitle) },
                actions = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_home_24),
                            contentDescription = null
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("feed/productdetail") }) {
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
                .fillMaxSize()
                .padding(innerpadding)
                .padding(horizontal = 10.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColors.green_100,
                        contentColor = Color.White,
                        disabledContainerColor = AppColors.green_100,
                        disabledContentColor = Color.White,
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(.5f)
                        .requiredHeight(92.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(.3f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.chip),
                            contentDescription = null
                        )
                    }
                    Column() {
                        Text(text =
                        buildAnnotatedString {
                            withStyle(style = offerGreenButtonLabelA) { append("Ürün\n") }
                            withStyle(style = offerGreenButtonLabelB) { append("Teklif Talebi\nOluştur") }
                        })
                    }
                }
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColors.green_100,
                        contentColor = Color.White,
                        disabledContainerColor = AppColors.green_100,
                        disabledContentColor = Color.White,
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(.5f)
                        .requiredHeight(92.dp)
                ) {
                    Column() {
                        Text(
                            text =
                            buildAnnotatedString {
                                withStyle(style = offerGreenButtonLabelA) { append("Hizmet\n") }
                                withStyle(style = offerGreenButtonLabelB) { append("Teklif Talebi\nOluştur") }
                            },
                            style = TextStyle(textAlign = TextAlign.Right)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_shopping_bag_24),
                            contentDescription = null
                        )
                    }
                }
            }
            //Taslak listeleri
            Spacer(modifier = Modifier.height(40.dp))
            Card(
                modifier = Modifier
                    .fillMaxSize(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = AppColors.primaryGrey,
                    disabledContainerColor = Color.White,
                    disabledContentColor = AppColors.primaryGrey
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        BadgedBox(
                            badge = {
                                Badge {
                                    val badgeNumber = "8"
                                    Text(
                                        badgeNumber,
                                        modifier = Modifier.semantics {
                                            contentDescription = "$badgeNumber new notifications"
                                        }
                                    )
                                }
                            }) {
                            Text(text = "Taslaklarım", style = draftsListTitle)
                        }
                        Spacer(modifier=Modifier.height(20.dp))

                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateOfferDetail(
    navController: NavController,
    homeViewMode: HomeViewMode = hiltViewModel(),
) {
    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = Color.White, scrolledContainerColor = Color.White,
                    navigationIconContentColor = AppColors.primaryGrey,
                    titleContentColor = AppColors.primaryGrey,
                    actionIconContentColor = AppColors.primaryGrey,
                ),
                title = { Text("Teklif Talebi Oluştur", style = homeSubSectionTitle) },
                actions = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_home_24),
                            contentDescription = null
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("feed/productdetail") }) {
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
                .verticalScroll(rememberScrollState()),
        ) {


        }
    }
}

