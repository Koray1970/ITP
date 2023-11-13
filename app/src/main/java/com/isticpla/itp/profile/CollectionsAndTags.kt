package com.isticpla.itp.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.offers.offerTopBarTitle
import com.isticpla.itp.uimodules.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionsAndTags(
    navController: NavController,
) {
    Scaffold(
        containerColor = Color.White,
        topBar = { ProfileTopBar(navController,"Koleksiyonlarım & Etiketlerim","profile/dashboard","home") }
    ) { innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .padding(top = 2.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }
    }
}