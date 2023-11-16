package com.isticpla.itp.views

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import com.isticpla.itp.dummydata.PanelActiveOfferItem
import com.isticpla.itp.dummydata.PanelCollectionItem
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.home.homeSubSectionTitle
import com.isticpla.itp.offers.appTextField
import com.isticpla.itp.offers.appTextFieldItems
import com.isticpla.itp.offers.txtFColors
import com.isticpla.itp.offers.txtFKeyboardOptionsCapWord
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.Bg
import com.isticpla.itp.uimodules.BottomBarMenuItem
import com.isticpla.itp.uimodules.BottomBarMenuItemType
import com.isticpla.itp.uistyles.listitemHeaderLineContent
import com.isticpla.itp.uistyles.listitemTitle
import com.isticpla.itp.views.helpers.AppMediumTopBar
import com.isticpla.itp.views.helpers.AppMediumTopBarItem
import com.isticpla.itp.views.helpers.TopBarActionItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPanelCollections(
    navController: NavController,
) {
    val scope = rememberCoroutineScope()
    val newCollectionSheetState = rememberModalBottomSheetState()
    var showNewCollectionSheet = remember { mutableStateOf(false) }

    val frmTitleValue = rememberSaveable { mutableStateOf("") }
    val frmCommentValue = rememberSaveable { mutableStateOf("") }

    val menuItemState = mutableListOf<BottomBarMenuItem>(
        BottomBarMenuItem(BottomBarMenuItemType.HOME, true),
        BottomBarMenuItem(BottomBarMenuItemType.BOOKMARK),
        BottomBarMenuItem(BottomBarMenuItemType.NOTIFICATION, hasbadge = true),
        BottomBarMenuItem(BottomBarMenuItemType.PROFILE),
    )
    val roundcornerRectangel = RoundedCornerShape(10.dp)
    val homeViewModel = hiltViewModel<HomeViewMode>()

    val collections =
        homeViewModel.panelCollections.collectAsStateWithLifecycle(initialValue = emptyList<PanelCollectionItem>())
    Scaffold(
        containerColor = Color.White,
        topBar = AppMediumTopBar(
            AppMediumTopBarItem(
                containerColor = Color.White.copy(.8f),
                title = {
                    Column(
                        modifier = Modifier
                            .padding(end = 10.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                "Kolleksiyonlarım",
                                style = homeSubSectionTitle,
                                modifier = Modifier.weight(1f)
                            )
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth(.26f)
                                    .requiredHeight(51.dp)
                                    .padding(10.dp)
                                    .clickable {
                                        scope.launch {
                                            showNewCollectionSheet.value = true
                                        }
                                    },
                                shape = RoundedCornerShape(6.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = AppColors.red_100,
                                    contentColor = Color.White
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(
                                        6.dp,
                                        Alignment.CenterHorizontally
                                    )
                                ) {
                                    Text(
                                        text = "Yeni",
                                        style = TextStyle(
                                            fontFamily = poppinFamily,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    )
                                    Icon(
                                        painter = painterResource(id = R.drawable.round_add_24),
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    }
                },
                actions = listOf<TopBarActionItem>(
                    TopBarActionItem(
                        url = { navController.navigate("home") },
                        icon = R.drawable.outline_home_24
                    )
                ).toMutableList(),
                navigationIcon = TopBarActionItem(
                    url = { navController.popBackStack() },
                    icon = R.drawable.arrow_left
                )
            )
        ),
        bottomBar = { Bg(navController, menuItemState) }
    ) { innerpadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerpadding)
                .padding(horizontal = 10.dp)
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            PanelSectionTitle(
                title = "Koleksiyonlarım",
                numberofitems = collections.value.size,
                showallNav = {},
                showallVisible = false
            )
            collections.value.forEach { ac ->
                ListItem(
                    modifier = Modifier.clickable { navController.navigate("mypanel/collections/sub") },
                    colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                    headlineContent = {
                        Text(text = buildAnnotatedString {
                            withStyle(style = listitemTitle) { append("${ac.title}\n") }
                            append("${ac.status.result}\n")
                            append("${ac.date}")
                        }, style = listitemHeaderLineContent)
                    },
                    leadingContent = {
                        Card(
                            modifier = Modifier
                                .size(80.dp, 100.dp)
                                .clip(roundcornerRectangel),
                            shape = roundcornerRectangel,
                            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                        ) {
                            Image(
                                painter = painterResource(id = ac.image),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(80.dp, 100.dp)
                            )
                        }
                    },
                    trailingContent = {
                        Column(
                            modifier = Modifier.size(40.dp, 40.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.round_arrow_forward_ios_24),
                                contentDescription = null,
                                tint = AppColors.grey_124
                            )
                        }
                    }
                )
                HorizontalDivider(thickness = 1.dp, color = AppColors.grey_127)
            }
        }
        AddNewCollectionFormSheet(
            scope,
            showNewCollectionSheet,
            newCollectionSheetState,
            frmTitleValue,
            frmCommentValue
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewCollectionFormSheet(
    scope: CoroutineScope,
    showNewCollectionSheet: MutableState<Boolean>,
    newCollectionSheetState: SheetState,
    frmTitleValue: MutableState<String>,
    frmCommentValue: MutableState<String>
) {
    if (showNewCollectionSheet.value) {
        ModalBottomSheet(
            onDismissRequest = {
                showNewCollectionSheet.value = false
            },
            sheetState = newCollectionSheetState,
            containerColor = Color.White
        ) {
            // Sheet content
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 20.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(26.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            scope
                                .launch { newCollectionSheetState.hide() }
                                .invokeOnCompletion {
                                    if (!newCollectionSheetState.isVisible) {
                                        showNewCollectionSheet.value = false
                                    }
                                }
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        20.dp,
                        Alignment.Start
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                        contentDescription = null
                    )
                    Text(
                        text = "Koleksiyon Oluştur",
                        style = TextStyle(
                            fontFamily = poppinFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                    )
                }

                appTextField(
                    itms = appTextFieldItems(
                        Modifier.border(1.dp, AppColors.grey_130, RoundedCornerShape(8.dp)),
                        Modifier
                            .fillMaxWidth(),
                        frmTitleValue,
                        null,
                        null,
                        "Başlık",
                        false,
                        true,
                        false,
                        true,
                        1,
                        minLines = 1,
                        txtFColors(),
                        txtFKeyboardOptionsCapWord
                    )
                )
                appTextField(
                    itms = appTextFieldItems(
                        Modifier.border(1.dp, AppColors.grey_130, RoundedCornerShape(8.dp)),
                        Modifier
                            .fillMaxWidth(),
                        frmCommentValue,
                        null,
                        null,
                        "Açıklama",
                        false,
                        true,
                        false,
                        false,
                        3,
                        minLines = 1,
                        txtFColors(),
                        txtFKeyboardOptionsCapWord
                    )
                )
                Button(
                    onClick = {
                        scope.launch { newCollectionSheetState.hide() }
                            .invokeOnCompletion {
                                if (!newCollectionSheetState.isVisible) {
                                    showNewCollectionSheet.value = false
                                }
                            }
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColors.red_100,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth(.6f)
                ) {
                    Text(
                        text = "Oluştur ve Kapat",
                        style = TextStyle(
                            fontFamily = poppinFamily,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = painterResource(id = R.drawable.round_arrow_right_alt_24),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                TextButton(onClick = {
                    scope.launch { newCollectionSheetState.hide() }
                        .invokeOnCompletion {
                            if (!newCollectionSheetState.isVisible) {
                                showNewCollectionSheet.value = false
                            }
                        }
                }) {
                    Text(
                        text = "Kapat", style = TextStyle(
                            fontFamily = poppinFamily,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = AppColors.grey_165
                        )
                    )
                }
            }
        }
    }
}