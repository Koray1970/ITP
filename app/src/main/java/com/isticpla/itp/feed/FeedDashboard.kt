package com.isticpla.itp.feed

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
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
import com.google.gson.Gson
import com.isticpla.itp.R
import com.isticpla.itp.database.AccountViewModel
import com.isticpla.itp.dummydata.BusinessTypeItem
import com.isticpla.itp.dummydata.FeedDashboardItems
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.home.homeSubSectionTitle
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.AppTextFilterComponent
import com.isticpla.itp.uimodules.Bg
import com.isticpla.itp.uimodules.SearchbarWithChips
import com.isticpla.itp.uimodules.defaultmenuItemState
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedDashboard(
    navController: NavController,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
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

    val sectorList = accountViewModel.getSectorList2()
        .collectAsStateWithLifecycle(initialValue = listOf<BusinessTypeItem>())

    var listOfChip = remember { mutableStateListOf<String>() }


    LaunchedEffect(lifecycleOwner.lifecycle) {
        if (sectorList.value.isNotEmpty()) {
            Log.v("MainActivity2", "${accountViewModel.state.isFinished}")
            Log.v("MainActivity2", "${sectorList.value.size}")

            selectedOption = sectorList.value.first()
            onOptionSelected(sectorList.value.first())
        }
    }

    val listofdashboarditem =
        homeViewMode.feedDashboardItems.collectAsState(initial = emptyList<FeedDashboardItems>())


    Scaffold(
        containerColor = Color.White,
        bottomBar = { Bg(navController, defaultmenuItemState) },
        topBar = {
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
                .padding(horizontal = 10.dp)
                .verticalScroll(rememberScrollState()),
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
            Spacer(modifier = Modifier.height(20.dp))
            //region Search bar
            Row(
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
            }
            //endregion
            Spacer(modifier = Modifier.height(20.dp))
            val expendedList = listofdashboarditem.value.filter { f ->
                if (listOfChip.isNotEmpty()) {
                    val str =
                        f.title + " " + if (f.spottext != null) f.spottext + " " else "" + if (f.content != null) f.content + " " else ""
                    listOfChip.any { a -> str.contains(a, true) }

                } else
                    true
            }
            expendedList.forEach { b ->
                when (b.type) {
                    1 -> {
                        FeedDashboardItemLarge(fdi = b, navController)
                    }

                    2 -> {
                        FeedDashboardItemMedium(fdi = b, navController)
                    }

                    else -> {
                        FeedDashboardItemNoImage(fdi = b, navController)
                    }
                }
                Spacer(modifier = Modifier.height(42.dp))
            }
        }
    }
}