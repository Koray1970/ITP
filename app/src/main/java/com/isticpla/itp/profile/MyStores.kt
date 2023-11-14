package com.isticpla.itp.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.MyStoreItem
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.offers.offerTopBarTitle
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uistyles.topmenuTitle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

enum class MyStoresDisplayMode(type: Int) {
    CARDVIEW(1),
    LISTVIEW(2)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyStores(
    navController: NavController,
) {
    val homeviewModel = hiltViewModel<HomeViewMode>()
    var btnActive by remember { mutableStateOf(false) }
    var btnAll by remember { mutableStateOf(false) }

    var storeList = remember { mutableListOf<MyStoreItem>() }
    val listofStore =
        homeviewModel.mystores.collectAsStateWithLifecycle(initialValue = emptyList<MyStoreItem>())
    var viewMode by remember { mutableStateOf(MyStoresDisplayMode.CARDVIEW) }
    val shopCardShape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp, bottomEnd = 10.dp)
    val shopCardBorderStroke = BorderStroke(1.dp, AppColors.grey_189)
    Scaffold(
        containerColor = Color.White,
        topBar = { ProfileTopBar(navController, "Mağazalarım", "profile/dashboard", "home") }
    ) { innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .padding(top = 20.dp)
                .padding(horizontal = 10.dp),
            //.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppColors.red_100,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Aktif")
                    }
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppColors.red_100,
                            contentColor = Color.White
                        )
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
                        modifier = Modifier.clickable { viewMode = MyStoresDisplayMode.CARDVIEW }
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ico_listview),
                        contentDescription = null,
                        modifier = Modifier.clickable { viewMode = MyStoresDisplayMode.LISTVIEW }
                    )
                }
            }
            Spacer(modifier = Modifier.height(26.dp))
            LazyColumn(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                itemsIndexed(listofStore.value) { _, itm ->
                    when (viewMode) {
                        MyStoresDisplayMode.CARDVIEW -> {
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
                                    modifier = Modifier.padding(16.dp)
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
                                            Text(text = itm.title, style = myStoreCardTitle)
                                            Text(text = buildAnnotatedString {
                                                withStyle(style = myStoreCardText) { append("${itm.content}\n") }
                                                append("${itm.address}\n")
                                                //withStyle(style = myStoreCardText) { append("${itm.address}\n") }
                                                //withStyle(style = myStoreCardText) { append("${itm.address}\n") }
                                                append("${itm.taglist.joinToString()}\n")
                                            }, style = myStoreCardTextStyle)
                                        }
                                    }
                                    Row() {
                                        TextButton(
                                            onClick = { /*TODO*/ },
                                            modifier = Modifier.weight(.33f)
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.ico_edit),
                                                contentDescription = null, tint = AppColors.grey_138
                                            )
                                            Text(text = "Düzenle", style = myStoreButtonLabel)
                                        }
                                        TextButton(
                                            onClick = { /*TODO*/ },
                                            modifier = Modifier.weight(.33f)
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.outline_visibility_24),
                                                contentDescription = null, tint = AppColors.grey_138
                                            )
                                            Text(text = "Düzenle", style = myStoreButtonLabel)
                                        }
                                        TextButton(
                                            onClick = { /*TODO*/ },
                                            modifier = Modifier.weight(.33f)
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.outline_delete_24),
                                                contentDescription = null, tint = AppColors.grey_138
                                            )
                                            Text(text = "Sil", style = myStoreButtonLabel)
                                        }
                                    }
                                }
                            }
                        }

                        MyStoresDisplayMode.LISTVIEW -> {

                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(40.dp))
                    Row(
                        modifier = Modifier
                            .padding(20.dp)
                            .background(AppColors.grey_127.copy(.5f))
                            .clip(RoundedCornerShape(20.dp))
                    ) {
                        Column() {
                            Icon(
                                painter = painterResource(id = R.drawable.round_add_24),
                                contentDescription = null,
                                tint = AppColors.blue_102,
                                modifier=Modifier.size(48.dp)
                            )
                        }
                        Column() {
                            Text(text = buildAnnotatedString {
                                withStyle(style = myStoreAddNewShowButtonLabel) { append("Yeni Mağaza Ekle\n") }
                                append("Diğer mağazalarınızı da ekleyerek tek bir yerden kolayca tekliflerinizi yönetebilirsiniz")
                            }, style = myStoreAddNewShowButton)
                        }
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }

        }
    }
}