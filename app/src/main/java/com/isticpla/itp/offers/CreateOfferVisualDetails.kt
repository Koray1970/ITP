package com.isticpla.itp.offers

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.isticpla.itp.BuildConfig
import com.isticpla.itp.R
import com.isticpla.itp.offerdetails.OfferViewModel
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.AppDefaultStyleText
import com.isticpla.itp.uimodules.AppTextField
import com.isticpla.itp.uimodules.DeepAnalyzerButton
import com.isticpla.itp.uimodules.UseCamera
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CreateOfferVisualDetails(
    navController: NavController
) {
    val radius = 6.dp
    val context = LocalContext.current.applicationContext
    val offerViewModel = hiltViewModel<OfferViewModel>()
    val scope = rememberCoroutineScope()
    val showGalleryBottomSheet = remember { mutableStateOf(false) }
    var gallerySheetState = rememberModalBottomSheetState()
    val selectedfilelist=offerViewModel.getSelectedMediaFiles.collectAsStateWithLifecycle(initialValue = emptyList<Uri>().toMutableStateList())

    val pickMedia =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                scope.launch {
                    showGalleryBottomSheet.value = false
                    gallerySheetState.hide()
                    offerViewModel.AddItemToSelectedMediaFiles(uri)
                }
            }
        }


    val imagelist =
        offerViewModel.getFileFromLocalDir.collectAsStateWithLifecycle(initialValue = emptyList<String>().toMutableStateList())

    val txtProdLinkValue = remember{ mutableStateOf("") }
    BottomSheetScaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(colors = TopAppBarColors(
                containerColor = Color.White, scrolledContainerColor = Color.White,
                navigationIconContentColor = AppColors.primaryGrey,
                titleContentColor = AppColors.primaryGrey,
                actionIconContentColor = AppColors.primaryGrey,
            ), title = { Text("Teklif Talebi Oluştur", style = offerTopBarTitle) }, actions = {
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_home_24),
                        contentDescription = null
                    )
                }
            }, navigationIcon = {
                IconButton(onClick = { navController.navigate("offer/create") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = null
                    )
                }
            })
        },
        sheetPeekHeight = 0.dp,
        sheetContent = {
            if (showGalleryBottomSheet.value) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showGalleryBottomSheet.value = false
                    },
                    sheetState = gallerySheetState
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                "Görseller",
                                style = offerGalleryPickerTitle,
                                modifier = Modifier.weight(1f)
                            )
                            Button(
                                onClick = {
                                    scope.launch {
                                        pickMedia.launch(
                                            PickVisualMediaRequest(
                                                ActivityResultContracts.PickVisualMedia.ImageAndVideo
                                            )
                                        )
                                    }
                                },
                                shape = RoundedCornerShape(radius),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = AppColors.blue_0xFF0495f1,
                                    contentColor = Color.White
                                )
                            ) {
                                Text(text = "Fotografları Aç", style = offerMediaButtonLabels)
                            }
                        }
                        if (imagelist.value.isNotEmpty()) {
                            FlowRow(
                                modifier = Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.Start,
                                verticalArrangement = Arrangement.spacedBy(
                                    10.dp,
                                    Alignment.CenterVertically
                                ),
                                maxItemsInEachRow = 4
                            ) {
                                imagelist.value.filter { a -> a.contains(".jpg") }.forEach { img ->
                                    AsyncImage(
                                        modifier = Modifier
                                            .size(90.dp)
                                            .clickable {
                                                scope.launch {
                                                    offerViewModel.AddItemToSelectedMediaFiles(
                                                        Uri.parse(
                                                            "${context.filesDir}/${img}"
                                                        )
                                                    )
                                                }
                                            },
                                        model = "${context.filesDir}/${img}",
                                        contentDescription = null,
                                        contentScale = ContentScale.Fit
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(40.dp))
                    }

                    // Sheet content
                    /*Button(onClick = {
                    scope.launch { gallerySheetState.hide() }.invokeOnCompletion {

                    }
                }) {

                }*/
                }
            }
        }) { innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .padding(horizontal = 10.dp)
                .padding(top = 30.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            ProposalWizardStage(0, "Görsel Detaylar")
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier=Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text = "Fotoğraf & Video", style = offerProductDetailFormSectionTitle,modifier=Modifier.weight(1f))
                TextButton(
                    onClick = { },
                    colors=ButtonDefaults.textButtonColors( contentColor = AppColors.red_0xffe23e3e)) {
                    Text(text="Yardım", style= offerHelpButtonLabel,modifier=Modifier.padding(end=10.dp))
                    Icon(painter= painterResource(id = R.drawable.arrow_right), contentDescription = null)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier=Modifier.padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start)
            ) {
                UseCamera(
                    radius = radius,
                    showGalleryBottomSheet,
                    mutableStateOf(gallerySheetState)
                )
                Button(
                    onClick = {
                        scope.launch {
                            //offerViewModel.ReloadMediaFiles()
                            delay(200L)
                            showGalleryBottomSheet.value = true
                            gallerySheetState.show()
                        }
                    },
                    shape = RoundedCornerShape(radius),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColors.blue_0xFF0495f1, contentColor = Color.White
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_photo_library_24),
                        contentDescription = null
                    )
                    Text(text = "Galeri", style = offerMediaButtonLabels)
                }
            }
            FlowRow(
                modifier=Modifier
                    .drawBehind {
                        val stroke = Stroke(width = 2f,
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f))
                        drawRoundRect(
                            size= Size(size.width,size.height),
                            color = AppColors.blue_0xFF0495f1,
                            style = stroke,
                            cornerRadius = CornerRadius(10f,10f))
                    }
                    .fillMaxWidth()
                    .requiredHeightIn(min=90.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp,Alignment.Top),
                horizontalArrangement = Arrangement.spacedBy(10.dp,Alignment.Start)
            ) {
                selectedfilelist.value.forEach {uri->
                    AsyncImage(
                        modifier = Modifier
                            .size(90.dp),
                        model = "$uri",
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                }
            }
            Spacer(modifier = Modifier.height(60.dp))
            Row(
                modifier=Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text = buildAnnotatedString {
                    append("Ürün Linki ")
                    withStyle(style=offerProductDetailFormSectionSubtitle){append("(Opsiyonel)")}
                } , style = offerProductDetailFormSectionTitle,modifier=Modifier.weight(1f))
                TextButton(
                    onClick = { },
                    colors=ButtonDefaults.textButtonColors( contentColor = AppColors.red_0xffe23e3e)) {
                    Text(text="Yardım", style= offerHelpButtonLabel,modifier=Modifier.padding(end=10.dp))
                    Icon(painter= painterResource(id = R.drawable.arrow_right), contentDescription = null)
                }
            }
            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                txtvalue = txtProdLinkValue,
                singleLine = true,
                label = { AppDefaultStyleText("Ürün Linki") },
            )
            Spacer(modifier = Modifier.height(60.dp))
            DeepAnalyzerButton()
            Spacer(modifier = Modifier.height(60.dp))
            Button(
                onClick = {},
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.grey_130,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(48.dp)
            ) {
                Text(text = "Devam", style = offerStagePublishButton)
                Icon(
                    painter = painterResource(id = R.drawable.round_arrow_right_alt_24),
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}




