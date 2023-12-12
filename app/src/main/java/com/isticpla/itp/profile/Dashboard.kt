package com.isticpla.itp.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.isticpla.itp.MainActivity
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.ProfileMenuItem
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.offers.offerTopBarTitle
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.AppGallery

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDashboard(
    navController: NavController,
) {
    val homeviewModel = hiltViewModel<HomeViewMode>()
    val lst = listOf<Pair<String, String>>(
        Pair("1124", "Taslaklar"), Pair("492", "Teklifler"), Pair("329", "Siparişler")
    )
    val menuItems by homeviewModel.profileMenu.collectAsStateWithLifecycle(initialValue = emptyList<ProfileMenuItem>())

    var appGalleryShowState = remember { mutableStateOf(false) }

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
                title = { Text("Profilim", style = offerTopBarTitle) },
                /*actions = {
                    TextButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.round_mode_edit_24),
                            contentDescription = null
                        )
                        Text("Düzenle", style = TextStyle())
                    }
                    TextButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.round_content_copy_24),
                            contentDescription = null
                        )
                        Text("Kopyala", style = TextStyle())
                    }
                },*/
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_left),
                            contentDescription = null
                        )
                    }
                }
            )
        })
    { innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .padding(top = 2.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ProfilePhotoViewer(appGalleryShowState)
            Text(text = "Julia Bright, 24", style = profileName)
            Row(
                Modifier.padding(top = 3.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_location_on_24),
                    contentDescription = null,
                    tint = AppColors.grey_177,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(text = "Berlin,DB", style = profileTitleLocation)
            }
            Row(
                Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                lst.forEachIndexed { index, itm ->
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = profileInfoThreeColHeader) { append("${itm.first}\n") }
                            withStyle(style = profileInfoThreeColValue) { append("${itm.second}") }
                        }, style = profileInfoThreeCol,
                        modifier = Modifier
                            .weight(.3f)
                            .drawBehind {
                                if (index < lst.size - 1) {
                                    val width = size.width
                                    val height = size.height - 1 / 2

                                    drawLine(
                                        color = Color.DarkGray,
                                        start = Offset(x = width, y = 0f),
                                        end = Offset(x = width, y = height),
                                        strokeWidth = (1).toFloat()
                                    )
                                }
                            }
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(.90f)
                    .padding(top = 30.dp)
            ) {

                menuItems.forEachIndexed { _, itm ->
                    ListItem(
                        modifier = Modifier
                            .clickable {
                                navController.navigate(itm.navuri)
                            },
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        headlineContent = { Text(text = itm.label, style = profileMenuItemLabel) },
                        leadingContent = {
                            Icon(
                                painter = painterResource(id = itm.icon),
                                contentDescription = null,
                                tint = AppColors.grey_186
                            )
                        }
                    )
                    HorizontalDivider(thickness = 1.dp, color = AppColors.grey_183)
                }
                ListItem(
                    modifier = Modifier
                        .clickable {
                            val activity: MainActivity = MainActivity()
                            activity.finish()
                            java.lang.System.exit(0)
                        },
                    colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                    headlineContent = {
                        Text(
                            text = "Çıkış Yap",
                            style = profileMenuItemExitLabel
                        )
                    },
                    leadingContent = {
                        Icon(
                            painter = painterResource(id = R.drawable.ico_signout),
                            contentDescription = null,
                            tint = AppColors.red_100
                        )
                    }
                )
            }

        }
    }
    AppGallery(appGalleryShowState)
}

@Composable
internal fun ProfilePhotoViewer(
    showGalleryState: MutableState<Boolean> = mutableStateOf(false),
) = Row() {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(168.dp)
                .clip(CircleShape)
                .shadow(
                    elevation = 5.dp,
                    shape = CircleShape,
                    clip = true,
                    ambientColor = Color.Black,
                    spotColor = Color.White
                )
        ) {}
        Image(
            painter = painterResource(id = R.mipmap.profilephoto),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
                .border(BorderStroke(2.dp, Color.White), CircleShape)
        )
        Box(
            modifier = Modifier
                .size(152.dp)
                .clip(CircleShape)
                .border(BorderStroke(6.dp, Color.White), CircleShape)
        ) {

        }

        Box(
            Modifier.absoluteOffset(52.dp, 54.dp)
        ) {
            IconButton(
                onClick = { showGalleryState.value = true },
                modifier = Modifier
                    .background(AppColors.green_106, CircleShape)
                    .requiredSize(46.dp)
                    .border(border = BorderStroke(3.dp, Color.White), CircleShape),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ico_edit),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(23.dp)
                )
            }
        }
    }
}