package com.isticpla.itp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.home.homeSectorShowAll
import com.isticpla.itp.home.homeSubSectionTitle
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uistyles.sectionBadge
import com.isticpla.itp.uistyles.sectionTitle
import com.isticpla.itp.views.helpers.AppMediumTopBar
import com.isticpla.itp.views.helpers.AppMediumTopBarItem
import com.isticpla.itp.views.helpers.TopBarActionItem

@Composable
fun MyPanel(
    navController: NavController,
) {
    Scaffold(
        containerColor = AppColors.grey_133,
        topBar = AppMediumTopBar(
            AppMediumTopBarItem(
                title = { Text("Panelim", style = homeSubSectionTitle) },
                actions = listOf<TopBarActionItem>(
                    TopBarActionItem(
                        url = { navController.navigate("home") },
                        icon = R.drawable.outline_home_24
                    )
                ).toMutableList(),
                navigationIcon = TopBarActionItem(
                    url = { navController.popBackStack() },
                    icon = R.drawable.arrow_left
                )
            )
        )

    ) { innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier=Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        10.dp,
                        Alignment.Start
                    )
                ) {
                    Text(text = "Aktif Siparişlerim", style = sectionTitle)
                    Card(
                        shape = RoundedCornerShape(10),
                        colors = CardDefaults.cardColors(containerColor = AppColors.red_100)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 6.dp, vertical = 4.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "000", style = sectionBadge)
                        }
                    }
                }
                TextButton(
                    onClick = { navController.navigate("") },
                ) {
                    Text("Hepsini Göster", style = homeSectorShowAll)
                }

            }
        }
    }
}