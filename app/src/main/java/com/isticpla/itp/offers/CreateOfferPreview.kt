package com.isticpla.itp.offers

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.uimodules.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateOfferPreview(
    navController: NavController,
) {
    var procuctDetailExpendedState by remember { mutableStateOf(true) }
    var procuctDetailExpendedIcon by remember { mutableStateOf(R.drawable.baseline_expand_more_24) }

    var orderDetailExpendedState by remember { mutableStateOf(true) }
    var orderDetailExpendedIcon by remember { mutableStateOf(R.drawable.baseline_expand_more_24) }

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
        ) {
            ProposalWizardStage(3, "Önizleme")
            Spacer(modifier = Modifier.height(40.dp))
            //start:Product details accordion
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        val borderSize = 2.dp.toPx()
                        drawLine(
                            color = AppColors.grey_133,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = borderSize
                        )
                    }
                    .padding(bottom = 10.dp)
            ) {
                Text(text = "Ürün Detayları", style = previewAccordionHeader)
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    procuctDetailExpendedState = !procuctDetailExpendedState
                    procuctDetailExpendedIcon = if (procuctDetailExpendedState)
                        R.drawable.baseline_expand_more_24
                    else
                        R.drawable.round_expand_less_24

                }) {
                    Icon(
                        painter = painterResource(id = procuctDetailExpendedIcon),
                        contentDescription = null,
                    )
                }
            }
            AnimatedVisibility(visible = procuctDetailExpendedState) {
                Column() {
                    Spacer(modifier = Modifier.requiredHeight(30.dp))
                    Text(text = buildAnnotatedString {
                        withStyle(style = previewItemTitle) { append("Başlık\n") }
                        withStyle(style = previewItemContent) { append("Tillman`s Hamburger vom Rind, 4 Stück, 250g") }
                    })
                    Spacer(modifier = Modifier.requiredHeight(30.dp))
                    Text(text = buildAnnotatedString {
                        withStyle(style = previewItemTitle) { append("Açıklama\n") }
                        withStyle(style = previewItemContent) {
                            append(
                                "İlave renklendirici yok, ilave tat yok, koruyucu yok. \n" +
                                        "4 Stück Hamburger für Grill und Pfanne\n" +
                                        "Aus leckerem Rinfleisch, 100% Geschmack, 100% Qualität\n" +
                                        "Schnell, lecker, trendy\n" +
                                        "Höchste Qualität\n" +
                                        "100% Geschmack\n" +
                                        "Absolute Frische"
                            )
                        }
                    })
                    Spacer(modifier = Modifier.requiredHeight(30.dp))
                    Text(text = buildAnnotatedString {
                        withStyle(style = previewItemTitle) { append("Örnek Ürün Linkleri") }
                    })
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(
                            8.dp,
                            Alignment.CenterVertically
                        ),
                        horizontalAlignment = Alignment.Start
                    ) {
                        repeat(3) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(6.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = AppColors.grey_127,
                                )
                            ) {
                                Column(
                                    modifier = Modifier.padding(18.dp)
                                ) {
                                    Text(text = buildAnnotatedString {
                                        withStyle(style = previewCardItemTitle) { append("Indonesian chicken burger\n") }
                                        withStyle(style = previewCardItemContent) { append("www.amazone.de") }
                                    }, style = TextStyle(lineHeight = 11.sp))
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.requiredHeight(30.dp))

                    Text(text = buildAnnotatedString {
                        withStyle(style = previewItemTitle) { append("Ham Madde\n") }
                        withStyle(style = previewItemContent) { append("Metal, Cam, Ahşap") }
                    })
                    Spacer(modifier = Modifier.requiredHeight(30.dp))

                    Text(text = buildAnnotatedString {
                        withStyle(style = previewItemTitle) { append("Renk\n") }
                        withStyle(style = previewItemContent) { append("Kahverengi, Siyah") }
                    })
                    Spacer(modifier = Modifier.requiredHeight(30.dp))

                    Text(text = buildAnnotatedString {
                        withStyle(style = previewItemTitle) { append("Sertifika Talepleriniz\n") }
                        withStyle(style = previewItemContent) { append("ISO 9001, 10001") }
                    })
                    Spacer(modifier = Modifier.requiredHeight(60.dp))
                }
            }
            //end:Product details accordion


            //start:Order details accordion
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        val borderSize = 2.dp.toPx()
                        drawLine(
                            color = AppColors.grey_133,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = borderSize
                        )
                    }
                    .padding(bottom = 10.dp)
            ) {
                Text(text = "Sipariş Bilgileri", style = previewAccordionHeader)
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    orderDetailExpendedState = !orderDetailExpendedState
                    orderDetailExpendedIcon = if (orderDetailExpendedState)
                        R.drawable.baseline_expand_more_24
                    else
                        R.drawable.round_expand_less_24

                }) {
                    Icon(
                        painter = painterResource(id = orderDetailExpendedIcon),
                        contentDescription = null,
                    )
                }
            }
            AnimatedVisibility(visible = orderDetailExpendedState) {
                Column() {
                    Spacer(modifier = Modifier.requiredHeight(30.dp))
                    Text(text = buildAnnotatedString {
                        withStyle(style = previewItemTitle) { append("Adet\n") }
                        withStyle(style = previewItemContent) { append("1000 adet") }
                    })
                    Spacer(modifier = Modifier.requiredHeight(30.dp))
                    Text(text = buildAnnotatedString {
                        withStyle(style = previewItemTitle) { append("Tarih\n") }
                        withStyle(style = previewItemContent) { append("24/10/2023 son tarih") }
                    })
                    Spacer(modifier = Modifier.requiredHeight(30.dp))
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(text = buildAnnotatedString {
                            withStyle(style = previewItemTitle) {
                                append(
                                    "Alıcı Firma"
                                )
                            }
                        })
                        Spacer(modifier = Modifier.requiredHeight(2.dp))
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(6.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = AppColors.grey_127,
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(18.dp)
                            ) {
                                Text(text = buildAnnotatedString {
                                    withStyle(style = previewCardItemTitle) { append("Tilman’s Market\n") }
                                    withStyle(style = previewCardItemContent) { append("Neue Strasse 52, Bremen, Deutschland") }
                                }, style = TextStyle(lineHeight = 11.sp))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.requiredHeight(30.dp))

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(text = buildAnnotatedString {
                            withStyle(style = previewItemTitle) {
                                append(
                                    "Teslim Şekli"
                                )
                            }
                        })
                        Spacer(modifier = Modifier.requiredHeight(2.dp))
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(6.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = AppColors.grey_127,
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(18.dp)
                            ) {
                                Text(text = buildAnnotatedString {
                                    withStyle(style = previewCardItemTitle) { append("Kapıdan Kapıya\n") }
                                    withStyle(style = previewCardItemContent) { append("Kargo Dahil") }
                                }, style = TextStyle(lineHeight = 11.sp))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.requiredHeight(30.dp))

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(text = buildAnnotatedString {
                            withStyle(style = previewItemTitle) {
                                append(
                                    "Teslim Yeri"
                                )
                            }
                        }, style = TextStyle(lineHeight = 11.sp))
                        Spacer(modifier = Modifier.requiredHeight(2.dp))
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(6.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = AppColors.grey_127,
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(18.dp)
                            ) {
                                Text(text = buildAnnotatedString {
                                    withStyle(style = previewCardItemSubTitle) { append("Kapıda Teslim\n") }
                                    withStyle(style = previewCardItemTitle) { append("Tilman’s Market\n") }
                                    withStyle(style = previewCardItemContent) { append("Neue Strasse 52, Bremen, Deutschland") }
                                }, style = TextStyle(lineHeight = 11.sp))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.requiredHeight(30.dp))

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(text = buildAnnotatedString {
                            withStyle(style = previewItemTitle) {
                                append(
                                    "Ödeme Şekli"
                                )
                            }
                        })
                        Spacer(modifier = Modifier.requiredHeight(2.dp))
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(6.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = AppColors.grey_127,
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(18.dp)
                            ) {
                                Text(text = buildAnnotatedString {
                                    withStyle(style = previewCardItemTitle) { append("Havale, Standart Ödeme\n") }
                                    withStyle(style = previewCardItemContent) { append("Sipariş sonrası peşin ödeme") }
                                }, style = TextStyle(lineHeight = 11.sp))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.requiredHeight(30.dp))

                    Text(text = buildAnnotatedString {
                        withStyle(style = previewItemTitle) { append("Numune Talebi\n") }
                        withStyle(style = previewItemContent) { append("Evet Numune İstiyorum") }
                    })
                    Spacer(modifier = Modifier.requiredHeight(30.dp))

                    Text(text = buildAnnotatedString {
                        withStyle(style = previewItemTitle) { append("Ek Açıklamalar\n") }
                        withStyle(style = previewItemContent) { append("Gelip yerinde görmek istiyorum. Ürün kalitesini görmek ve kendim test edip bilmek istiyorum.") }
                    })
                    Spacer(modifier = Modifier.requiredHeight(30.dp))

                    Text(text = buildAnnotatedString {
                        withStyle(style = previewItemTitle) { append("Teklif Alacağınız Yerler\n") }
                        withStyle(style = previewItemContent) {
                            append(
                                "Anlaşmalı Tedarikçiler\n" +
                                        "A Firması\n" +
                                        "B. Firması\n" +
                                        "C Firması"
                            )
                        }
                    })
                    Spacer(modifier = Modifier.requiredHeight(30.dp))

                    Text(text = buildAnnotatedString {
                        withStyle(style = previewItemContent) { append("ITP Teklif Verecek") }
                    })
                    Spacer(modifier = Modifier.requiredHeight(60.dp))
                }
            }
            //end:Order details accordion
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = { navController.navigate("offer/create/publish") },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.green_100,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(48.dp)
            ) {
                Text(text = "İnceledim ve Onaylıyorum", style = offerStagePublishButton)
                Icon(
                    painter = painterResource(id = R.drawable.round_arrow_right_alt_24),
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { navController.navigate("offer/dashboard") },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColors.grey_133,
                        contentColor = AppColors.primaryGrey
                    ),
                    modifier = Modifier
                        .requiredHeight(48.dp)
                ) {
                    Text(text = "Taslağa Dön", style = offerStageDefaultButton)
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}