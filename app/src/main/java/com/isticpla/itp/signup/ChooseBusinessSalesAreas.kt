package com.isticpla.itp.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.gson.Gson
import com.isticpla.itp.R
import com.isticpla.itp.database.Account
import com.isticpla.itp.database.AccountViewModel
import com.isticpla.itp.dummydata.BusinessTypeItem
import com.isticpla.itp.dummydata.listofBusiness
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseBusinessSalesAreas(navController: NavController) {
    val context = LocalContext.current.applicationContext
    val scope = rememberCoroutineScope()
    val gson = Gson()
    val homeViewModel = hiltViewModel<HomeViewMode>()
    val accountViewModel = hiltViewModel<AccountViewModel>()

    var listofSector = remember { mutableStateListOf<BusinessTypeItem>() }
    val getSectorList =
        homeViewModel.sectorList.collectAsStateWithLifecycle(initialValue = mutableListOf<BusinessTypeItem>())
    LaunchedEffect(Unit) {
        delay(300)
        listofSector = getSectorList.value.toMutableStateList()
    }

    var selectedBusinessTypes = remember { mutableStateListOf<BusinessTypeItem>() }

    var snackBarShowState = remember { SnackbarHostState() }

    var accountinfo =
        accountViewModel.getAccount.collectAsStateWithLifecycle(initialValue = Account())

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), containerColor = Color.White,
        snackbarHost = { SnackbarHost(hostState = snackBarShowState) }
    ) { innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 72.dp, bottom = 30.dp)
                    .height(10.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .weight(.33f)
                        .height(10.dp)
                        .background(color = AppColors.yellow_100)
                )
                Spacer(modifier = Modifier.weight(.05f))
                Box(
                    modifier = Modifier
                        .weight(.33f)
                        .height(10.dp)
                        .background(color = AppColors.yellow_100)
                )
                Spacer(modifier = Modifier.weight(.05f))
                Box(
                    modifier = Modifier
                        .weight(.33f)
                        .height(10.dp)
                        .background(color = AppColors.yellow_100)
                )
            }
            val headerReq = SignUpHeaderRequest()
            headerReq.title = "İşletmenizin satış alanlarını seçin"
            headerReq.subtitle =
                "Bu bilgileri işletmenizi daha yakından tanımak ve daha doğru tavsiyeler paylaşmak için istiyoruz"
            SingUpHeader(context = context, request = headerReq)
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Sektörler", style = TextStyle(
                    fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = AppColors.grey_124
                ), textAlign = TextAlign.Left, modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .horizontalScroll(
                        rememberScrollState()
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(3.dp, Alignment.Start)
            ) {
                if (getSectorList.value.isNotEmpty()) {
                    getSectorList.value.forEach { b ->
                        Card(
                            colors = CardColors(
                                containerColor = AppColors.grey_133,
                                contentColor = AppColors.primaryGrey,
                                disabledContainerColor = AppColors.grey_133,
                                disabledContentColor = AppColors.grey_135
                            ),
                            enabled = b.isSelected.value,
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier
                                .padding(all = 10.dp)
                                .width(70.dp)
                                .height(92.dp)
                                .alpha(if (!b.isSelected.value) .25f else 1f),
                            onClick = {
                                selectedBusinessTypes.add(b)
                                b.isSelected.value = false
                            }) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    painter = painterResource(id = b.icon!!),
                                    contentDescription = null,
                                    tint = AppColors.primaryGrey
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = buildAnnotatedString {
                                        append(b.label!!)
                                    }, style = TextStyle(
                                        fontFamily = poppinFamily,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = AppColors.primaryGrey
                                    )
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Seçilen sektörler", style = TextStyle(
                    fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = AppColors.grey_124
                ), textAlign = TextAlign.Left, modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .horizontalScroll(
                        rememberScrollState()
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(3.dp, Alignment.Start)
            ) {
                if (selectedBusinessTypes.isNotEmpty()) {
                    selectedBusinessTypes.forEach { b ->
                        Card(colors = CardColors(
                            containerColor = AppColors.primaryGrey,
                            contentColor = Color.White,
                            disabledContainerColor = AppColors.primaryGrey,
                            disabledContentColor = Color.White
                        ),
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier
                                .padding(all = 10.dp)
                                .width(70.dp)
                                .height(92.dp),
                            onClick = {
                                getSectorList.value.first { a -> a.id == b.id }.isSelected.value =
                                    true
                                selectedBusinessTypes.removeAt(selectedBusinessTypes.indexOf(b))
                            }) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    painter = painterResource(id = b.icon!!),
                                    contentDescription = null,
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = b.label!!, style = TextStyle(
                                        fontFamily = poppinFamily,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
                    scope.launch {
                        if (selectedBusinessTypes.isEmpty()) {
                            snackBarShowState.showSnackbar("Lütfen İşletmenizin satış alanlarından birini seçiniz!")
                        } else {
                            accountinfo.value.sectors = gson.toJson(selectedBusinessTypes)
                            accountViewModel.UpsertAccount(accountinfo.value)
                            delay(300)
                            navController.navigate("home")
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.primaryGrey,//context.getColor(R.color.gray99)
                    contentColor = Color.White,
                    disabledContainerColor = AppColors.primaryGrey,//context.getColor(R.color.gray99)
                    disabledContentColor = Color.White
                )
            ) {
                Text(text = "Devam Et", style = signupSubmitButton)
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(70.dp))
        }
    }
}