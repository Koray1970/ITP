package com.isticpla.itp.offers

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
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
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.OfferDraftListItem
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.home.homeSubSectionTitle
import com.isticpla.itp.uimodules.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateOfferDashboard(
    navController: NavController,
) {
    val homeViewMode = hiltViewModel<HomeViewMode>()
    val offerDraftListState =
        homeViewMode.offerDrafts.collectAsState(initial = emptyList<OfferDraftListItem>())

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
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
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
                    onClick = { navController.navigate("offer/create") },
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
                    onClick = {  navController.navigate("offer/create/asservice") },
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
                        .padding(vertical = 20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(start = 20.dp)
                            .fillMaxWidth()
                    ) {
                        BadgedBox(
                            badge = {
                                Badge {
                                    val badgeNumber = "${offerDraftListState.value.size}"
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
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    offerDraftListState.value.forEach { o ->
                        ListItem(
                            headlineContent = {
                                Column(
                                    modifier = Modifier
                                        .drawWithContent {
                                            drawContent()
                                            drawLine(
                                                color = AppColors.grey_153,
                                                start = Offset(size.width, 0f),
                                                end = Offset(size.width, size.height),
                                                strokeWidth = 2f
                                            )
                                        }
                                ) {
                                    Text(text = buildAnnotatedString {
                                        withStyle(style = draftsListItemDate) { append("${o.date}\n") }
                                        withStyle(style = draftsListItemTitle) { append(o.title) }
                                    })
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(
                                            4.dp,
                                            Alignment.Start
                                        )
                                    ) {
                                        repeat(4) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.round_check_circle_24),
                                                contentDescription = null,
                                                tint =
                                                if (o.status > it)
                                                    AppColors.green_100
                                                else
                                                    AppColors.green_100.copy(.2f)
                                            )
                                        }
                                    }

                                }
                            },
                            leadingContent = {
                                Image(
                                    painter = painterResource(id = o.image!!),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .requiredSize(80.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                )
                            },
                            trailingContent = {
                                Column(
                                    modifier = Modifier
                                        .clickable { }
                                        .requiredSize(58.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.round_content_copy_24),
                                        contentDescription = null,
                                        tint = Color.Black.copy(.5f)
                                    )
                                    Text(
                                        text = "Kopya\nOluştur",
                                        style = draftsListItemTrailingItemText
                                    )
                                }
                            },
                            colors = ListItemDefaults.colors(
                                containerColor = Color.Transparent,
                                headlineColor = Color.Green
                            ),
                            modifier = Modifier
                                .padding(0.dp)
                                .drawWithContent {
                                    drawContent()
                                    drawLine(
                                        color = AppColors.grey_153,
                                        start = Offset(0f, size.height),
                                        end = Offset(size.width, size.height),
                                        strokeWidth = 2f
                                    )
                                }
                        )
                    }
                }
            }
        }
    }
}