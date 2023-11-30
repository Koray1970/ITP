package com.isticpla.itp.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
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
import com.isticpla.itp.dummydata.HomeDesignItem
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.AppTextFilterComponent
import com.isticpla.itp.uimodules.SearchbarWithChips
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun StockSalesDashboard(
    navController: NavController,

    ) {
    val context = LocalContext.current.applicationContext
    val homeViewMode = hiltViewModel<HomeViewMode>()
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
    var sectorListState = accountViewModel.getSectorList2()
        .collectAsStateWithLifecycle(initialValue = mutableStateListOf<BusinessTypeItem>())

    var listOfChip = remember { mutableStateListOf<String>() }


    LaunchedEffect(Unit) {
        //sectorlist = sectorListState.value
        if (sectorListState.value.isNotEmpty()) {
            selectedOption = sectorListState.value.first()
            onOptionSelected(sectorListState.value.first())
        }
    }
    val listofStockSales =
        homeViewMode.stokSaleList.collectAsState(initial = emptyList<HomeDesignItem>())
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
                title = { Text("Stoklu Satışlar", style = homeSubSectionTitle) },
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
                .padding(horizontal = 10.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .selectableGroup()
                    .horizontalScroll(rememberScrollState())
            ) {
                when (accountViewModel.state.isFinished) {
                    null -> {}
                    else -> {
                        sectorListState.value.forEach { b ->
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
                                            homeViewMode.UpdateSectorListSelection(b.id)
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
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            //SearchbarWithChips()
            //region Search bar
            Row(
                verticalAlignment = Alignment.CenterVertically,
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
                        .fillMaxSize(.15f)
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
            }
            //endregion
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FlowRow(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    maxItemsInEachRow = 2
                ) {

                    val expendedList = listofStockSales.value.filter { f ->
                        if (listOfChip.isNotEmpty()) {
                            listOfChip.any{a-> f.title.contains(a,true)}

                        }
                        else
                            true
                    }
                    expendedList.forEach { b ->
                        Column(
                            modifier = Modifier
                                .clickable { }
                                .requiredWidth(140.dp)
                                .requiredHeight(290.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .size(140.dp, 210.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .paint(
                                        painter = painterResource(id = b.image),
                                        contentScale = ContentScale.Crop
                                    ),
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.End
                            ) {
                                Text(
                                    text = b.price,
                                    modifier = Modifier
                                        .background(Color.White.copy(.6f))
                                        .fillMaxWidth()
                                        .padding(start = 8.dp, end = 8.dp),
                                    style = homeSectorDesignCardPriceLabel
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = b.title,
                                modifier = Modifier
                                    .requiredWidth(140.dp)
                                    .requiredHeight(90.dp),
                                maxLines = 3,
                                style = homeSectorDesignCardLabel
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}