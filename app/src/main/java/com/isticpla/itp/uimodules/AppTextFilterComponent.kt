package com.isticpla.itp.uimodules

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.isticpla.itp.R
import com.isticpla.itp.poppinFamily

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AppTextFilterComponent(
    cardmodifier: Modifier = Modifier,
    listOfChip: SnapshotStateList<String> = mutableStateListOf<String>()
) {
    var aTextFieldState by remember { mutableStateOf(false) }
    var txtInitialValue = remember { mutableStateOf("") }
    //var listOfChip = remember { mutableStateListOf<String>() }
    val textMeasurer = rememberTextMeasurer()
    val textLayoutResult: TextLayoutResult =
        textMeasurer.measure(text = AnnotatedString("Kelime Giriniz"))
    val textSize = textLayoutResult.size
    Card(
        modifier = cardmodifier.then(Modifier
            .clickable {
                if (listOfChip.size <= 3)
                    aTextFieldState = !aTextFieldState
                else
                    aTextFieldState = false
            }
            .drawBehind {
                drawText(
                    textLayoutResult,
                    topLeft = Offset(
                        10f,
                        10f
                    ),
                    color=AppColors.primaryGrey.copy(.3f)
                )
            }),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        border = BorderStroke(1.dp, AppColors.secondaryGrey)
    ) {

        if (aTextFieldState) {
            Box(
                modifier = Modifier
                    .background(AppColors.primaryGreyDisabled)
                    .fillMaxWidth()
            ) {
                Row() {
                    AppTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(BorderStroke(0.dp, Color.Transparent), RectangleShape),
                        shape = RectangleShape,
                        txtvalue = txtInitialValue,
                        label = { AppDefaultStyleText("Kelime Giriniz") },
                        singleLine = true,
                        trailingIcon = {
                            Button(
                                onClick = {
                                    if (listOfChip.size <= 3)
                                        if (txtInitialValue.value.isNotEmpty())
                                            listOfChip.add(txtInitialValue.value)
                                    txtInitialValue.value = ""
                                    aTextFieldState = false
                                },
                                modifier = Modifier.height(58.dp),
                                shape = RoundedCornerShape(7.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = AppColors.primaryGrey,
                                    contentColor = Color.White
                                )
                            ) {
                                Text(
                                    text = "Ekle",
                                    style = TextStyle(
                                        fontFamily = poppinFamily,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                            }
                        }
                    )

                }
            }
        }
        FlowRow(
            maxItemsInEachRow = 3,
            horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.Start)
        ) {
            if (!listOfChip.isNullOrEmpty()) {
                listOfChip.forEach { c ->
                    var enabled by remember { mutableStateOf(true) }
                    InputChip(
                        onClick = {},
                        label = { Text(c) },
                        selected = enabled,
                        trailingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.round_clear_24),
                                contentDescription = null,
                                modifier = Modifier
                                    .clickable {
                                        listOfChip.remove(c)
                                        enabled = !enabled
                                    }
                                    .size(InputChipDefaults.AvatarSize)
                            )
                        },
                    )
                }
            }
        }
    }
}