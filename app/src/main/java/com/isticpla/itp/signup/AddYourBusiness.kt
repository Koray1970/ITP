package com.isticpla.itp.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.listofEmployeePosition
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.DropDownTextField
import com.isticpla.itp.uimodules.DropDowndTextFieldRequest
import com.isticpla.itp.uimodules.defaultTextFieldColor
import com.isticpla.itp.uimodules.dropdownMenuItemColors
import com.isticpla.itp.uimodules.dropdownTextFieldColors

@Composable
fun AddYourBusiness(navController: NavController) {
    val context = LocalContext.current.applicationContext
    var companynameValue by rememberSaveable { mutableStateOf("") }
    var companynameError by remember { mutableStateOf(false) }

    var positionValue = rememberSaveable { mutableStateOf("") }
    var positionExpand = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(0.dp),
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
                        .background(color = AppColors.grey_127)
                )
            }
            val headerReq = SignUpHeaderRequest()
            headerReq.title = "İşletmenizi ekleyin"
            headerReq.subtitle = "İşletmenizin bilgilerini giriniz!"
            SingUpHeader(context = context, request = headerReq)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .border(5.dp, Color.White, RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .shadow(4.dp, RoundedCornerShape(10.dp), false)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_insert_photo_24),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "İşletme Logo",
                        style = TextStyle(
                            lineHeight = 14.em,
                            fontSize = 12.sp,
                            color = AppColors.grey_118
                        )
                    )
                    Text(
                        text = "1024 x 1024, .png, .jpg",
                        style = TextStyle(
                            lineHeight = 15.em,
                            fontSize = 14.sp,
                            color = AppColors.primaryGrey
                        ),
                        modifier = Modifier.padding(vertical = 3.dp)
                    )
                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppColors.blue_104,
                            contentColor = Color.White,
                            disabledContainerColor = AppColors.blue_104,
                            disabledContentColor = Color.White
                        ),
                        modifier = Modifier.requiredHeight(48.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_edit_24),
                            contentDescription = null
                        )
                        Text(
                            text = "Logo Yükle",
                            style = TextStyle(fontSize = 16.sp),
                            modifier = Modifier.padding(horizontal = 6.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            TextField(
                value = companynameValue,
                onValueChange = {
                    companynameValue = it
                },
                label = { Text("İşletme Adı (zorunlu)") },
                colors = defaultTextFieldColor(null, true),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, AppColors.grey_130, RoundedCornerShape(5.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))
            DropDownTextField(
                request = DropDowndTextFieldRequest(
                    exposedDropdownMenuBoxModifier = Modifier.fillMaxWidth(),
                    label = "İşletmediki Pozisyonunuz",
                    selectedOptionText = positionValue,
                    expended = positionExpand,
                    listOfOptions = listofEmployeePosition,
                    textFieldModifier = Modifier
                        .fillMaxWidth()
                        .border(
                            1.dp,
                            AppColors.grey_130,
                            RoundedCornerShape(5.dp)
                        ),
                    textFieldReadOnly = true,
                    textfieldColors = dropdownTextFieldColors(null, true),
                    menuItemColors = dropdownMenuItemColors(null, true),
                )
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = { navController.navigate("choosebusinesssalesareas") },
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

        }
    }
}