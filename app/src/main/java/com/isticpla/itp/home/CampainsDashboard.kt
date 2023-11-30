package com.isticpla.itp.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.database.AccountViewModel
import com.isticpla.itp.dummydata.BusinessTypeItem
import com.isticpla.itp.dummydata.HomeCampaignItem
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.AppTextFilterComponent
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CampaignsDashboards(
    navController: NavController
) {
    val lifecycleOwner= LocalLifecycleOwner.current
    val homeViewModel = hiltViewModel<HomeViewMode>()
    val accountViewModel = hiltViewModel<AccountViewModel>()

    var sectorlist = remember { mutableListOf<BusinessTypeItem>() }
    var (selectedOption, onOptionSelected) = remember {
        mutableStateOf(
            BusinessTypeItem(
                id = 0,
                icon = 0,
                label = ""
            )
        )
    }

    val sectorList = accountViewModel.getSectorList2()
        .collectAsStateWithLifecycle(initialValue =listOf<BusinessTypeItem>())

    var listOfChip = remember { mutableStateListOf<String>() }


    LaunchedEffect(lifecycleOwner.lifecycle) {
        if (sectorList.value.isNotEmpty()) {
            Log.v("MainActivity2", "${accountViewModel.state.isFinished}")
            Log.v("MainActivity2", "${sectorList.value.size}")

            selectedOption = sectorList.value.first()
            onOptionSelected(sectorList.value.first())
        }
    }

    val listofCampaigns =
        homeViewModel.campaignList.collectAsState(initial = emptyList<HomeCampaignItem>())
    Scaffold(
        containerColor = Color.White,
        topBar = {
            MediumTopAppBar(
                colors = TopAppBarColors(
                    containerColor = Color.White, scrolledContainerColor = Color.White,
                    navigationIconContentColor = AppColors.primaryGrey,
                    titleContentColor = AppColors.primaryGrey,
                    actionIconContentColor = AppColors.primaryGrey,
                ),
                title = { Text("Kampanyalar", style = homeSubSectionTitle) },
                actions = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_home_24),
                            contentDescription = null
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
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
                .padding(horizontal = 10.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .selectableGroup()
                    .horizontalScroll(rememberScrollState())
            ) {
                sectorList.value.forEach { b ->
                    var toggleButtonState by remember { mutableStateOf(b.isSelected) }
                    val cColor = if ((b != selectedOption))
                        CardColors(
                            containerColor = AppColors.grey_133,
                            contentColor = Color.Black,
                            disabledContainerColor = AppColors.primaryGrey,
                            disabledContentColor = Color.White
                        )
                    else
                        CardColors(
                            containerColor = AppColors.primaryGrey,
                            contentColor = Color.White,
                            disabledContainerColor = AppColors.primaryGrey,
                            disabledContentColor = Color.White
                        )
                    Card(
                        shape = RoundedCornerShape(5.dp),
                        colors = cColor,
                        //enabled = b.isSelected,
                        modifier = Modifier
                            .size(100.dp, 44.dp)
                            .padding(0.dp)
                            .selectable(
                                selected = (b == selectedOption),
                                onClick = {
                                    homeViewModel.UpdateSectorListSelection(b.id)
                                    onOptionSelected(b)
                                },
                                role = Role.Button
                            ),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = b.icon!!),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(28.dp)
                                    .padding(0.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = b.label!!.uppercase(Locale.ROOT),
                                style = TextStyle(
                                    fontFamily = poppinFamily,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            //region Search bar
            /*Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
            ) {
                AppTextFilterComponent(
                    cardmodifier = Modifier
                        .fillMaxWidth(.8f)
                        .heightIn(min = 80.dp, max = 140.dp)
                        .weight(1f),
                    listOfChip = listOfChip
                )
                Card(
                    onClick = {},
                    colors = CardColors(
                        containerColor = AppColors.grey_133,
                        contentColor = Color.Black,
                        disabledContainerColor = AppColors.grey_133,
                        disabledContentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .fillMaxWidth(.15f)
                        .height(80.dp)
                        .padding(0.dp)
                        .border(1.dp, AppColors.grey_130, RoundedCornerShape(5.dp))
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_favorite_border_24),
                            contentDescription = null,
                        )
                    }
                }
            }*/
            //endregion
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                listofCampaigns.value.take(2).forEach { b ->

                    Card(
                        modifier = Modifier.padding(bottom = 16.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardColors(
                            containerColor = AppColors.grey_144,
                            contentColor = AppColors.grey_147,
                            disabledContainerColor = AppColors.grey_144,
                            disabledContentColor = AppColors.grey_147
                        ),
                        onClick = {}
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .wrapContentWidth(Alignment.Start)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(16.dp)
                                ) {
                                    VerticalDivider(
                                        thickness = 1.dp,
                                        color = AppColors.grey_138
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(b.subTitle, style = homeSectorCampaignCardSpot)
                                }
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(b.title, style = homeSectorCampaignCardTitle)
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .wrapContentWidth(Alignment.End),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(vertical = 10.dp)
                                        .size(120.dp)
                                        .clip(CircleShape)
                                        .background(AppColors.grey_150)
                                ) {}
                                Box {
                                    Image(
                                        painter = painterResource(id = b.image),
                                        contentDescription = null,
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier.size(140.dp)
                                    )
                                }
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start)
                ) {
                    if (listofCampaigns.value.isNotEmpty()) {
                        //region left side
                        val lastOne = listofCampaigns.value[2]
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(.48f)
                                .requiredHeight(280.dp)
                                .padding(bottom = 16.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardColors(
                                containerColor = AppColors.grey_144,
                                contentColor = AppColors.grey_147,
                                disabledContainerColor = AppColors.grey_144,
                                disabledContentColor = AppColors.grey_147
                            ),
                            onClick = {}
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .paint(
                                        sizeToIntrinsics = false,
                                        painter = painterResource(id = lastOne.image),
                                        contentScale = ContentScale.Fit,
                                        alignment = Alignment.CenterStart
                                    ),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.End
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth(.7f)
                                        .padding(end = 30.dp)
                                        .wrapContentWidth(Alignment.End)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(16.dp)
                                    ) {

                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(lastOne.subTitle, style = homeSectorCampaignCardSpot)
                                    }
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Text(lastOne.title, style = homeSectorCampaignCardTitle)
                                }
                            }
                        }
                        //endregion

                        //region right side
                        val lastTwo = listofCampaigns.value[3]
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .requiredHeight(280.dp)
                                .padding(bottom = 16.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardColors(
                                containerColor = AppColors.grey_144,
                                contentColor = AppColors.grey_147,
                                disabledContainerColor = AppColors.grey_144,
                                disabledContentColor = AppColors.grey_147
                            ),
                            onClick = {}
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .paint(
                                        sizeToIntrinsics = false,
                                        painter = painterResource(id = lastTwo.image),
                                        contentScale = ContentScale.Fit,
                                        alignment = Alignment.CenterEnd
                                    ),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.End
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(start = 10.dp, end = 60.dp)
                                        .fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(16.dp)
                                    ) {

                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(lastTwo.subTitle, style = homeSectorCampaignCardSpot)
                                    }
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Text(lastTwo.title, style = homeSectorCampaignCardTitle)
                                }
                            }
                        }
                        //endregion
                    }
                }
            }
        }
    }
}