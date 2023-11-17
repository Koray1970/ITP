package com.isticpla.itp.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.offers.*
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyStoreAddEdit(
    navController: NavController
) {
    val homeviewModel = hiltViewModel<HomeViewMode>()
    val employeePositions =
        homeviewModel.employeePositions.collectAsStateWithLifecycle(initialValue = emptyList<Pair<String, String>>())
    var empPositionexpanded = remember { mutableStateOf(false) }
    var empPositionselectedOptionText = remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        delay(120)
        empPositionselectedOptionText.value = employeePositions.value.first().second
    }
    var comNameValue = remember { mutableStateOf("") }
    var comTaxOfficeValue = rememberSaveable { mutableStateOf("") }
    var comTaxNumberValue = rememberSaveable { mutableStateOf("") }
    var comAddressValue = rememberSaveable { mutableStateOf("") }

    var comCityexpanded = remember { mutableStateOf(false) }
    var comCityselectedOptionText = remember { mutableStateOf("") }

    var comCountryexpanded = remember { mutableStateOf(false) }
    var comCountryselectedOptionText = remember { mutableStateOf("") }

    var comEmailValue = rememberSaveable { mutableStateOf("") }
    var comWebValue = rememberSaveable { mutableStateOf("") }


    Scaffold(
        containerColor = Color.White,
        topBar = {
            ProfileTopBar(
                navController,
                "Mağaza Ekle",
                "profile/mystores",
                "profile/dashboard"
            )
        }
    ) { innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .padding(top = 2.dp)
                .padding(horizontal = 10.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //region Image Upload
            Row(
                modifier = Modifier
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
            ) {
                val imgRoundRectangle = RoundedCornerShape(10.dp)
                Column(
                    //colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .size(130.dp)
                        .clip(imgRoundRectangle)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(Color.DarkGray, Color.White)
                            ),
                            shape = imgRoundRectangle
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier
                            .clip(imgRoundRectangle)
                            .size(100.dp)
                            .border(border = BorderStroke(4.dp, Color.White), imgRoundRectangle)

                    ) {
                        Image(
                            painter = painterResource(id = R.mipmap.profilephoto),
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                            modifier = Modifier.size(100.dp)
                        )
                    }
                }
                Column() {
                    Text(text = buildAnnotatedString {
                        append("İşletme Logo\n")
                        withStyle(style = mystoreeditfileloaderTextStyleTitle) { append("1024 x 1024, .png, .jpg") }
                    }, style = mystoreeditfileloaderTextStyle)
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppColors.blue_102,
                            contentColor = Color.White
                        ),
                        shape = imgRoundRectangle
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_edit_24),
                            contentDescription = null,
                            tint = Color.White
                        )
                        Text(
                            text = "Logo Yükle",
                            style = TextStyle(
                                fontFamily = poppinFamily,
                                fontSize = 12.sp,
                                color = Color.White
                            )
                        )
                    }
                }
            }
            //endregion
            //region Form
            DropDownMenu<String, String>(
                itms = DropdownMenuItems(
                    txfItems = appTextFieldItems(
                        Modifier,
                        Modifier.fillMaxWidth(),
                        empPositionselectedOptionText,
                        {
                            Icon(
                                painter = painterResource(id = R.drawable.round_unfold_more_24),
                                contentDescription = null
                            )
                        },
                        null,
                        "İşletmedeki Yetkiniz",
                        false,
                        true,
                        true,
                        true,
                        1,
                        1,
                        TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            focusedLabelColor = AppColors.blue_102,
                            unfocusedLabelColor = AppColors.blue_102,
                            focusedTextColor = AppColors.blue_102,
                            unfocusedTextColor = AppColors.blue_102,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        txtFKeyboardOptionsCapSentence
                    ),
                    cardBorderColor = AppColors.blue_102,
                    expanded = empPositionexpanded,
                    menuitems = employeePositions.value,
                )
            )
            appTextField(
                itms = appTextFieldItems(
                    Modifier,
                    Modifier
                        .fillMaxWidth(),
                    comNameValue,
                    null,
                    null,
                    "İşletme Adı",
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
            appTextField(
                itms = appTextFieldItems(
                    Modifier,
                    Modifier
                        .fillMaxWidth(),
                    comTaxNumberValue,
                    null,
                    null,
                    "İşletme Vergi No",
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
            appTextField(
                itms = appTextFieldItems(
                    Modifier,
                    Modifier
                        .fillMaxWidth(),
                    comTaxOfficeValue,
                    null,
                    null,
                    "İşletme Vergi Dairesi",
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
            appTextField(
                itms = appTextFieldItems(
                    Modifier,
                    Modifier
                        .fillMaxWidth(),
                    comAddressValue,
                    null,
                    null,
                    "İşletme Tam Adresi",
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start)
            ) {
                DropDownMenu<String, String>(
                    itms = DropdownMenuItems(
                        txfItems = appTextFieldItems(
                            Modifier
                                .fillMaxWidth(.5f),
                            Modifier,
                            comCityselectedOptionText,
                            {
                                Icon(
                                    painter = painterResource(id = R.drawable.round_unfold_more_24),
                                    contentDescription = null
                                )
                            },
                            null,
                            "İşletme Şehri",
                            false,
                            true,
                            true,
                            true,
                            1,
                            1,
                            txtFColors(),
                            txtFKeyboardOptionsCapSentence
                        ),
                        expanded = comCityexpanded,
                        menuitems = employeePositions.value,
                    )
                )
                DropDownMenu<String, String>(
                    itms = DropdownMenuItems(
                        txfItems = appTextFieldItems(
                            Modifier,
                            Modifier,
                            comCountryselectedOptionText,
                            {
                                Icon(
                                    painter = painterResource(id = R.drawable.round_unfold_more_24),
                                    contentDescription = null
                                )
                            },
                            null,
                            "İşletme Ülke",
                            false,
                            true,
                            true,
                            true,
                            1,
                            1,
                            txtFColors(),
                            txtFKeyboardOptionsCapSentence
                        ),
                        expanded = comCountryexpanded,
                        menuitems = employeePositions.value,
                    )
                )
            }
            appTextField(
                itms = appTextFieldItems(
                    Modifier,
                    Modifier
                        .fillMaxWidth(),
                    comEmailValue,
                    null,
                    null,
                    "İşletme E-posta Adresi",
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
            appTextField(
                itms = appTextFieldItems(
                    Modifier,
                    Modifier
                        .fillMaxWidth(),
                    comWebValue,
                    null,
                    null,
                    "İşletme Web Adresi",
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
            Spacer(modifier = Modifier.height(26.dp))
            Button(
                onClick = { navController.navigate("offer/create/publish") },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.blue_102,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(48.dp)
            ) {
                Text(text = "Mağazamı Ekle", style = offerStagePublishButton)
                Icon(
                    painter = painterResource(id = R.drawable.round_arrow_right_alt_24),
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.height(26.dp))
            Text(
                text = "İptal",
                style = TextStyle(
                    fontFamily = poppinFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AppColors.grey_165
                )
            )
            Spacer(modifier = Modifier.height(30.dp))
            //endregion
        }
    }
}