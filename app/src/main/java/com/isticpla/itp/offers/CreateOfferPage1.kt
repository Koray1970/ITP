package com.isticpla.itp.offers

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.OrderStages
import com.isticpla.itp.dummydata.OrderStagesStatus
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.uimodules.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateOfferPage1(
    navController: NavController,
) {
    val homeViewMode = hiltViewModel<HomeViewMode>()
    val draftName = rememberSaveable { mutableStateOf("") }
    val listOfOrderStage =
        homeViewMode.orderStages.collectAsState(initial = emptyList<OrderStages>())
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
                title = { Text("Teklif Talebi Oluştur", style = offerTopBarTitle) },
                actions = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_home_24),
                            contentDescription = null
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("offer/dashboard") }) {
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
                .padding(top = 30.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .border(1.dp, AppColors.grey_133, RoundedCornerShape(8.dp))
            ) {
                TextField(
                    value = draftName.value,
                    onValueChange = { draftName.value = it },
                    label = {
                        Text(text = "Taslak adı")
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        capitalization = KeyboardCapitalization.Words
                    ),

                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
            ) {
                listOfOrderStage.value.forEach { o ->
                    val statusColor = remember { mutableStateOf<Color>(AppColors.grey_159) }
                    if (o.status == OrderStagesStatus.TAMAMLANDI) {
                        statusColor.value = AppColors.green_103
                    }
                    ListItem(
                        headlineContent = {
                            Text(text = buildAnnotatedString {
                                withStyle(style = offerStageLabel) {
                                    append("${o.label}\n")
                                }
                                withStyle(style = offerStageStatus) {
                                    append(o.status.result)
                                }
                            }, style = offerStageLabelText.merge(color = statusColor.value))
                        },
                        leadingContent = {
                            if (o.status == OrderStagesStatus.TAMAMLANDI) {
                                BadgedBox(
                                    badge = {
                                        Badge(
                                            containerColor = AppColors.green_103
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.round_check_24),
                                                contentDescription = null,
                                                modifier = Modifier.size(10.dp),
                                                tint = Color.White
                                            )
                                        }
                                    }) {
                                    Card(
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier
                                            .size(50.dp)
                                            .border(
                                                1.dp,
                                                statusColor.value,
                                                RoundedCornerShape(8.dp)
                                            ),
                                        colors = CardDefaults.cardColors(
                                            containerColor = Color.White
                                        ),
                                    ) {
                                        Column(
                                            modifier = Modifier.fillMaxSize(),
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Image(
                                                painter = painterResource(id = o.icon),
                                                contentDescription = null
                                            )
                                        }
                                    }
                                }

                            } else {
                                Card(
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier
                                        .size(50.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color.White
                                    ),
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Image(
                                            painter = painterResource(id = o.icon),
                                            contentDescription = null
                                        )
                                    }
                                }
                            }
                        },
                        trailingContent = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                                contentDescription = null,
                                tint = statusColor.value
                            )
                        },
                        colors = ListItemDefaults.colors(
                            containerColor = statusColor.value.copy(.1f),
                            headlineColor = statusColor.value,
                        ),
                        modifier = Modifier
                            .clickable { navController.navigate(o.uri) }
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    onClick = { navController.navigate("offer/create/publish") },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColors.grey_130,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(48.dp)
                ) {
                    Text(text = "Yayınla", style = offerStagePublishButton)
                    Icon(
                        painter = painterResource(id = R.drawable.round_arrow_right_alt_24),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(.98f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { navController.navigate("offer/create/publish") },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppColors.grey_133,
                            contentColor = AppColors.primaryGrey
                        ),
                        modifier = Modifier
                            .requiredHeight(48.dp)
                    ) {
                        Text(text = "Taslak Olarak Kaydet", style = offerStageDefaultButton)
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Button(
                        onClick = { },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppColors.grey_133,
                            contentColor = AppColors.primaryGrey
                        ),
                        modifier = Modifier
                            .requiredHeight(48.dp)
                    ) {
                        Text(text = "Kopyala", style = offerStageDefaultButton)
                    }
                }
            }

        }
    }
}