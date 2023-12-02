package com.isticpla.itp.offers

import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.isticpla.itp.R
import com.isticpla.itp.offerdetails.AdditionalProductDetails
import com.isticpla.itp.offerdetails.OfferViewModel
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.AppDefaultStyleText
import com.isticpla.itp.uimodules.AppTextField
import com.isticpla.itp.uimodules.UseCamera
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CreateOfferProductParts(
    navController: NavController
) {
    val radius = 6.dp
    val context = LocalContext.current.applicationContext
    val scope = rememberCoroutineScope()
    val offerViewModel = hiltViewModel<OfferViewModel>()
    val showGalleryBottomSheet = remember { mutableStateOf(false) }
    var gallerySheetState = rememberModalBottomSheetState()
    val selectedfilelist =
        offerViewModel.getSelectedMediaFiles.collectAsStateWithLifecycle(initialValue = emptyList<Uri>().toMutableStateList())

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

    val PartImagesSame = remember { mutableStateOf(false) }

    val txtPartNameValue = remember { mutableStateOf("") }
    val txtPartNameError = remember { mutableStateOf(false) }

    val txtPartCommentValue = remember { mutableStateOf("") }
    val txtPartRawMaterialValue = remember { mutableStateOf("") }
    val txtPartRawMaterialSame = remember { mutableStateOf(false) }
    val txtPartColorValue = remember { mutableStateOf("") }
    val txtPartColorSame = remember { mutableStateOf(false) }
    val txtPartWidthValue = remember { mutableStateOf("") }
    val txtPartHeigthValue = remember { mutableStateOf("") }
    val txtPartDepthValue = remember { mutableStateOf("") }
    val txtPartDiameterValue = remember { mutableStateOf("") }


    BottomSheetScaffold(
        containerColor = Color.White,
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
        },
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = Color.White, scrolledContainerColor = Color.White,
                    navigationIconContentColor = AppColors.primaryGrey,
                    titleContentColor = AppColors.primaryGrey,
                    actionIconContentColor = AppColors.primaryGrey,
                ),
                title = { Text("Ürün Parçası", style = offerTopBarTitle) },
                actions = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_home_24),
                            contentDescription = null
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("offer/create/productdetails") }) {
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
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Fotoğraf & Video",
                    style = offerProductDetailFormSectionTitle,
                    modifier = Modifier.weight(1f)
                )
                TextButton(
                    onClick = { },
                    colors = ButtonDefaults.textButtonColors(contentColor = AppColors.red_0xffe23e3e)
                ) {
                    Text(
                        text = "Yardım",
                        style = offerHelpButtonLabel,
                        modifier = Modifier.padding(end = 10.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_right),
                        contentDescription = null
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start
            ) {
                //region Media Area
                Row(
                    modifier = Modifier.padding(bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.Start)
                ) {
                    UseCamera(
                        radius = radius,
                        showGalleryBottomSheet,
                        mutableStateOf(gallerySheetState)
                    )
                    Card(
                        modifier = Modifier
                            .size(110.dp, 48.dp)
                            .clickable {
                                scope.launch {
                                    //offerViewModel.ReloadMediaFiles()
                                    delay(200L)
                                    showGalleryBottomSheet.value = true
                                    gallerySheetState.show()
                                }
                            },
                        shape = RoundedCornerShape(radius),
                        colors = CardDefaults.cardColors(
                            containerColor = AppColors.blue_0xFF0495f1,
                            contentColor = Color.White
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(
                                10.dp,
                                Alignment.CenterHorizontally
                            )
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.outline_photo_library_24),
                                contentDescription = null
                            )
                            Text(text = "Galeri", style = offerMediaButtonLabels)
                        }
                    }


                    Card(
                        modifier = Modifier
                            .size(136.dp, 48.dp)
                            .clickable {
                                PartImagesSame.value=!PartImagesSame.value
                            },
                        shape = RoundedCornerShape(radius),
                        colors = if (PartImagesSame.value) {
                            CardDefaults.cardColors(
                                containerColor = AppColors.blue_0xFF0495f1,
                                contentColor = Color.White
                            )
                        } else {
                            CardDefaults.cardColors(
                                containerColor = AppColors.gray_FFEEF1F4,
                                contentColor = AppColors.primaryGrey
                            )
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(
                                10.dp,
                                Alignment.CenterHorizontally
                            )
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.round_check_24),
                                contentDescription = null
                            )
                            Text(text = buildAnnotatedString {
                                append("Ürün\n")
                                append("Fotoğrafları")
                            }, style = offerMediaProductPartsButtonLabels)
                        }
                    }


                }
                FlowRow(
                    modifier = Modifier
                        .drawBehind {
                            val stroke = Stroke(
                                width = 2f,
                                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                            )
                            drawRoundRect(
                                size = Size(size.width, size.height),
                                color = AppColors.blue_0xFF0495f1,
                                style = stroke,
                                cornerRadius = CornerRadius(10f, 10f)
                            )
                        }
                        .fillMaxWidth()
                        .requiredHeightIn(min = 90.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start)
                ) {
                    selectedfilelist.value.forEach { uri ->
                        AsyncImage(
                            modifier = Modifier
                                .size(90.dp),
                            model = "$uri",
                            contentDescription = null,
                            contentScale = ContentScale.Fit
                        )
                    }
                }
                //endregion
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Parça Bilgileri", style = offerProductDetailFormSectionTitle)
                AppTextField(
                    modifier = Modifier.fillMaxWidth(),
                    txtvalue = txtPartNameValue,
                    isError = txtPartNameError,
                    singleLine = true,
                    label = { AppDefaultStyleText("Ürün Parça Adı") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        autoCorrect = false
                    )
                )
                AppTextField(
                    modifier = Modifier.fillMaxWidth(),
                    txtvalue = txtPartCommentValue,
                    singleLine = true,
                    label = { AppDefaultStyleText("Ürün parça açıklama") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        autoCorrect = false
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        10.dp,
                        Alignment.CenterHorizontally
                    )
                ) {
                    AppTextField(
                        modifier = Modifier
                            .fillMaxWidth(.46f)
                            .weight(1f),
                        txtvalue = if (!txtPartRawMaterialSame.value) {
                            txtPartRawMaterialValue
                        } else {
                            txtPartRawMaterialValue.value = ""
                            mutableStateOf("")
                        },
                        enabled = mutableStateOf(!txtPartRawMaterialSame.value),
                        singleLine = true,
                        label = { AppDefaultStyleText("Ürün ham madde") }
                    )
                    SameAsProductButton(
                        isselected = txtPartRawMaterialSame
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        10.dp,
                        Alignment.CenterHorizontally
                    )
                ) {
                    AppTextField(
                        modifier = Modifier
                            .fillMaxWidth(.46f)
                            .weight(1f),
                        txtvalue = if (!txtPartColorSame.value) {
                            txtPartColorValue
                        } else {
                            txtPartColorValue.value = ""
                            mutableStateOf("")
                        },
                        enabled = mutableStateOf(!txtPartColorSame.value),
                        singleLine = true,
                        label = { AppDefaultStyleText("Ürün renk") }
                    )
                    SameAsProductButton(
                        isselected = txtPartColorSame
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        10.dp,
                        Alignment.CenterHorizontally
                    )
                ) {
                    AppTextField(
                        modifier = Modifier
                            .fillMaxWidth(.5f)
                            .weight(1f),
                        txtvalue = txtPartWidthValue,
                        singleLine = true,
                        label = { AppDefaultStyleText("Genişlik") }
                    )
                    AppTextField(
                        modifier = Modifier
                            .fillMaxWidth(.5f)
                            .weight(1f),
                        txtvalue = txtPartHeigthValue,
                        singleLine = true,
                        label = { AppDefaultStyleText("Uzunluk") }
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        10.dp,
                        Alignment.CenterHorizontally
                    )
                ) {
                    AppTextField(
                        modifier = Modifier
                            .fillMaxWidth(.5f)
                            .weight(1f),
                        txtvalue = txtPartDepthValue,
                        singleLine = true,
                        label = { AppDefaultStyleText("Derinlik") }
                    )
                    AppTextField(
                        modifier = Modifier
                            .fillMaxWidth(.5f)
                            .weight(1f),
                        txtvalue = txtPartDiameterValue,
                        singleLine = true,
                        label = { AppDefaultStyleText("Çap") }
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    onClick = { navController.navigate("offer/create/productdetails") },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColors.green_100,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(48.dp)
                ) {
                    Text(text = "Ekle ve Kapat", style = offerStagePublishButton)
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
                        onClick = { },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppColors.grey_133,
                            contentColor = AppColors.primaryGrey
                        ),
                        modifier = Modifier
                            .requiredHeight(48.dp)
                    ) {
                        Text(text = "Kopyala", style = offerStageDefaultButton)
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
fun SameAsProductButton(
    isselected: MutableState<Boolean> = mutableStateOf(false)
) = Card(
    modifier = Modifier
        .clickable { isselected.value = !isselected.value },
    shape = RoundedCornerShape(4.dp),
    colors = if (!isselected.value) {
        CardDefaults.cardColors(
            containerColor = AppColors.gray_FFEEF1F4,
            contentColor = AppColors.primaryGrey
        )
    } else {
        CardDefaults.cardColors(
            containerColor = AppColors.green_100,
            contentColor = Color.White
        )
    }
) {
    Row(
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.round_check_circle_24),
            contentDescription = null
        )
        Text(
            text = buildAnnotatedString {
                append("Ürün\n")
                append("ile aynı")
            },
            style = TextStyle(
                fontFamily = poppinFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 18.sp
            )
        )
    }
}