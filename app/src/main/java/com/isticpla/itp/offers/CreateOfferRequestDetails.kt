package com.isticpla.itp.offers

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.AppDefaultStyleText
import com.isticpla.itp.uimodules.AppDropdown
import com.isticpla.itp.uimodules.AppSwitch
import com.isticpla.itp.uimodules.AppTextField
import com.isticpla.itp.uimodules.AppTextFieldDefaults

@RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateOfferRequestDetails(
    navController: NavController,
) {
    val homeViewModel = hiltViewModel<HomeViewMode>()
    val quantityError = remember { mutableStateOf(false) }
    val quantityList by homeViewModel.requestQuantity.collectAsStateWithLifecycle(initialValue = listOf<Pair<Int, Int>>())
    val quantityTxtValue = rememberSaveable { mutableStateOf("") }

    val buyerExpendedState = remember { mutableStateOf(false) }
    val buyerError = remember { mutableStateOf(false) }
    val buyers by homeViewModel.shopList.collectAsStateWithLifecycle(initialValue = listOf<Pair<Int, String>>())
    val buyerTxtValue = rememberSaveable { mutableStateOf("") }

    val deliveryExpendedState = remember { mutableStateOf(false) }
    val deliveryError = remember { mutableStateOf(false) }
    val deliveryTypes by homeViewModel.requestDeliveryTypes.collectAsStateWithLifecycle(initialValue = listOf<Pair<Int, String>>())
    val deliveryTxtValue = rememberSaveable { mutableStateOf("") }

    val placeOfDeliveryExpendedState = remember { mutableStateOf(false) }
    val placeOfDeliveryError = remember { mutableStateOf(false) }
    val placeOfDelivery by homeViewModel.shopList.collectAsStateWithLifecycle(initialValue = listOf<Pair<Int, String>>())
    val placeOfDeliveryTxtValue = rememberSaveable { mutableStateOf("") }

    val paymentTypeExpendedState = remember { mutableStateOf(false) }
    val paymentTypeError = remember { mutableStateOf(false) }
    val paymentTypes by homeViewModel.requestPaymentType.collectAsStateWithLifecycle(initialValue = listOf<Pair<Int, String>>())
    val paymentTypeTxtValue = rememberSaveable { mutableStateOf("") }

    val wantSampleChecked = remember { mutableStateOf(false) }

    val contractedSupplierChecked = remember { mutableStateOf(false) }
    val contractedSupplierCodeTxtValue = rememberSaveable { mutableStateOf("") }
    val contractedSupplierCodeValue = remember { mutableStateOf("") }

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
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.Start
        ) {
            ProposalWizardStage(2, "Sipariş Detaylar")
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Sipariş Bilgileri", style = offerProductDetailFormSectionTitle)
            Spacer(modifier = Modifier.height(8.dp))
            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                txtvalue = quantityTxtValue,
                isError = quantityError,
                singleLine = true,
                label = { AppDefaultStyleText("Adet") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    autoCorrect = false
                )
            )
            AppDropdown(
                expended = buyerExpendedState,
                isError = buyerError,
                selectedOptionText = buyerTxtValue,
                listdata = buyers,
                dropdownlabel = { AppDefaultStyleText("Alıcı Firma") }
            )
            AppDropdown(
                expended = deliveryExpendedState,
                isError = deliveryError,
                selectedOptionText = deliveryTxtValue,
                listdata = deliveryTypes,
                dropdownlabel = { AppDefaultStyleText("Teslimat Şekli") }
            )
            AppDropdown(
                expended = placeOfDeliveryExpendedState,
                isError = placeOfDeliveryError,
                selectedOptionText = placeOfDeliveryTxtValue,
                listdata = placeOfDelivery,
                dropdownlabel = { AppDefaultStyleText("Teslimat Yeri") }
            )
            AppDropdown(
                expended = paymentTypeExpendedState,
                isError = paymentTypeError,
                selectedOptionText = paymentTypeTxtValue,
                listdata = paymentTypes,
                dropdownlabel = { AppDefaultStyleText("Ödeme Şekli") }
            )
            Spacer(modifier = Modifier.height(30.dp))
            Column() {
                Text(text = "Numune Talebi Var Mı?", style = offerProductDetailFormSectionTitle)
                AppSwitch(wantSampleChecked)
            }
            Spacer(modifier = Modifier.height(30.dp))
            Column() {
                Text(
                    text = "Anlaşmalı Tedarikçi ile Çalışmak İstiyor musunuz?",
                    style = offerProductDetailFormSectionTitle
                )
                AppSwitch(contractedSupplierChecked)
            }
            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                txtvalue = contractedSupplierCodeValue,
                singleLine = true,
                enabled = contractedSupplierChecked,
                label = { AppDefaultStyleText("Tedarikçi Kodu") },
            )

            Spacer(modifier = Modifier.height(60.dp))
            Column() {
                Text(
                    "ITP'den de Teklif Almak İster misiniz?",
                    style = offerProductDetailFormSectionTitle
                )
                AppSwitch(recieveOfferFromITPChecked)
            }

            Spacer(modifier = Modifier.height(30.dp))
            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                txtvalue = additianalrequestTxtValue,
                singleLine = false,
                minLines = 5,
                label = { AppDefaultStyleText("Ek Açıklamalar") },
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
                    if (quantityTxtValue.value.isNullOrEmpty() ||
                        buyerTxtValue.value.isNullOrEmpty() ||
                        deliveryTxtValue.value.isNullOrEmpty() ||
                        placeOfDeliveryTxtValue.value.isNullOrEmpty() ||
                        paymentTypeTxtValue.value.isNullOrEmpty()
                    ) {
                        quantityError.value = quantityTxtValue.value.isNullOrEmpty()
                        buyerError.value = buyerTxtValue.value.isNullOrEmpty()
                        deliveryError.value = deliveryTxtValue.value.isNullOrEmpty()
                        placeOfDeliveryError.value = placeOfDeliveryTxtValue.value.isNullOrEmpty()
                        paymentTypeError.value = paymentTypeTxtValue.value.isNullOrEmpty()
                    } else {
                        navController.navigate("offer/create/preview")
                    }
                },
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