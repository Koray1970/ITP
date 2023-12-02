package com.isticpla.itp.uimodules

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.ShopItem
import com.isticpla.itp.poppinFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppShopDropdown(
    listofShop: List<ShopItem> = emptyList<ShopItem>()
) {
    var showDropdownState by remember { mutableStateOf(false) }

    var shopExpanded by remember { mutableStateOf(false) }
    var shopDummySelectedOptionText = remember { mutableStateOf("") }
    if (listofShop.isNotEmpty()) {
        var (shopSelectedOptionText, onShopSelectedOption) = remember { mutableStateOf(listofShop.first()) }
        shopDummySelectedOptionText.value = shopSelectedOptionText.name
        OutlinedCard(
            modifier= Modifier.fillMaxWidth()
                .clickable {
                    showDropdownState = !showDropdownState
                },
            shape= RoundedCornerShape(5.dp),
            colors = CardDefaults.outlinedCardColors(
                containerColor = Color.Transparent,
                contentColor = AppColors.secondaryGrey
            ),
            border= BorderStroke(1.dp,AppColors.secondaryGrey)
        ) {
            if (showDropdownState) {
                Box() {
                    ExposedDropdownMenuBox(
                        modifier=Modifier.fillMaxWidth(),
                        expanded = shopExpanded,
                        onExpandedChange = { shopExpanded = it },
                    ) {
                        TextField(
                            modifier= Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            colors=TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedTextColor = AppColors.secondaryGrey,
                                unfocusedTextColor = AppColors.blue_0xFF0495f1,
                            ),
                            value = shopDummySelectedOptionText.value,
                            onValueChange = { shopDummySelectedOptionText.value = it },
                            label = { Text("Mağazalarım", style=TextStyle(fontFamily = poppinFamily, fontSize = 12.sp, fontWeight = FontWeight.Normal, color=AppColors.secondaryGrey)) },
                            trailingIcon = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    AppDropdownDefaults.ClearTextIcon(
                                        text = shopDummySelectedOptionText,
                                        isenabled = true,
                                        onClickEvent = {
                                            val dummyshop=ShopItem(0, "", "")
                                            shopSelectedOptionText = dummyshop
                                            onShopSelectedOption(dummyshop)
                                        }
                                    )
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = shopExpanded)
                                }
                            },
                            //colors = ExposedDropdownMenuDefaults.textFieldColors()
                        )
                        // filter options based on text field value
                        val filteringOptions =
                            listofShop.filter {
                                it.name.contains(
                                    shopDummySelectedOptionText.value,
                                    ignoreCase = true
                                )
                            }
                        if (filteringOptions.isNotEmpty()) {
                            ExposedDropdownMenu(
                                modifier=Modifier.fillMaxWidth().background(Color.White),
                                expanded = shopExpanded,
                                onDismissRequest = {
                                    shopExpanded = false
                                }
                            ) {
                                filteringOptions.forEach { selectionOption ->
                                    DropdownMenuItem(
                                        modifier=Modifier.fillMaxWidth(),

                                        onClick = {
                                            shopDummySelectedOptionText.value = selectionOption.name
                                            shopSelectedOptionText = selectionOption
                                            onShopSelectedOption(selectionOption)
                                            shopExpanded = false
                                            showDropdownState = false
                                        },
                                        text = {
                                            Text(
                                                text = buildAnnotatedString {
                                                    withStyle(
                                                        style = SpanStyle(
                                                            fontSize = 16.sp,
                                                            fontWeight = FontWeight.SemiBold,
                                                            color = AppColors.blue_0xFF0495f1
                                                        )
                                                    ) { append("${selectionOption.name}\n") }
                                                    append(selectionOption.address)
                                                },
                                                style = TextStyle(
                                                    fontFamily = poppinFamily,
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Normal
                                                ),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
            else{
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(
                            10.dp,
                            Alignment.CenterHorizontally
                        )
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = AppColors.blue_0xFF0495f1
                                    )
                                ) { append("${shopSelectedOptionText.name}\n") }
                                append(shopSelectedOptionText.address)
                            },
                            style = TextStyle(
                                fontFamily = poppinFamily,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal
                            ),
                            modifier = Modifier
                                .fillMaxWidth(.93f)
                                .weight(1f)
                        )
                        IconButton(onClick = {
                            showDropdownState = true
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.round_unfold_more_24),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}