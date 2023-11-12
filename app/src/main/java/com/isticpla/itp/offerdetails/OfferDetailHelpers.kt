package com.isticpla.itp.offerdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.OfferChatItem
import com.isticpla.itp.dummydata.OfferDetailTabItem
import com.isticpla.itp.dummydata.OfferDetailTrackingItem
import com.isticpla.itp.dummydata.TypeOfPerson
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.DeepAnalyzerButton

@Composable
fun OfferDetailTabRow(
    offerDetailTabState: MutableState<Int>,
    offerDetailTabDbState: List<OfferDetailTabItem>
) = SecondaryTabRow(
    selectedTabIndex = offerDetailTabState.value,
    modifier = Modifier.fillMaxWidth(),
    containerColor = Color.White,
    indicator = { it ->
        if (offerDetailTabState.value < it.size) {
            TabRowDefaults.SecondaryIndicator(
                Modifier.tabIndicatorOffset(it[offerDetailTabState.value]),
                color = AppColors.primaryGrey
            )
        }
    }
) {
    offerDetailTabDbState.forEachIndexed { index, item ->
        //var labelTextStyleState by remember { mutableStateOf(offerdetailTabLabel) }
        Tab(
            selected = offerDetailTabState.value == index,
            onClick = { offerDetailTabState.value = index },
            selectedContentColor = AppColors.primaryGrey,
            unselectedContentColor = AppColors.primaryGrey,
            text = {
                if (item.badgeval == null) {
                    Text(
                        text = item.label,
                        style = if (offerDetailTabState.value == index) offerdetailTabLabelSelected else offerdetailTabLabel,
                    )
                } else {
                    BadgedBox(
                        modifier = Modifier.fillMaxSize(),
                        badge = {
                            Badge(
                                containerColor = AppColors.green_103,
                                contentColor = Color.White
                            ) {
                                val badgeNumber = item.badgeval!!.toString()
                                Text(
                                    badgeNumber,
                                    modifier = Modifier.semantics {
                                        contentDescription =
                                            "$badgeNumber new notifications"
                                    }
                                )
                            }
                        }) {
                        Text(
                            text = item.label,
                            style = if (offerDetailTabState.value == index) offerdetailTabLabelSelected else offerdetailTabLabel,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        )
    }
}

@Composable
internal fun OFHome() {
    val homeviewModel = hiltViewModel<HomeViewMode>()
    val trackListState by homeviewModel.offerDetailsTracklist.collectAsStateWithLifecycle(
        initialValue = emptyList<OfferDetailTrackingItem>()
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
            .padding(horizontal = 20.dp),
        //.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
        ) {
            Card(
                modifier = Modifier
                    .weight(.4f)
                    .clickable { },
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = AppColors.green_103.copy(.05f),
                    contentColor = AppColors.green_103
                ),
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = offerdetailTrackButtonLabel) { append("Teslimat\n") }
                        withStyle(style = offerdetailTrackButtonDate) { append("15.05.2021") }
                    }, style = offerdetailTrackButton,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp, vertical = 8.dp)
                )
            }
            Card(
                modifier = Modifier
                    .weight(.4f)
                    .clickable { },
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = AppColors.green_103.copy(.05f),
                    contentColor = AppColors.green_103
                ),
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = offerdetailTrackButtonLabel) { append("Takip Kodu\n") }
                        withStyle(style = offerdetailTrackButtonDate) { append("IK287368838") }
                    }, style = offerdetailTrackButton,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp, vertical = 8.dp)
                )
            }
        }
        Spacer(modifier = Modifier.requiredHeight(20.dp))
        trackListState.forEachIndexed { index, itm ->
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                Column(
                    modifier = Modifier
                        .requiredWidth(24.dp)
                        .padding(bottom = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ico_odt_ok_),
                        contentDescription = null
                    )
                    if (index < trackListState.size - 1) {
                        repeat(3) {
                            Icon(
                                painter = painterResource(id = R.drawable.ico_odt_sprt),
                                contentDescription = null
                            )
                        }
                    }
                }
                Text(text = buildAnnotatedString {
                    withStyle(style = offerdetailTrackLabel) { append("${itm.title}\n") }
                    withStyle(style = offerdetailTrackDate) { append("${itm.date}") }
                }, style = offerdetailTrackText)
            }
        }
    }
}

@Composable
internal fun OFDetail() = Column() {

}

@Composable
internal fun OFOffers() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(5.dp),
            colors = CardDefaults.cardColors(containerColor = AppColors.grey_127.copy(.5f))
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = offerdetailOffersTitle) { append("Teklif A\n") }
                        withStyle(style = offerdetailOffersSubText) {
                            append(
                                "Min Adet:1.000\n" +
                                        "Üretim süresi: 21 gün\n" +
                                        "Kalite: A sınıfı\n" +
                                        "Kargo: Alıcı ödemeli\n" +
                                        "Numune: Alıcı ödemeli / 4 gün"
                            )
                        }
                    }, style = offerdetailOffersTextStyle,
                    modifier = Modifier.weight(.7f)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = offerdetailOffersTitle) { append("$11.90\n") }
                        withStyle(style = offerdetailOffersSubText) { append("adet") }
                    }, style = offerdetailOffersPriceTextStyle,
                    modifier = Modifier.weight(.15f)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = offerdetailOffersTitle) { append("$11,900\n") }
                        withStyle(style = offerdetailOffersSubText) { append("toplam") }
                    }, style = offerdetailOffersPriceTextStyle,
                    modifier = Modifier.weight(.15f)
                )
            }
        }
    }
}

@Composable
internal fun OFAnalyz() = Column(
    modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp)
) {
    DeepAnalyzerButton()
}

@Composable
internal fun OFMessages() {
    val homeviewModel = hiltViewModel<HomeViewMode>()
    val chatState by homeviewModel.offerDetailsChat.collectAsStateWithLifecycle(initialValue = emptyList<OfferChatItem>())
    //chat messages
    Column(
        modifier = Modifier
            .heightIn(60.dp,2000.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        chatState.forEachIndexed { index, itm ->
            when (itm.typeofperson) {
                TypeOfPerson.ADMIN -> {
                    Column() {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.spacedBy(
                                3.dp,
                                Alignment.CenterHorizontally
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .background(Color.Transparent)
                                    .requiredSize(40.dp, 40.dp)
                                    .clip(
                                        RoundedCornerShape(
                                            topStart = 3.dp,
                                            topEnd = 3.dp,
                                            bottomStart = 3.dp,
                                            bottomEnd = 0.dp
                                        )
                                    )
                                    .paint(
                                        painterResource(id = R.mipmap.profilephoto),
                                        contentScale = ContentScale.Crop
                                    )
                            ) {

                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(.8f)
                                    .clip(
                                        RoundedCornerShape(
                                            topStart = 10.dp,
                                            topEnd = 10.dp,
                                            bottomEnd = 10.dp
                                        )
                                    ),
                                verticalArrangement = Arrangement.Bottom
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(AppColors.yellow_103.copy(.1f))
                                        .heightIn(30.dp, 400.dp)
                                        .padding(horizontal = 10.dp, vertical = 8.dp),
                                    verticalArrangement = Arrangement.Bottom
                                ) {
                                    Text(text = buildAnnotatedString {
                                        withStyle(style = offerdetailChatPersonName) {
                                            append(
                                                "${itm.personalname}\n"
                                            )
                                        }
                                        withStyle(style = offerdetailChatMessage) {
                                            append(
                                                itm.message
                                            )
                                        }
                                    }, style = offerdetailChatTextStyle)
                                }
                            }
                            Column(
                                modifier = Modifier
                                    .background(Color.Transparent)
                                    .requiredSize(40.dp, 40.dp)
                            ) {

                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.spacedBy(
                                3.dp,
                                Alignment.CenterHorizontally
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .background(Color.Transparent)
                                    .requiredWidth(40.dp)
                            ) {

                            }
                            Column(
                                modifier = Modifier
                                    .background(Color.Transparent)
                                    .fillMaxWidth(.8f)
                            ) {
                                Text(
                                    text = buildAnnotatedString {
                                        withStyle(style = offerdetailChatMessage) { append(itm.date) }
                                    },
                                    style = offerdetailChatTextStyle
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .background(Color.Transparent)
                                    .requiredWidth(40.dp)
                            ) {

                            }
                        }
                    }
                }

                TypeOfPerson.CLIENT -> {
                    Column() {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.spacedBy(
                                3.dp,
                                Alignment.CenterHorizontally
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .background(Color.Transparent)
                                    .requiredSize(40.dp, 40.dp)
                            ) {

                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(.8f)
                                    .clip(
                                        RoundedCornerShape(
                                            topStart = 10.dp,
                                            topEnd = 10.dp,
                                            bottomStart = 10.dp
                                        )
                                    ),
                                verticalArrangement = Arrangement.Bottom
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(AppColors.blue_103.copy(.1f))
                                        .heightIn(30.dp, 400.dp)
                                        .padding(horizontal = 10.dp, vertical = 8.dp),
                                    verticalArrangement = Arrangement.Bottom
                                ) {
                                    Text(text = buildAnnotatedString {
                                        if (!itm.personalname.isNullOrEmpty())
                                            withStyle(style = offerdetailChatPersonName) {
                                                append(
                                                    "${itm.personalname}\n"
                                                )
                                            }
                                        withStyle(style = offerdetailChatMessage) {
                                            append(
                                                itm.message
                                            )
                                        }
                                    }, style = offerdetailChatTextStyle)
                                }
                            }
                            Column(
                                modifier = Modifier
                                    .background(Color.Transparent)
                                    .requiredSize(40.dp, 40.dp)
                                    .clip(
                                        RoundedCornerShape(
                                            topStart = 3.dp,
                                            topEnd = 3.dp,
                                            bottomStart = 0.dp,
                                            bottomEnd = 3.dp
                                        )
                                    )
                                    .paint(
                                        painterResource(id = R.mipmap.profilephoto),
                                        contentScale = ContentScale.Crop
                                    )

                            ) {

                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.spacedBy(
                                3.dp,
                                Alignment.CenterHorizontally
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .background(Color.Transparent)
                                    .requiredWidth(40.dp)
                            ) {

                            }
                            Column(
                                modifier = Modifier
                                    .background(Color.Transparent)
                                    .fillMaxWidth(.8f)
                            ) {
                                Text(
                                    text = buildAnnotatedString {
                                        withStyle(style = offerdetailChatMessage) { append(itm.date) }
                                    },
                                    style = offerdetailChatTextStyle
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .background(Color.Transparent)
                                    .requiredWidth(40.dp)
                            ) {

                            }
                        }
                    }
                }
            }
        }

    }

}