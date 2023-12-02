package com.isticpla.itp.offers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.google.gson.Gson
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.ServiceContentType
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.offerdetails.OfferViewModel
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.AppDefaultStyleText
import com.isticpla.itp.uimodules.AppDropdown
import com.isticpla.itp.uimodules.AppExposedDropdownMenuBox
import com.isticpla.itp.uimodules.AppSwitch
import com.isticpla.itp.uimodules.AppTextField
import com.isticpla.itp.uimodules.AppTextFieldDefaults
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateOfferAsService(
    navController: NavController
) {
    val scope = rememberCoroutineScope()

    val offerViewModel = hiltViewModel<OfferViewModel>()
    val servicelist =
        offerViewModel.getServiceTypes.collectAsStateWithLifecycle(initialValue = emptyList<Pair<String, String>>())


    val txtDrafNameValue = remember { mutableStateOf("") }
    val txtDrafNameError = remember { mutableStateOf(false) }

    val drpServiceTypeExpended = remember { mutableStateOf(false) }
    val drpServiceTypeError = remember { mutableStateOf(false) }
    val drpServiceTypeValue = remember { mutableStateOf("") }
    var drpServiceTypeKey = remember { mutableStateOf("") }


    val drpServiceContentTypeExpended = remember { mutableStateOf(false) }
    val drpServiceContentTypeError = remember { mutableStateOf(false) }
    var drpServiceContentTypeValue = remember { mutableStateOf("") }
    var drpServiceContentTypeKey = remember { mutableStateOf("") }
    var listofServiceContentType = remember { mutableListOf<Pair<String, String>>() }
    LaunchedEffect(Unit) {
        offerViewModel.expendedServiceContents.collectLatest {
            listofServiceContentType = it
        }
    }
    val homeViewModel = hiltViewModel<HomeViewMode>()
    val buyerExpendedState = remember { mutableStateOf(false) }
    val buyerError = remember { mutableStateOf(false) }
    val buyers by homeViewModel.shopListAsPairs().collectAsStateWithLifecycle(initialValue = listOf<Pair<Int, String>>())
    val buyerTxtValue = rememberSaveable { mutableStateOf("") }

    val paymentTypeExpendedState = remember { mutableStateOf(false) }
    val paymentTypeError = remember { mutableStateOf(false) }
    val paymentTypes by homeViewModel.requestPaymentType.collectAsStateWithLifecycle(initialValue = listOf<Pair<Int, String>>())
    val paymentTypeTxtValue = rememberSaveable { mutableStateOf("") }

    val contractedSupplierChecked = remember { mutableStateOf(false) }
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
                    IconButton(onClick = {
                        //.navigate("offer/create/productdetails")
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
                .padding(innerpadding)
                .padding(horizontal = 10.dp)
                .padding(top = 30.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.Start
        ) {
            ProposalStraightStage("Sipariş Detaylar")
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Sipariş Bilgileri", style = offerProductDetailFormSectionTitle)
            Spacer(modifier = Modifier.height(8.dp))
            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                txtvalue = txtDrafNameValue,
                isError = txtDrafNameError,
                singleLine = true,
                label = { AppDefaultStyleText("Taslak Adı") },
            )
            AppDropdown(
                expended = drpServiceTypeExpended,
                isError = drpServiceTypeError,
                selectedOptionText = drpServiceTypeValue,
                selectedOptionKey = drpServiceTypeKey,
                onClearIconClickEvent = {
                    scope.launch {
                        drpServiceContentTypeValue.value=""
                        drpServiceContentTypeKey.value=""
                        drpServiceContentTypeError.value = false
                        delay(200L)
                        //println("_serviceContentList: ${gson.toJson(serviceContentsTypes)}")
                    }
                },
                onMenuItemClickEvent = {
                    scope.launch {
                        drpServiceContentTypeValue.value = ""
                        drpServiceContentTypeKey.value = ""
                        drpServiceContentTypeError.value = false
                        offerViewModel.onServiceSelected(drpServiceTypeKey.value)
                        delay(200L)
                        //println("_serviceContentList: ${gson.toJson(serviceContentsTypes)}")
                    }
                },
                listdata = servicelist.value,
                dropdownlabel = { AppDefaultStyleText("Hizmet türünü seçiniz") }
            )
            AppExposedDropdownMenuBox(
                expended = drpServiceContentTypeExpended,
                isError = drpServiceContentTypeError,
                selectedOptionText = drpServiceContentTypeValue,
                selectedOptionKey = drpServiceContentTypeKey,
                listdata = listofServiceContentType,
                dropdownlabel = { AppDefaultStyleText("Hizmet içerik türünü seçiniz") }
            )
            AppDropdown(
                expended = buyerExpendedState,
                isError = buyerError,
                selectedOptionText = buyerTxtValue,
                listdata = buyers,
                dropdownlabel = { AppDefaultStyleText("Alıcı Firma") }
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
                shape= AppTextFieldDefaults.RoundCornderShape(3),
                txtvalue = additianalrequestTxtValue,
                singleLine = false,
                minLines = 5,
                label = { AppDefaultStyleText("Ek Açıklamalar") },
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
                    if (txtDrafNameValue.value.isNullOrEmpty() ||
                        drpServiceTypeValue.value.isNullOrEmpty() ||
                        drpServiceContentTypeValue.value.isNullOrEmpty()
                    ) {
                        txtDrafNameError.value=txtDrafNameValue.value.isNullOrEmpty()
                        drpServiceTypeError.value=drpServiceTypeValue.value.isNullOrEmpty()
                        drpServiceContentTypeError.value=drpServiceContentTypeValue.value.isNullOrEmpty()
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