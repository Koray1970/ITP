package com.isticpla.itp.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.Screen
import com.isticpla.itp.database.AccountViewModel
import com.isticpla.itp.dummydata.ShopItem
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.AppProgress
import com.isticpla.itp.uimodules.Bg
import com.isticpla.itp.uimodules.BottomBarMenuItem
import com.isticpla.itp.uimodules.BottomBarMenuItemType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun Home(
    navController: NavController,
) {
    val scope = rememberCoroutineScope()
    val homeViewModel = hiltViewModel<HomeViewMode>()
    val progressState = remember { mutableStateOf(true) }
    val menuItemState = mutableListOf(
        BottomBarMenuItem(BottomBarMenuItemType.HOME, true),
        BottomBarMenuItem(BottomBarMenuItemType.BOOKMARK),
        BottomBarMenuItem(BottomBarMenuItemType.NOTIFICATION, hasbadge = true),
        BottomBarMenuItem(BottomBarMenuItemType.PROFILE),
    )

    val accountViewModel = hiltViewModel<AccountViewModel>()
    val numberofAccount =
        accountViewModel.getNumberofAccount.collectAsStateWithLifecycle(initialValue = Int)

    scope.launch {
        when (numberofAccount.value) {
            0 -> {
                navController.navigate(Screen.StartSelectCulture.route)
            }

            else -> {
                accountViewModel.isAccountCreated().collect { g ->
                    if (g.second != null)
                        println("DD: ${g.second!!}")
                    when (g.first) {
                        true -> {
                            delay(5000L)
                            progressState.value = false
                        }

                        else -> {
                            if (g.second != null)
                                navController.navigate(g.second!!)
                        }
                    }
                }
            }
        }
    }
    val dummyshop = mutableStateOf(ShopItem(0, "", ""))


    val showShopListBottomSheet = remember { mutableStateOf(false) }
    var shopSheetState = rememberModalBottomSheetState()


    val listofShops = homeViewModel.shopList.collectAsState(initial = emptyList())
    val getselectedShop =
        homeViewModel.selectedShop.collectAsStateWithLifecycle(
            initialValue = mutableStateOf(
                dummyshop
            )
        )
    var (selectedShopOption, onOptionSelectedShop) = remember { dummyshop }


    BottomSheetScaffold(
        sheetPeekHeight = 0.dp,

        sheetContent = {
            if (showShopListBottomSheet.value) {
                ModalBottomSheet(
                    scrimColor = Color.Black.copy(.8f),
                    onDismissRequest = {
                        showShopListBottomSheet.value = false
                    },
                    sheetState = shopSheetState
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.5f)
                            .selectableGroup()
                    ) {
                        if (listofShops.value.isNotEmpty()) {

                            items(listofShops.value) { itm ->
                                ListItem(
                                    colors = ListItemDefaults.colors(
                                        containerColor = Color.White
                                    ),
                                    modifier = Modifier
                                        .selectable(
                                            selected = (itm.id == selectedShopOption.id),
                                            onClick = {
                                                scope.launch {
                                                    selectedShopOption = itm
                                                    onOptionSelectedShop(itm)
                                                    homeViewModel.updateSelectedShop(itm)
                                                    delay(600)
                                                    progressState.value = true

                                                    shopSheetState.partialExpand()
                                                    showShopListBottomSheet.value=false
                                                }
                                            },
                                            role = Role.RadioButton
                                        ),
                                    leadingContent = {
                                        RadioButton(
                                            colors = RadioButtonDefaults.colors(
                                                selectedColor = AppColors.blue_0xFF0495f1,
                                                unselectedColor = AppColors.secondaryGrey
                                            ),
                                            selected = (itm.id == selectedShopOption.id),
                                            onClick = null // null recommended for accessibility with screenreaders
                                        )
                                    },
                                    headlineContent = {
                                        Text(
                                            text = buildAnnotatedString {
                                                withStyle(
                                                    style = SpanStyle(
                                                        fontSize = 16.sp,
                                                        fontWeight = FontWeight.SemiBold,
                                                        color = AppColors.blue_0xFF0495f1
                                                    )
                                                ) { append("${itm.name}\n") }
                                                append(itm.address)
                                            },
                                            style = TextStyle(
                                                fontFamily = poppinFamily,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Normal
                                            ),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        )

                                    })
                            }
                        }
                        item {
                            TextButton(
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.textButtonColors(
                                    containerColor = Color.Transparent,
                                    contentColor = AppColors.blue_0xFF0495f1
                                )
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.round_add_24),
                                    contentDescription = null
                                )
                                Text(
                                    text = "Yeni Mağaza Ekle",
                                    style = TextStyle(
                                        fontFamily = poppinFamily,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                            }
                        }
                    }
                }
            }

        }) { padd ->
        Scaffold(
            modifier = Modifier
                .padding(padd)
                .fillMaxSize(),
            containerColor = Color.White,
            topBar = { HomeTopBar(navController) },
            bottomBar = { Bg(navController, menuItemState) })
        { innerpadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(innerpadding)
                    .padding(horizontal = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (listofShops.value.isNotEmpty())
                    HomeSectionHeader(shopSheetState,showShopListBottomSheet)
                HomeSectionSectors(navController)
                HomeSectionDesigns(navController)
                HomeSectionCampaigns(navController)
                HomeSectionInStockSales(navController)
                HomeSectionSectorNews(navController)
            }
        }
    }

    AppProgress(progressState)
}