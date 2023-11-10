package com.isticpla.itp.offerdetails

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.isticpla.itp.AppNavigate
import com.isticpla.itp.R
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.offerdetails.ui.theme.ITPTheme
import com.isticpla.itp.offers.ProposalWizardStage
import com.isticpla.itp.offers.offerTopBarTitle
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.Carousel
import com.isticpla.itp.uimodules.CarouselPagerRequest
import com.isticpla.itp.uimodules.CarouselRequest

class OfferDetailActivity : ComponentActivity() {
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
fun OfferDetailDashboard(
    navController: NavController
) {
    val homeviewModel = hiltViewModel<HomeViewMode>()
    val carouselListState = homeviewModel.carouselList.collectAsState(initial = emptyList<Int>())
    val carouselRequest = CarouselRequest(
        visuals = carouselListState.value,
        carouselmodifier = Modifier
            .background(Color.Red)
            .padding(horizontal = 0.dp),
        pagespacing = 0.dp,
        visualitemmodifier = Modifier
            .fillMaxWidth()
            .requiredHeight(220.dp),
        pager = CarouselPagerRequest(true, 0.dp, AppColors.blue_104, AppColors.blue_105)
    )
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
                title = { Text("", style = offerTopBarTitle) },
                actions = {
                    TextButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.round_mode_edit_24),
                            contentDescription = null
                        )
                        Text("Düzenle", style = TextStyle())
                    }
                    TextButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.round_content_copy_24),
                            contentDescription = null
                        )
                        Text("Kopyala", style = TextStyle())
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("offer/create") }) {
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
                .padding(top =8.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Carousel(requests = carouselRequest)
            Text("Tillman’s Hamburger vom\nRind, 4 Stück, 250g", style = offerdetailHeader)
            Text(text = buildAnnotatedString {
                withStyle(style = offerdetailTrackingCodeA) { append("TAKİP KODU:") }
                withStyle(style = offerdetailTrackingCodeB) { append("XR47HYGFV") }
            }, style = offerdetailTrackingCode)
            InputChip(
                selected = false,
                onClick = {},
                border=InputChipDefaults.inputChipBorder(borderWidth = 0.dp, borderColor = Color.Transparent),
                colors = InputChipDefaults.inputChipColors(
                    containerColor = AppColors.greyLight,
                    labelColor = AppColors.primaryGrey,
                    leadingIconColor = AppColors.primaryGrey,

                ),
                label = { Text("Teklif Aşamasında") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.round_gavel_24),
                        contentDescription = null,
                        tint = AppColors.primaryGrey
                    )
                }
            )


        }
    }
}
/*
@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    ITPTheme {
        Dashboard(rememberNavController())
    }
}*/
