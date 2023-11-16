package com.isticpla.itp.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.ContractedSupplierItem
import com.isticpla.itp.dummydata.MyStoreItem
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.offers.offerTopBarTitle
import com.isticpla.itp.uimodules.AppColors
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContractedSuppliers(
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    val homeviewModel = hiltViewModel<HomeViewMode>()
    var btnActiveState by remember { mutableStateOf(true) }
    var btnAllState by remember { mutableStateOf(false) }
    var btnCardViewState by remember { mutableStateOf(AppColors.red_100) }
    var btnListViewState by remember { mutableStateOf(AppColors.grey_138) }


    var supplierFiltered by remember { mutableStateOf(true) }
    val listofSupplier = homeviewModel.contractedSuppliers(supplierFiltered)
        .collectAsStateWithLifecycle(initialValue = emptyList<ContractedSupplierItem>())


    var viewMode by remember { mutableStateOf(ListDisplayMode.CARDVIEW) }
    val shopCardShape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp, bottomEnd = 10.dp)


    var rootColumnPadding = Modifier.padding(horizontal = 10.dp)
    var subColumnPadding = Modifier.padding(horizontal = 10.dp)
    when (viewMode) {
        ListDisplayMode.CARDVIEW -> {
            rootColumnPadding = Modifier.padding(horizontal = 10.dp)
            subColumnPadding = Modifier.padding(horizontal = 0.dp)
        }

        ListDisplayMode.LISTVIEW -> {
            rootColumnPadding = Modifier.padding(horizontal = 0.dp)
            subColumnPadding = Modifier.padding(horizontal = 10.dp)
        }
    }
    var bordercolor by remember { mutableStateOf(AppColors.grey_189) }

    var txtnewSupplerValue by remember { mutableStateOf("") }
    Scaffold(
        containerColor = Color.White,
        topBar = {
            ProfileTopBar(
                navController,
                "Anlaşmalı Tedarikçiler",
                "profile/dashboard",
                "home"
            )
        }
    ) { innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .padding(top = 20.dp),
            //.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.then(rootColumnPadding)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(subColumnPadding),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                btnActiveState = true
                                btnAllState = false
                                scope.launch {
                                    supplierFiltered = true
                                }
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = if (btnActiveState) {
                                ButtonDefaults.buttonColors(
                                    containerColor = AppColors.red_100,
                                    contentColor = Color.White
                                )
                            } else {
                                ButtonDefaults.buttonColors(
                                    containerColor = AppColors.grey_127,
                                    contentColor = AppColors.red_100
                                )
                            }
                        ) {
                            Text(text = "Aktif")
                        }
                        Button(
                            onClick = {
                                btnActiveState = false
                                btnAllState = true
                                scope.launch {
                                    supplierFiltered = false
                                }
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = if (btnAllState) {
                                ButtonDefaults.buttonColors(
                                    containerColor = AppColors.red_100,
                                    contentColor = Color.White
                                )
                            } else {
                                ButtonDefaults.buttonColors(
                                    containerColor = AppColors.grey_127,
                                    contentColor = AppColors.red_100
                                )
                            }
                        ) {
                            Text(text = "Hepsi")
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            10.dp,
                            Alignment.CenterHorizontally
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ico_cardview),
                            contentDescription = null,
                            tint = btnCardViewState,
                            modifier = Modifier
                                .clickable {
                                    btnCardViewState = AppColors.red_100
                                    btnListViewState = AppColors.grey_138
                                    viewMode = ListDisplayMode.CARDVIEW
                                }
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ico_listview),
                            contentDescription = null,
                            tint = btnListViewState,
                            modifier = Modifier
                                .clickable {
                                    btnCardViewState = AppColors.grey_138
                                    btnListViewState = AppColors.red_100
                                    viewMode = ListDisplayMode.LISTVIEW
                                }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(26.dp))
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.then(rootColumnPadding),
                    verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    listofSupplier.value.forEach { itm ->
                        when (viewMode) {
                            ListDisplayMode.CARDVIEW -> {
                                var shopCardBorderStroke = BorderStroke(1.dp, AppColors.grey_189)
                                if (!itm.isactive)
                                    shopCardBorderStroke = BorderStroke(1.dp, AppColors.red_100)
                                Card(
                                    modifier = Modifier
                                        .clip(shopCardShape)
                                        .border(border = shopCardBorderStroke),
                                    shape = shopCardShape,
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color.White
                                    ),
                                    border = shopCardBorderStroke
                                ) {
                                    Column(
                                        modifier = Modifier.padding(top = 16.dp)
                                    ) {
                                        Row {
                                            Image(
                                                painter = painterResource(id = itm.image),
                                                contentDescription = null,
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .weight(.3f)
                                                    .requiredSize(75.dp)
                                                    .clip(RoundedCornerShape(8.dp))
                                            )
                                            Column(
                                                modifier = Modifier
                                                    .weight(.7f)
                                            ) {
                                                Text(
                                                    text = "${itm.title}\n",
                                                    style = myStoreCardTitle
                                                )
                                                Text(text = buildAnnotatedString {
                                                    withStyle(style = myStoreCardText) {
                                                        append(
                                                            "${itm.content}\n"
                                                        )
                                                    }
                                                    append("${itm.address}\n")
                                                    appendInlineContent(
                                                        "approveicon",
                                                        "Approved"
                                                    )
                                                    append(" Onaylandı\n")
                                                    withStyle(style = myStoreCardText) {
                                                        append(
                                                            "${itm.email}\n"
                                                        )
                                                    }
                                                    withStyle(style = myStoreCardText) {
                                                        append(
                                                            "${itm.webaddress}\n"
                                                        )
                                                    }

                                                }, inlineContent = mapOf(
                                                    Pair("approveicon", InlineTextContent(
                                                        Placeholder(
                                                            width = 24.sp,
                                                            height = 24.sp,
                                                            placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
                                                        )
                                                    ) {
                                                        Icon(
                                                            painter = painterResource(id = R.drawable.round_check_circle_24),
                                                            contentDescription = null,
                                                            tint = AppColors.grey_138
                                                        )
                                                    }
                                                    )
                                                ), style = myStoreCardTextStyle)
                                            }
                                        }
                                        Row() {
                                            TextButton(
                                                onClick = { /*TODO*/ },
                                                modifier = Modifier.weight(.33f)
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.round_access_time_24),
                                                    contentDescription = null,
                                                    tint = AppColors.grey_138
                                                )
                                                Text(text = "Teklifler", style = myStoreButtonLabel)
                                            }

                                            TextButton(
                                                onClick = { /*TODO*/ },
                                                modifier = Modifier.weight(.33f)
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.outline_delete_24),
                                                    contentDescription = null,
                                                    tint = AppColors.grey_138
                                                )
                                                Text(text = "Sil", style = myStoreButtonLabel)
                                            }
                                        }
                                        if (!itm.isactive) {
                                            Row(
                                                modifier = Modifier
                                                    .background(AppColors.red_100.copy(.2f))
                                                    .fillMaxWidth()
                                                    .requiredHeight(66.dp)
                                            ) {
                                                Row(
                                                    modifier = Modifier
                                                        .padding(
                                                            vertical = 16.dp,
                                                            horizontal = 10.dp
                                                        ),
                                                    horizontalArrangement = Arrangement.spacedBy(
                                                        10.dp,
                                                        Alignment.Start
                                                    ),
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Icon(
                                                        painter = painterResource(id = R.drawable.round_warning_amber_24),
                                                        contentDescription = null,
                                                        tint = AppColors.red_100
                                                    )
                                                    Text(text = buildAnnotatedString {
                                                        withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                                            append(
                                                                "Kullanılamıyor\n"
                                                            )
                                                        }
                                                        append("İlgili şirket ile anlaşmalar sonlandırılmıştır.")
                                                    }, style = myStoreInactive)
                                                }

                                            }
                                        }
                                    }
                                }
                            }

                            ListDisplayMode.LISTVIEW -> {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .drawBehind {
                                            drawLine(
                                                color = if (!itm.isactive)
                                                    AppColors.red_100
                                                else
                                                    AppColors.grey_189,
                                                start = Offset(0f, 0f),
                                                end = Offset(size.width, 0f),
                                                strokeWidth = 1f
                                            )
                                            drawLine(
                                                color = if (!itm.isactive)
                                                    AppColors.red_100
                                                else
                                                    AppColors.grey_189,
                                                start = Offset(0f, size.height),
                                                end = Offset(size.width, size.height),
                                                strokeWidth = 1f
                                            )
                                        },
                                ) {
                                    Column(
                                        modifier = Modifier.padding(
                                            horizontal = 10.dp,
                                            vertical = 10.dp
                                        )
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .requiredWidth(55.dp)
                                            ) {
                                                Image(
                                                    painter = painterResource(id = itm.image),
                                                    contentDescription = null,
                                                    contentScale = ContentScale.Crop,
                                                    modifier = Modifier
                                                        .requiredSize(50.dp)
                                                        .clip(RoundedCornerShape(8.dp))
                                                )
                                            }
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth(.75f)
                                                    .fillMaxHeight()
                                                    .padding(horizontal = 10.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Text(text = itm.title, style = myStoreCardTitle)
                                            }

                                            Column(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .requiredWidth(70.dp),
                                                horizontalAlignment = Alignment.End
                                            ) {
                                                Text(text = "Teklifler",
                                                    style = myStoreButtonListViewLabel,
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .clickable {

                                                        }
                                                )
                                                HorizontalDivider(
                                                    thickness = 1.dp,
                                                    color = AppColors.grey_189
                                                )
                                                Text(
                                                    text = "Sil",
                                                    style = myStoreButtonListViewLabel,
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .clickable {

                                                        }
                                                )
                                            }
                                        }
                                    }
                                    if (!itm.isactive) {
                                        Row(
                                            modifier = Modifier
                                                .background(AppColors.red_100.copy(.2f))
                                                .fillMaxWidth()
                                                .requiredHeight(66.dp)
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .padding(vertical = 16.dp, horizontal = 10.dp),
                                                horizontalArrangement = Arrangement.spacedBy(
                                                    10.dp,
                                                    Alignment.Start
                                                ),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.round_warning_amber_24),
                                                    contentDescription = null,
                                                    tint = AppColors.red_100
                                                )
                                                Text(text = buildAnnotatedString {
                                                    withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                                        append(
                                                            "Kullanılamıyor\n"
                                                        )
                                                    }
                                                    append("İlgili şirket ile anlaşmalar sonlandırılmıştır.")
                                                }, style = myStoreInactive)
                                            }

                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }

                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
                //region Yeni Tedarikci Ekle
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(AppColors.grey_127)
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 26.dp, horizontal = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp,Alignment.CenterVertically),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Yeni Tedarikçi Ekle", style = contractedSuppNewSupplierTitle,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(
                                10.dp,
                                Alignment.Start
                            )
                        ) {
                            TextField(
                                value = txtnewSupplerValue,
                                onValueChange = { txtnewSupplerValue = it },
                                label = {
                                    Text(
                                        text = "Tedarikçi Kodu",
                                        style = contractedSuppTextFieldLabel
                                    )
                                },
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    focusedLabelColor = AppColors.grey_118,
                                    unfocusedLabelColor = AppColors.grey_118,
                                    focusedTextColor = AppColors.grey_138,
                                    unfocusedTextColor = AppColors.grey_138,
                                    focusedPrefixColor = AppColors.grey_138,
                                    unfocusedPrefixColor = AppColors.grey_138,
                                    focusedSuffixColor = AppColors.grey_138,
                                    unfocusedSuffixColor = AppColors.grey_138,
                                ),
                                modifier = Modifier.border(
                                    1.dp, color = AppColors.blue_102,
                                    RoundedCornerShape(5.dp)
                                ),
                                shape = RoundedCornerShape(5.dp),
                                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters,autoCorrect = false )
                            )
                            Button(
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = AppColors.blue_102,
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(5.dp),
                                modifier=Modifier.requiredHeight(55.dp)
                            ) {
                                Text(text = "EKLE", style = contractedSuppButtonLabel)
                            }
                        }
                    }
                }
                //endregion
                Spacer(modifier = Modifier.height(40.dp))
            }


        }
    }
}