package com.isticpla.itp.uimodules

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ChipColors
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.isticpla.itp.R
import com.isticpla.itp.feed.btnFeedDetailDeepAnalyzerSpotText
import com.isticpla.itp.feed.btnFeedDetailDeepAnalyzerTitle

@Composable
fun DeepAnalyzerButton()= ListItem(
    colors = ListItemColors(
        containerColor = Color.Transparent,
        headlineColor = AppColors.blue_100,
        leadingIconColor = Color.Transparent,
        overlineColor = Color.Transparent,
        supportingTextColor = Color.Transparent,
        trailingIconColor = AppColors.grey_dark,
        disabledHeadlineColor = Color.Transparent,
        disabledLeadingIconColor = Color.Transparent,
        disabledTrailingIconColor = Color.Transparent
    ),
    modifier = Modifier
        .clickable { },
    headlineContent = {
        Column() {
            SuggestionChip(
                onClick = { },
                border = SuggestionChipDefaults.suggestionChipBorder(
                    enabled = false,
                    borderWidth = 0.dp,
                    borderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent
                ),
                colors = ChipColors(
                    containerColor = AppColors.yellow_103,
                    labelColor = Color.White,
                    leadingIconContentColor = AppColors.yellow_103,
                    trailingIconContentColor = AppColors.yellow_103,
                    disabledContainerColor = AppColors.yellow_103,
                    disabledLabelColor = Color.White,
                    disabledLeadingIconContentColor = AppColors.yellow_103,
                    disabledTrailingIconContentColor = AppColors.yellow_103
                ),
                label = { Text("Premium") })
            Text(
                text = "Derin Ürün Analizi Yap",
                style = btnFeedDetailDeepAnalyzerTitle
            )
            Text(
                text = "Derin Ürün Analizini yaparak ürüne ait fiyat aralıklarını, eksilerini ve artılarını tespit edebilirsiniz..",
                style = btnFeedDetailDeepAnalyzerSpotText
            )
        }
    },
    leadingContent = {
        Image(
            painter = painterResource(id = R.mipmap.productdeepanalyzer),
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )
    },
    trailingContent = {
        Icon(
            painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
            contentDescription = null
        )
    }
)