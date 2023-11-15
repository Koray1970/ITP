package com.isticpla.itp.offers

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.isticpla.itp.AppNavigate
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.*
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.home.homeSubSectionTitle
import com.isticpla.itp.offers.ui.*
import com.isticpla.itp.offers.ui.theme.ITPTheme
import com.isticpla.itp.poppinFamily
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateOfferVisualDetails(
    navController: NavController
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
                .padding(horizontal = 10.dp)
                .padding(top = 30.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            ProposalWizardStage(0, "Görsel Detaylar")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CreateOfferProductDetails(
    navController: NavController,
    homeViewMode: HomeViewMode = hiltViewModel(),
) {
    val expendedMenuViewModel = hiltViewModel<ExpendedMenuViewModel>()

    val txtName = rememberSaveable { mutableStateOf("") }
    val txtComment = rememberSaveable { mutableStateOf("") }
    val productDRPMenuExpanded = remember { mutableStateOf(false) }
    val productDRPMenuListdataState =
        homeViewMode.productDRPItems.collectAsState(initial = emptyList<ProductFeatureItem>())
    val prdDRPItems = remember { mutableListOf<Pair<String, String>>() }
    //prdDRPItems = mutableListOf<Pair<String, String>>()
    productDRPMenuListdataState.value.forEach { i ->
        prdDRPItems.add(Pair(i.id.toString(), i.label))
    }
    val txtProductFieldValue = rememberSaveable { mutableStateOf("") }

    /*val listofSelectedDataItems by expendedMenuViewModel.listOfSelectedCollections.collectAsStateWithLifecycle(
        mutableListOf<ExpendedMenuSelectedCollectionItem>()
    )*/

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
                    IconButton(onClick = { navController.navigate("offer/create") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_left),
                            contentDescription = null
                        )
                    }
                }
            )
        }) { innerpadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerpadding)
                .padding(horizontal = 10.dp)
                .padding(top = 30.dp)
                .fillMaxSize(),
        ) {
            item {
                Column {
                    ProposalWizardStage(1, "Ürün Detaylar")
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(text = "Ürün Bilgileri", style = offerProductDetailFormSectionTitle)
                    Spacer(modifier = Modifier.height(8.dp))
                    appTextField(
                        itms = appTextFieldItems(
                            Modifier,
                            Modifier
                                .fillMaxWidth(),
                            txtName,
                            null,
                            null,
                            "Ürün adı",
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
                    Spacer(modifier = Modifier.height(10.dp))
                    appTextField(
                        itms = appTextFieldItems(
                            Modifier,
                            Modifier
                                .fillMaxWidth(),
                            txtComment,
                            null,
                            null,
                            "Açıklama",
                            false,
                            true,
                            false,
                            false,
                            Int.MAX_VALUE,
                            2,
                            txtFColors(),
                            txtFKeyboardOptionsCapSentence
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    DropDownMenuWithAddButton(
                        itms = DropdownMenuItems(
                            txfItems = appTextFieldItems(
                                Modifier,
                                Modifier,
                                txtProductFieldValue,
                                {
                                    Icon(
                                        painter = painterResource(id = R.drawable.round_unfold_more_24),
                                        contentDescription = null
                                    )
                                },
                                null,
                                "Özellikler Seçiniz",
                                false,
                                true,
                                true,
                                true,
                                1,
                                1,
                                txtFColors(),
                                txtFKeyboardOptionsCapSentence
                            ),
                            expanded = productDRPMenuExpanded,
                            menuitems = prdDRPItems.toList(),
                            buttonModifier = Modifier,
                            buttonLabelText = "Ekle",
                            buttonLabelTextStyle = TextStyle(
                                fontFamily = poppinFamily,
                                fontSize = 14.sp,
                                color = Color.White
                            )
                        ),
                        productDRPMenuListdataState.value,
                        expendedMenuViewModel
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Button(
                        onClick = { navController.navigate("offer/create/requestdetails") },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppColors.grey_130,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(48.dp)
                    ) {
                        Text(text = "Devam", style = offerStagePublishButton)
                        Icon(
                            painter = painterResource(id = R.drawable.round_arrow_right_alt_24),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
            /*items(listofSelectedDataItems.size){i->
                Text(listofSelectedDataItems[i].productFeatureItem.label)
            }*/
        }
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateOfferRequestDetails(
    navController: NavController,
) {
    val homeViewModel = hiltViewModel<HomeViewMode>()
    val quantityExpendedState = remember { mutableStateOf(false) }
    val quantityList by homeViewModel.requestQuantity.collectAsStateWithLifecycle(initialValue = listOf<Pair<Int, Int>>())
    val quantityTxtValue = rememberSaveable { mutableStateOf("") }

    val buyerExpendedState = remember { mutableStateOf(false) }
    val buyers by homeViewModel.shopList.collectAsStateWithLifecycle(initialValue = listOf<Pair<Int, String>>())
    val buyerTxtValue = rememberSaveable { mutableStateOf("") }

    val deliveryExpendedState = remember { mutableStateOf(false) }
    val deliveryTypes by homeViewModel.requestDeliveryTypes.collectAsStateWithLifecycle(initialValue = listOf<Pair<Int, String>>())
    val deliveryTxtValue = rememberSaveable { mutableStateOf("") }

    val placeOfDeliveryExpendedState = remember { mutableStateOf(false) }
    val placeOfDelivery by homeViewModel.shopList.collectAsStateWithLifecycle(initialValue = listOf<Pair<Int, String>>())
    val placeOfDeliveryTxtValue = rememberSaveable { mutableStateOf("") }

    val paymentTypeExpendedState = remember { mutableStateOf(false) }
    val paymentTypes by homeViewModel.requestPaymentType.collectAsStateWithLifecycle(initialValue = listOf<Pair<Int, String>>())
    val paymentTypeTxtValue = rememberSaveable { mutableStateOf("") }

    val wantSampleChecked = remember { mutableStateOf(false) }

    val contractedSupplierChecked = remember { mutableStateOf(false) }
    val contractedSupplierCodeTxtValue = rememberSaveable { mutableStateOf("") }

    val recieveOfferFromITPChecked = remember { mutableStateOf(false) }

    val additianalrequestTxtValue = rememberSaveable { mutableStateOf("") }

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
                .padding(horizontal = 10.dp)
                .padding(top = 30.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            ProposalWizardStage(2, "Sipariş Detaylar")
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Sipariş Bilgileri", style = offerProductDetailFormSectionTitle)
            Spacer(modifier = Modifier.height(8.dp))
            DropDownMenu<Int, Int>(
                itms = DropdownMenuItems(
                    txfItems = appTextFieldItems(
                        Modifier,
                        Modifier,
                        quantityTxtValue,
                        {
                            Icon(
                                painter = painterResource(id = R.drawable.round_unfold_more_24),
                                contentDescription = null
                            )
                        },
                        null,
                        "Adet",
                        false,
                        true,
                        true,
                        true,
                        1,
                        1,
                        txtFColors(),
                        txtFKeyboardOptionsCapSentence
                    ),
                    expanded = quantityExpendedState,
                    menuitems = quantityList,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            DropDownMenu<Int, String>(
                itms = DropdownMenuItems(
                    txfItems = appTextFieldItems(
                        Modifier,
                        Modifier,
                        buyerTxtValue,
                        {
                            Icon(
                                painter = painterResource(id = R.drawable.round_unfold_more_24),
                                contentDescription = null
                            )
                        },
                        null,
                        "Alıcı Firma",
                        false,
                        true,
                        true,
                        true,
                        1,
                        1,
                        txtFColors(),
                        txtFKeyboardOptionsCapSentence
                    ),
                    expanded = buyerExpendedState,
                    menuitems = buyers,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            DropDownMenu<Int, String>(
                itms = DropdownMenuItems(
                    txfItems = appTextFieldItems(
                        Modifier,
                        Modifier,
                        deliveryTxtValue,
                        {
                            Icon(
                                painter = painterResource(id = R.drawable.round_unfold_more_24),
                                contentDescription = null
                            )
                        },
                        null,
                        "Teslimat Şekli",
                        false,
                        true,
                        true,
                        true,
                        1,
                        1,
                        txtFColors(),
                        txtFKeyboardOptionsCapSentence
                    ),
                    expanded = deliveryExpendedState,
                    menuitems = deliveryTypes,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            DropDownMenu<Int, String>(
                itms = DropdownMenuItems(
                    txfItems = appTextFieldItems(
                        Modifier,
                        Modifier,
                        placeOfDeliveryTxtValue,
                        {
                            Icon(
                                painter = painterResource(id = R.drawable.round_unfold_more_24),
                                contentDescription = null
                            )
                        },
                        null,
                        "Teslimat Yeri",
                        false,
                        true,
                        true,
                        true,
                        1,
                        1,
                        txtFColors(),
                        txtFKeyboardOptionsCapSentence
                    ),
                    expanded = placeOfDeliveryExpendedState,
                    menuitems = placeOfDelivery,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            DropDownMenu<Int, String>(
                itms = DropdownMenuItems(
                    txfItems = appTextFieldItems(
                        Modifier,
                        Modifier,
                        paymentTypeTxtValue,
                        {
                            Icon(
                                painter = painterResource(id = R.drawable.round_unfold_more_24),
                                contentDescription = null
                            )
                        },
                        null,
                        "Ödeme Şekli",
                        false,
                        true,
                        true,
                        true,
                        1,
                        1,
                        txtFColors(),
                        txtFKeyboardOptionsCapSentence
                    ),
                    expanded = paymentTypeExpendedState,
                    menuitems = paymentTypes,
                )
            )
            Spacer(modifier = Modifier.height(60.dp))
            Text(text = "Numune Talebi Var Mı?", style = offerProductDetailFormSectionTitle)
            AppSwitch(wantSampleChecked)

            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = "Anlaşmalı Tedarikçi ile Çalışmak İstiyor musunuz?",
                style = offerProductDetailFormSectionTitle
            )
            AppSwitch(contractedSupplierChecked)

            Spacer(modifier = Modifier.height(8.dp))
            appTextField(
                itms = appTextFieldItems(
                    Modifier,
                    Modifier
                        .fillMaxWidth(),
                    contractedSupplierCodeTxtValue,
                    null,
                    null,
                    "Tedarikçi Kodu Gir",
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
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                "ITP'den de Teklif Almak İster misiniz?",
                style = offerProductDetailFormSectionTitle
            )
            AppSwitch(recieveOfferFromITPChecked)


            Spacer(modifier = Modifier.height(30.dp))
            appTextField(
                itms = appTextFieldItems(
                    Modifier,
                    Modifier
                        .fillMaxWidth(),
                    additianalrequestTxtValue,
                    null,
                    null,
                    "Ek Açıklamalar",
                    false,
                    true,
                    false,
                    false,
                    Int.MAX_VALUE,
                    minLines = 5,
                    txtFColors(),
                    txtFKeyboardOptionsCapWord
                )
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = { navController.navigate("offer/create/preview") },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.grey_130,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(48.dp)
            ) {
                Text(text = "Devam", style = offerStagePublishButton)
                Icon(
                    painter = painterResource(id = R.drawable.round_arrow_right_alt_24),
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateOfferPreview(
    navController: NavController,
) {
    var procuctDetailExpendedState by remember { mutableStateOf(true) }
    var procuctDetailExpendedIcon by remember { mutableStateOf(R.drawable.baseline_expand_more_24) }

    var orderDetailExpendedState by remember { mutableStateOf(true) }
    var orderDetailExpendedIcon by remember { mutableStateOf(R.drawable.baseline_expand_more_24) }

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
                .padding(horizontal = 10.dp)
                .padding(top = 30.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            ProposalWizardStage(3, "Önizleme")
            Spacer(modifier = Modifier.height(40.dp))
            //start:Product details accordion
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        val borderSize = 2.dp.toPx()
                        drawLine(
                            color = AppColors.grey_133,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = borderSize
                        )
                    }
                    .padding(bottom = 10.dp)
            ) {
                Text(text = "Ürün Detayları", style = previewAccordionHeader)
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    procuctDetailExpendedState = !procuctDetailExpendedState
                    procuctDetailExpendedIcon = if (procuctDetailExpendedState)
                        R.drawable.baseline_expand_more_24
                    else
                        R.drawable.round_expand_less_24

                }) {
                    Icon(
                        painter = painterResource(id = procuctDetailExpendedIcon),
                        contentDescription = null,
                    )
                }
            }
            AnimatedVisibility(visible = procuctDetailExpendedState) {
                Column() {
                    Spacer(modifier = Modifier.requiredHeight(30.dp))
                    Text(text = buildAnnotatedString {
                        withStyle(style = previewItemTitle) { append("Başlık\n") }
                        withStyle(style = previewItemContent) { append("Tillman`s Hamburger vom Rind, 4 Stück, 250g") }
                    })
                    Spacer(modifier = Modifier.requiredHeight(30.dp))
                    Text(text = buildAnnotatedString {
                        withStyle(style = previewItemTitle) { append("Açıklama\n") }
                        withStyle(style = previewItemContent) {
                            append(
                                "İlave renklendirici yok, ilave tat yok, koruyucu yok. \n" +
                                        "4 Stück Hamburger für Grill und Pfanne\n" +
                                        "Aus leckerem Rinfleisch, 100% Geschmack, 100% Qualität\n" +
                                        "Schnell, lecker, trendy\n" +
                                        "Höchste Qualität\n" +
                                        "100% Geschmack\n" +
                                        "Absolute Frische"
                            )
                        }
                    })
                    Spacer(modifier = Modifier.requiredHeight(30.dp))
                    Text(text = buildAnnotatedString {
                        withStyle(style = previewItemTitle) { append("Örnek Ürün Linkleri") }
                    })
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(
                            8.dp,
                            Alignment.CenterVertically
                        ),
                        horizontalAlignment = Alignment.Start
                    ) {
                        repeat(3) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(6.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = AppColors.grey_127,
                                )
                            ) {
                                Column(
                                    modifier = Modifier.padding(18.dp)
                                ) {
                                    Text(text = buildAnnotatedString {
                                        withStyle(style = previewCardItemTitle) { append("Indonesian chicken burger\n") }
                                        withStyle(style = previewCardItemContent) { append("www.amazone.de") }
                                    }, style = TextStyle(lineHeight = 11.sp))
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.requiredHeight(30.dp))

                    Text(text = buildAnnotatedString {
                        withStyle(style = previewItemTitle) { append("Ham Madde\n") }
                        withStyle(style = previewItemContent) { append("Metal, Cam, Ahşap") }
                    })
                    Spacer(modifier = Modifier.requiredHeight(30.dp))

                    Text(text = buildAnnotatedString {
                        withStyle(style = previewItemTitle) { append("Renk\n") }
                        withStyle(style = previewItemContent) { append("Kahverengi, Siyah") }
                    })
                    Spacer(modifier = Modifier.requiredHeight(30.dp))

                    Text(text = buildAnnotatedString {
                        withStyle(style = previewItemTitle) { append("Sertifika Talepleriniz\n") }
                        withStyle(style = previewItemContent) { append("ISO 9001, 10001") }
                    })
                    Spacer(modifier = Modifier.requiredHeight(60.dp))
                }
            }
            //end:Product details accordion


            //start:Order details accordion
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        val borderSize = 2.dp.toPx()
                        drawLine(
                            color = AppColors.grey_133,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = borderSize
                        )
                    }
                    .padding(bottom = 10.dp)
            ) {
                Text(text = "Sipariş Bilgileri", style = previewAccordionHeader)
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    orderDetailExpendedState = !orderDetailExpendedState
                    orderDetailExpendedIcon = if (orderDetailExpendedState)
                        R.drawable.baseline_expand_more_24
                    else
                        R.drawable.round_expand_less_24

                }) {
                    Icon(
                        painter = painterResource(id = orderDetailExpendedIcon),
                        contentDescription = null,
                    )
                }
            }
            AnimatedVisibility(visible = orderDetailExpendedState) {
                Column() {
                    Spacer(modifier = Modifier.requiredHeight(30.dp))
                    Text(text = buildAnnotatedString {
                        withStyle(style = previewItemTitle) { append("Adet\n") }
                        withStyle(style = previewItemContent) { append("1000 adet") }
                    })
                    Spacer(modifier = Modifier.requiredHeight(30.dp))
                    Text(text = buildAnnotatedString {
                        withStyle(style = previewItemTitle) { append("Tarih\n") }
                        withStyle(style = previewItemContent) { append("24/10/2023 son tarih") }
                    })
                    Spacer(modifier = Modifier.requiredHeight(30.dp))
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(text = buildAnnotatedString {
                            withStyle(style = previewItemTitle) {
                                append(
                                    "Alıcı Firma"
                                )
                            }
                        })
                        Spacer(modifier = Modifier.requiredHeight(2.dp))
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(6.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = AppColors.grey_127,
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(18.dp)
                            ) {
                                Text(text = buildAnnotatedString {
                                    withStyle(style = previewCardItemTitle) { append("Tilman’s Market\n") }
                                    withStyle(style = previewCardItemContent) { append("Neue Strasse 52, Bremen, Deutschland") }
                                }, style = TextStyle(lineHeight = 11.sp))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.requiredHeight(30.dp))

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(text = buildAnnotatedString {
                            withStyle(style = previewItemTitle) {
                                append(
                                    "Teslim Şekli"
                                )
                            }
                        })
                        Spacer(modifier = Modifier.requiredHeight(2.dp))
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(6.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = AppColors.grey_127,
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(18.dp)
                            ) {
                                Text(text = buildAnnotatedString {
                                    withStyle(style = previewCardItemTitle) { append("Kapıdan Kapıya\n") }
                                    withStyle(style = previewCardItemContent) { append("Kargo Dahil") }
                                }, style = TextStyle(lineHeight = 11.sp))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.requiredHeight(30.dp))

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(text = buildAnnotatedString {
                            withStyle(style = previewItemTitle) {
                                append(
                                    "Teslim Yeri"
                                )
                            }
                        }, style = TextStyle(lineHeight = 11.sp))
                        Spacer(modifier = Modifier.requiredHeight(2.dp))
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(6.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = AppColors.grey_127,
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(18.dp)
                            ) {
                                Text(text = buildAnnotatedString {
                                    withStyle(style = previewCardItemSubTitle) { append("Kapıda Teslim\n") }
                                    withStyle(style = previewCardItemTitle) { append("Tilman’s Market\n") }
                                    withStyle(style = previewCardItemContent) { append("Neue Strasse 52, Bremen, Deutschland") }
                                }, style = TextStyle(lineHeight = 11.sp))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.requiredHeight(30.dp))

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(text = buildAnnotatedString {
                            withStyle(style = previewItemTitle) {
                                append(
                                    "Ödeme Şekli"
                                )
                            }
                        })
                        Spacer(modifier = Modifier.requiredHeight(2.dp))
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(6.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = AppColors.grey_127,
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(18.dp)
                            ) {
                                Text(text = buildAnnotatedString {
                                    withStyle(style = previewCardItemTitle) { append("Havale, Standart Ödeme\n") }
                                    withStyle(style = previewCardItemContent) { append("Sipariş sonrası peşin ödeme") }
                                }, style = TextStyle(lineHeight = 11.sp))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.requiredHeight(30.dp))

                    Text(text = buildAnnotatedString {
                        withStyle(style = previewItemTitle) { append("Numune Talebi\n") }
                        withStyle(style = previewItemContent) { append("Evet Numune İstiyorum") }
                    })
                    Spacer(modifier = Modifier.requiredHeight(30.dp))

                    Text(text = buildAnnotatedString {
                        withStyle(style = previewItemTitle) { append("Ek Açıklamalar\n") }
                        withStyle(style = previewItemContent) { append("Gelip yerinde görmek istiyorum. Ürün kalitesini görmek ve kendim test edip bilmek istiyorum.") }
                    })
                    Spacer(modifier = Modifier.requiredHeight(30.dp))

                    Text(text = buildAnnotatedString {
                        withStyle(style = previewItemTitle) { append("Teklif Alacağınız Yerler\n") }
                        withStyle(style = previewItemContent) {
                            append(
                                "Anlaşmalı Tedarikçiler\n" +
                                        "A Firması\n" +
                                        "B. Firması\n" +
                                        "C Firması"
                            )
                        }
                    })
                    Spacer(modifier = Modifier.requiredHeight(30.dp))

                    Text(text = buildAnnotatedString {
                        withStyle(style = previewItemContent) { append("ITP Teklif Verecek") }
                    })
                    Spacer(modifier = Modifier.requiredHeight(60.dp))
                }
            }
            //end:Order details accordion
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = { navController.navigate("offer/create/publish") },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.green_100,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(48.dp)
            ) {
                Text(text = "İnceledim ve Onaylıyorum", style = offerStagePublishButton)
                Icon(
                    painter = painterResource(id = R.drawable.round_arrow_right_alt_24),
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { navController.navigate("offer/dashboard") },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColors.grey_133,
                        contentColor = AppColors.primaryGrey
                    ),
                    modifier = Modifier
                        .requiredHeight(48.dp)
                ) {
                    Text(text = "Taslağa Dön", style = offerStageDefaultButton)
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateOfferPublish(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.mipmap.confeti),
            contentDescription = null,
            modifier = Modifier.size(271.dp)
        )
        Spacer(modifier = Modifier.height(22.dp))
        Text(text = buildAnnotatedString {
            withStyle(style = offerFinalTitle) {
                append("Teklif Talebin Alındı\n\n")
            }
            withStyle(style = offerFinalSubTitle) {
                append("Ürün bilgilerinizin incelenmesinin ardından teklif operasyonları başlatılacaktır\n\n")
            }
            withStyle(style = offerFinalCoupon1) {
                append("TAKİP KODU: ")
            }
            withStyle(style = offerFinalCoupon2) {
                append("XR47HYGFV")
            }
        }, style = offerFinalStyle)
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedButton(
            onClick = { navController.navigate("offer/detail/dashboard") },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White),
            border = BorderStroke(width = 1.dp, color = AppColors.red_100),
            modifier = Modifier.fillMaxWidth(.60f)
        ) {
            Text(
                text = "Teklif Detaylarına Git",
                style = TextStyle(
                    fontFamily = poppinFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppColors.red_100
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.round_arrow_right_alt_24),
                contentDescription = null,
                tint = AppColors.red_100
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(.60f),
            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                HorizontalDivider(thickness = 1.dp, color = AppColors.grey_113)
            }
            Box(
                modifier = Modifier
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(5.dp)
                ) {
                    Text(
                        text = "Ya da",
                        style = TextStyle(
                            fontFamily = poppinFamily,
                            fontSize = 14.sp,
                            color = AppColors.grey_168
                        )
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedButton(
            onClick = {},
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White),
            border = BorderStroke(width = 1.dp, color = AppColors.red_100),
            modifier = Modifier.fillMaxWidth(.60f)
        ) {
            Text(
                text = "Kopya Oluştur",
                style = TextStyle(
                    fontFamily = poppinFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppColors.red_100
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.round_arrow_right_alt_24),
                contentDescription = null,
                tint = AppColors.red_100
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(.60f),
            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                HorizontalDivider(thickness = 1.dp, color = AppColors.grey_113)
            }
            Box(
                modifier = Modifier
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(5.dp)
                ) {
                    Text(
                        text = "Veya",
                        style = TextStyle(
                            fontFamily = poppinFamily,
                            fontSize = 14.sp,
                            color = AppColors.grey_168
                        )
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Button(
            onClick = { navController.navigate("home") },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppColors.red_100,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(.6f)
        ) {
            Text(
                text = "Ana Sayfa",
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
        Spacer(modifier = Modifier.height(6.dp))
        Button(
            onClick = { navController.navigate("feed") },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppColors.red_100,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(.6f)
        ) {
            Text(
                text = "Benim Sayfam",
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
    }
}

