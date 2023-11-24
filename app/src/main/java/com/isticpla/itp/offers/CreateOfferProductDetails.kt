package com.isticpla.itp.offers

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
                                            additionalFeatures.value.add(
                                                AdditionalProductDetails(formitem = item))
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
            verticalArrangement = Arrangement.SpaceBetween
        ) {

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
                        modifier = Modifier.fillMaxWidth(),
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
                            val dismissFormState = rememberDismissState(
                                confirmValueChange = {
                                    if (it == DismissValue.DismissedToStart) {
                                        scope.launch {
                                            additionalFeatures.value.remove(i)
                                            delay(200)
                                        }
                                    }
                                    true
                                }
                            )
                            val uuid = UUID.randomUUID().toString()
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
                                    txtvalue = mutableStateOf(formState[uuid] ?: i.value),
                                    label = { AppDefaultStyleText(i.formitem.label) },
                                    isError = mutableStateOf(
                                        (formState[uuid] ?: i.value).isNotEmpty()
                                    ),
                                    singleLine = true,
                                )
                            }
                        }
                    }
                    //endregion
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
            //region Bottom Buttons
            Row(
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
                        if (formState.toMap().containsValue("")) {
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