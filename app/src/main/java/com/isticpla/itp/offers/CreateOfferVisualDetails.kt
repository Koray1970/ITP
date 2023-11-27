package com.isticpla.itp.offers

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.isticpla.itp.uimodules.AppColors
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

    val imagelist =
        offerViewModel.getFileFromLocalDir.collectAsStateWithLifecycle(initialValue = emptyList<String>().toMutableStateList())

    val sharedGalleryImageList = offerViewModel.getFileFromSharedDir.collectAsStateWithLifecycle(
        initialValue = emptyList<Uri>().toMutableStateList()
    )



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

                    if (imagelist.value.isNotEmpty()) {
                        FlowRow(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Start,
                            verticalArrangement = Arrangement.spacedBy(
                                10.dp,
                                Alignment.Top
                            ),
                            maxItemsInEachRow = 3
                        ) {
                            imagelist.value.filter { a -> a.contains(".jpg") }.forEach { img ->
                                AsyncImage(
                                    modifier = Modifier.size(140.dp),
                                    model = "${context.filesDir}/${img}",
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit
                                )
                            }
                        }
                    }
                    HorizontalDivider(thickness = 1.dp, color = Color.Red)
                    if (sharedGalleryImageList.value.isNotEmpty()) {
                        FlowRow(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Start,
                            verticalArrangement = Arrangement.spacedBy(
                                10.dp,
                                Alignment.Top
                            ),
                            maxItemsInEachRow = 3
                        ) {
                            sharedGalleryImageList.value.forEach { img ->
                                AsyncImage(
                                    modifier = Modifier.size(140.dp),
                                    model = img,
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit
                                )
                            }
                        }
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
            Text(text = "Fotoğraf & Video", style = offerProductDetailFormSectionTitle)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start)
            ) {
                UseCamera(
                    radius = radius,
                    offerViewModel,
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
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun UseCamera(
    radius: Dp,
    offerViewModel: OfferViewModel,
    showGalleryBottomSheet: MutableState<Boolean> = mutableStateOf(false),
    sheetState: MutableState<SheetState>
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var file = context.createImageFile()
    var uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context), BuildConfig.APPLICATION_ID + ".provider", file
    )

    val cameraLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicture()) {
            if (it != null) {

                //captureImageUri.value = uri
                var bitmap: Bitmap?

                val loadBitmap = scope.launch(Dispatchers.IO) {
                    val loader = ImageLoader(context)
                    val request =
                        ImageRequest.Builder(context).data(uri).allowHardware(false).build()
                    val result = loader.execute(request)
                    if (result is SuccessResult) {
                        bitmap = (result.drawable as BitmapDrawable).bitmap
                        bitmap?.compress(
                            Bitmap.CompressFormat.JPEG,
                            100,
                            context.createImageFileToLocalDirectory().outputStream()
                        )
                        //offerViewModel.ReloadMediaFiles()
                        delay(250L)
                        showGalleryBottomSheet.value = true
                        sheetState.value.show()
                    } else if (result is ErrorResult) {
                        cancel(result.throwable.localizedMessage ?: "ErrorResult", result.throwable)
                    }
                }
            }
        }
    val permissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
            if (it) {
                cameraLauncher.launch(uri)
            } else {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    Button(
        onClick = {
            val permissionCheckResult =
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA)
            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                cameraLauncher.launch(uri)
            } else {
                // Request a permission
                permissionLauncher.launch(android.Manifest.permission.CAMERA)
            }
        }, shape = RoundedCornerShape(radius), colors = ButtonDefaults.buttonColors(
            containerColor = AppColors.blue_0xFF0495f1, contentColor = Color.White
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.outline_camera_alt_24),
            contentDescription = null
        )
        Text(text = "Kamera", style = offerMediaButtonLabels)
    }
}


fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    return File.createTempFile(timeStamp, ".jpg", externalCacheDir)
}

fun Context.createImageFileToLocalDirectory(): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    return File.createTempFile(timeStamp, ".jpg", filesDir)
}