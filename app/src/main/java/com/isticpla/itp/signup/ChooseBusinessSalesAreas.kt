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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.BusinessTypeItem
import com.isticpla.itp.dummydata.listofBusiness
import com.isticpla.itp.uimodules.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseBusinessSalesAreas(navController: NavController) {
    val context = LocalContext.current.applicationContext
    var selectedBusinessTypes = mutableListOf<BusinessTypeItem>()
    //var flowselectedBusinessTypes = Flow<ArrayList<BusinessTypeItem>>()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        containerColor = Color.White
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
                text = "Sektörler",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AppColors.grey_124
                ),
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth()
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
                listofBusiness.forEach { b ->
                    var isDisabledBussing by remember { mutableStateOf(true) }
                    Card(
                        colors = CardColors(
                            containerColor = AppColors.grey_133,
                            contentColor = AppColors.primaryGrey,
                            disabledContainerColor = AppColors.grey_133,
                            disabledContentColor = AppColors.grey_135
                        ),
                        enabled = isDisabledBussing,
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .width(70.dp)
                            .height(92.dp)
                            .alpha(if (!isDisabledBussing) .25f else 1f),
                        onClick = {
                            selectedBusinessTypes.add(b)
                            isDisabledBussing = false
                        }
                    ) {
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
                                text = b.label!!,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AppColors.primaryGrey
                                )
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Seçilen sektörler",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AppColors.grey_124
                ),
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth()
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
                selectedBusinessTypes.forEach { b ->
                    Card(
                        colors = CardColors(
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

                        }
                    ) {
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
                                text = b.label!!,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = { navController.navigate("home")},//
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