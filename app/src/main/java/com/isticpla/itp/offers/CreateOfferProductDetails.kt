package com.isticpla.itp.offers

import android.util.Log
import android.view.KeyEvent.ACTION_UP
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.rememberDismissState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.ExpendedMenuViewModel
import com.isticpla.itp.dummydata.ProductFeatureItem
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.offerdetails.AdditionalProductDetails
import com.isticpla.itp.offerdetails.OfferViewModel
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.AppDefaultStyleText
import com.isticpla.itp.uimodules.AppTextField
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CreateOfferProductDetails(
    navController: NavController
) {
    val context = LocalContext.current.applicationContext
    val scope = rememberCoroutineScope()
    val homeViewMode = hiltViewModel<HomeViewMode>()
    val offerViewModel = hiltViewModel<OfferViewModel>()
    val expendedMenuViewModel = hiltViewModel<ExpendedMenuViewModel>()

    val txtPNameValue = rememberSaveable { mutableStateOf("") }
    val txtPNameError = rememberSaveable { mutableStateOf(false) }
    val txtComment = rememberSaveable { mutableStateOf("") }
    val listOfPrdFeaturesState =
        homeViewMode.productDRPItems.collectAsState(initial = emptyList<ProductFeatureItem>())

    val pageSheetState = rememberModalBottomSheetState()
    val additionalFeatures =
        offerViewModel.additionalProductDetails.collectAsStateWithLifecycle(initialValue = mutableStateListOf<AdditionalProductDetails>())

    var pageShowBottomSheet by remember { mutableStateOf(false) }

    val formState = remember { mutableStateMapOf<String, String>() }
    BottomSheetScaffold(
        containerColor = Color.White,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            if (pageShowBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        pageShowBottomSheet = false
                    },
                    sheetState = pageSheetState
                ) {
                    // Sheet content
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .fillMaxHeight(.5f),
                        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        //region sheet header
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Ürün Özellik Listesi",
                                style = offerProductDetailFormSectionTitle,
                                modifier = Modifier.weight(1f)
                            )
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        pageSheetState.hide()
                                    }.invokeOnCompletion { pageShowBottomSheet = false }
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.round_clear_24),
                                    contentDescription = null
                                )
                            }
                        }
                        //endregion
                        //region features
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(
                                10.dp,
                                Alignment.CenterVertically
                            ),
                            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start)
                        ) {
                            items(listOfPrdFeaturesState.value) { item ->
                                Button(
                                    onClick = {
                                        scope.launch {
                                            offerViewModel.AddItemToAdditionalProductDetails(
                                                AdditionalProductDetails(formitem = item)
                                            )
                                        }
                                    },
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = AppColors.blue_0xFF0495f1,
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text(
                                        text = item.label,
                                        style = TextStyle(
                                            fontFamily = poppinFamily,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    )
                                }
                            }
                        }
                        //endregion
                    }
                }
            }
        },
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
                .padding(top = 30.dp, bottom = 40.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start
            ) {
                ProposalWizardStage(1, "Ürün Detaylar")
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Ürün Bilgileri", style = offerProductDetailFormSectionTitle)
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.Start
                ) {
                    AppTextField(
                        modifier = Modifier.fillMaxWidth(),
                        txtvalue = txtPNameValue,
                        isError = txtPNameError,
                        singleLine = true,
                        label = { AppDefaultStyleText("Adet") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            autoCorrect = false
                        )
                    )
                    AppTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        txtvalue = txtComment,
                        label = { AppDefaultStyleText("Açıklama") },
                        singleLine = false,
                        minLines = 4,
                        maxLines = Int.MAX_VALUE
                    )

                    Spacer(modifier = Modifier.height(22.dp))
                    //region product features
                    if (additionalFeatures.value.isNotEmpty()) {
                        additionalFeatures.value.forEach { i ->
                            val uuid = UUID.randomUUID().toString()
                            val dismissFormState = rememberDismissState(
                                confirmValueChange = {
                                    if (it == DismissValue.DismissedToStart) {
                                        offerViewModel.removeItemFromAdditionalProductDetails(i)
                                    }
                                    true
                                }
                            )
                            SwipeToDismissBox(
                                state = dismissFormState,
                                directions = setOf(DismissDirection.EndToStart),
                                backgroundContent = {
                                    val color by animateColorAsState(
                                        when (dismissFormState.targetValue) {
                                            DismissValue.Default -> Color.LightGray
                                            DismissValue.DismissedToEnd -> Color.Green
                                            DismissValue.DismissedToStart -> Color.Red
                                        }, label = ""
                                    )
                                    Box(
                                        Modifier
                                            .fillMaxSize()
                                            .background(color, RoundedCornerShape(10.dp))
                                    )

                                }) {
                                AppTextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    txtvalue = i.txtvalue, //mutableStateOf(formState[uuid] ?: i.value),
                                    label = { AppDefaultStyleText(i.formitem.label) },
                                    isError = i.txtiserror,
                                    singleLine = true,
                                )
                            }
                        }
                    }
                    //endregion
                    Spacer(modifier = Modifier.height(20.dp))
                    //region Product Parts
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(
                            26.dp,
                            Alignment.CenterVertically
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = buildAnnotatedString {
                                    append("Ürün Parçaları ")
                                    withStyle(style = offerProductDetailFormSectionSubtitle) {
                                        append(
                                            "(Opsiyonel)"
                                        )
                                    }
                                },
                                style = offerProductDetailFormSectionTitle,
                                modifier = Modifier.weight(1f)
                            )
                            TextButton(
                                onClick = { },
                                colors = ButtonDefaults.textButtonColors(contentColor = AppColors.red_0xffe23e3e)
                            ) {
                                Text(
                                    text = "Yardım",
                                    style = offerHelpButtonLabel,
                                    modifier = Modifier.padding(end = 10.dp)
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.arrow_right),
                                    contentDescription = null
                                )
                            }
                        }
                        Column(

                        ) {
                            ProductParts(
                                headline = "Efsane Hamburger",
                                subheadline = "Bu hamburgeri orta pişmiş ve zeytin ezmesi sosu ile denemlisiniz.",
                                image = R.mipmap.hamburger_01,
                                uri = { navController.navigate("offer/create/productdetails/parts") }
                            )
                        }
                        Button(
                            onClick = {
                                navController.navigate("offer/create/productdetails/parts")
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = AppColors.primaryGrey,
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .fillMaxWidth(.8f)
                                .requiredHeight(48.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.round_add_circle_outline_24),
                                contentDescription = null,
                                modifier = Modifier.padding(end = 10.dp)
                            )
                            Text(
                                text = "Yeni Ürün Parçası Ekle",
                                style = offerStagePublishButton
                            )
                        }
                        Text(
                            text = "Eğer var ise ürün parçalarını ekleyebilirsiniz. Ürün parçalarını eklemeniz siparişinizin daha doğru anlaşılmasını sağlayacaktır.",
                            style = offerProductPairsComment,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.height(50.dp))
                    //endregion
                }


            }

            //region Bottom Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(59.dp)
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
            ) {
                Button(
                    onClick = {
                        scope.launch {
                            pageSheetState.show()
                        }.invokeOnCompletion {
                            pageShowBottomSheet = true
                        }
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColors.green_103,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth(.4f)
                        .requiredHeight(58.dp)
                ) {
                    Text(text = "Ürün Özellik Ekle", style = offerStageAddProductFeatureButton)
                    /*Icon(
                        painter = painterResource(id = R.drawable.round_arrow_right_alt_24),
                        contentDescription = null,
                        tint = Color.White
                    )*/
                }
                Button(
                    onClick = {
                        if (additionalFeatures.value.any { a -> a.txtvalue.value.isEmpty() }) {
                            additionalFeatures.value.filter { a -> a.txtvalue.value.isEmpty() }
                                .forEach { a -> a.txtiserror.value = true }
                            Toast.makeText(
                                context,
                                "Lütfen tüm form verilerini giriniz",
                                Toast.LENGTH_LONG
                            ).show()
                        } else
                            navController.navigate("offer/create/requestdetails")
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColors.grey_130,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(58.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Devam",
                            style = offerStagePublishButton,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.round_arrow_right_alt_24),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
            //endregion
        }
    }
}

@Composable
fun ProductParts(
    headline: String,
    subheadline: String,
    image: Int,
    uri: () -> Unit = { }
) = ListItem(
    modifier = Modifier
        .clickable { uri }
        .clip(RoundedCornerShape(4.dp)),
    colors = ListItemDefaults.colors(
        containerColor = AppColors.gray_FFEEF1F4,
        headlineColor = AppColors.gray_FF666666,
        trailingIconColor = AppColors.gray_FF666666,
    ),
    leadingContent = {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .size(50.dp)
                .border(BorderStroke(4.dp, Color.White), RoundedCornerShape(6.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(46.dp)
            )
        }

    },
    headlineContent = {
        Text(text = buildAnnotatedString {
            append("${headline}\n")
            withStyle(style = offerProductPairsSubHeadline) { append(subheadline) }
        }, style = offerProductPairsHeadline)
    },
    trailingContent = {
        Icon(
            painter = painterResource(id = R.drawable.round_arrow_forward_ios_24),
            contentDescription = null
        )
    }
)