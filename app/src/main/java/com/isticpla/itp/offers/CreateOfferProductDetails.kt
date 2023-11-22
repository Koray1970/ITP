package com.isticpla.itp.offers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.ExpendedMenuViewModel
import com.isticpla.itp.dummydata.ProductFeatureItem
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.AppDefaultStyleText
import com.isticpla.itp.uimodules.AppTextField

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
    BottomSheetScaffold(
        containerColor = Color.White,
        sheetContent = {

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
                    /*AppTextField(
                        modifier = Modifier.fillMaxWidth(),
                        txtvalue = txtName,
                        isError = quantityError,
                        singleLine = true,
                        label = { AppDefaultStyleText("Adet") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            autoCorrect = false
                        )
                    )*/
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