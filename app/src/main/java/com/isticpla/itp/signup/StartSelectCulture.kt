package com.isticpla.itp.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.database.Account
import com.isticpla.itp.database.AccountViewModel
import com.isticpla.itp.dummydata.AppCultureDataModel
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.AppCultureDropdown
import com.isticpla.itp.uimodules.AppTextFieldDefaults
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun StartSelectCulture(
    navController: NavController,
) {
    val context = LocalContext.current.applicationContext
    val scope = rememberCoroutineScope()
    val homeViewModel = hiltViewModel<HomeViewMode>()
    val accountViewModel = hiltViewModel<AccountViewModel>()
    var getaccountdb =
        accountViewModel.getAccount.collectAsStateWithLifecycle(initialValue = Account())

    val listOfAppCulture =
        homeViewModel.appCultures.collectAsStateWithLifecycle(initialValue = mutableListOf<AppCultureDataModel>())
    val firstCulture = homeViewModel.firstCulture()
        .collectAsStateWithLifecycle(initialValue = AppCultureDataModel(0, 0, "", false))
    var expanded = remember { mutableStateOf(false) }
    var selectedOptionValue = remember {
        mutableStateOf(
            AppCultureDataModel(
                1,
                R.drawable.flg_tr,
                "Türkçe",
                isdefault = true
            )
        )
    }
    LaunchedEffect(Unit) {
        delay(200)
        selectedOptionValue.value = firstCulture.value
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("home") }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_forward_24),
                    contentDescription = null
                )
            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay
    ) { innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
                .padding(horizontal = 20.dp)
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.logo_blue), contentDescription = null)
            Spacer(modifier = Modifier.height(40.dp))
            AppCultureDropdown(
                textfieldmodifier = AppTextFieldDefaults.TextFieldDefaultModifier(fillmaxwidth = .67f),
                expanded = expanded,
                selectedOptionValue = selectedOptionValue,
                options = listOfAppCulture.value.toMutableList()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    scope.launch {

                        if (getaccountdb.value != null) {
                            getaccountdb.value.cultureid = selectedOptionValue.value.id
                            accountViewModel.UpsertAccount(getaccountdb.value)
                        } else
                            accountViewModel.UpsertAccount(Account(cultureid = selectedOptionValue.value.id))
                        delay(200)
                        navController.navigate("appintro")
                    }
                },
                modifier = Modifier
                    .width(265.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.primaryGrey,//context.getColor(R.color.gray99)
                    contentColor = Color.White,
                    disabledContainerColor = AppColors.primaryGrey,//context.getColor(R.color.gray99)
                    disabledContentColor = Color.White
                )
            ) {
                Text(text = "Başla", style = signupSubmitButton)
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(26.dp))
            Text(
                text = "Kaydolarak Şartlarımızı kabul etmiş olursunuz. Verilerinizi nasıl kullandığımızı Gizlilik Politikamızda görün.",
                style = TextStyle(
                    fontFamily = poppinFamily,
                    fontSize = 16.sp,
                    color = AppColors.primaryGrey,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.width(320.dp)
            )
        }
    }
}