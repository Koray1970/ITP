package com.isticpla.itp.uimodules

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.isticpla.itp.R
import com.isticpla.itp.poppinFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchbarWithChips(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Card(
            colors = CardColors(
                containerColor = Color.Transparent,
                contentColor = AppColors.grey_130,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = AppColors.grey_130
            ),
            modifier = Modifier
                .fillMaxWidth(.86f)
                .requiredHeight(63.dp)
                .border(1.dp, AppColors.grey_130, RoundedCornerShape(5.dp))
        ) {
            Text(
                text = "Kelime Giriniz",
                style = TextStyle(
                    fontFamily = poppinFamily,
                    color = AppColors.grey_118,
                    fontSize = 12.sp
                ),
                modifier = Modifier.padding(start = 10.dp, top = 10.dp)
            )
            Row() {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_search_24),
                        contentDescription = null
                    )
                }
                Column(

                ) {

                }
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Card(
            onClick = {},
            colors = CardColors(
                containerColor = AppColors.grey_133,
                contentColor = Color.Black,
                disabledContainerColor = AppColors.grey_133,
                disabledContentColor = Color.Black
            ),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .border(1.dp, AppColors.grey_130, RoundedCornerShape(5.dp))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_favorite_border_24),
                    contentDescription = null,
                )
            }
        }
    }
}