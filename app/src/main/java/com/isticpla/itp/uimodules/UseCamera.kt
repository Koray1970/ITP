package com.isticpla.itp.uimodules

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.isticpla.itp.BuildConfig
import com.isticpla.itp.R
import com.isticpla.itp.offers.offerMediaButtonLabels
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun UseCamera(
    radius: Dp,
    showGalleryBottomSheet: MutableState<Boolean> = mutableStateOf(false),
    sheetState: MutableState<SheetState>
) {
    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context), BuildConfig.APPLICATION_ID + ".provider", file
    )

    val cameraLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicture()) {

            //captureImageUri.value = uri
            var bitmap: Bitmap?

            /*val loadBitmap = scope.launch(Dispatchers.IO) {
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
            }*/
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
        modifier=Modifier.size(140.dp, 48.dp),
        onClick = {
            val permissionCheckResult =
                ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                cameraLauncher.launch(uri)
            } else {
                // Request a permission
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }, shape = RoundedCornerShape(radius), colors = ButtonDefaults.buttonColors(
            containerColor = AppColors.blue_0xFF0495f1, contentColor = Color.White
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.outline_camera_alt_24),
            contentDescription = null,modifier= Modifier.padding(end=10.dp)
        )
        Text(text = "Kamera", style = offerMediaButtonLabels)
    }
}

@SuppressLint("SimpleDateFormat")
fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    return File.createTempFile(timeStamp, ".jpg", externalCacheDir)
}

@SuppressLint("SimpleDateFormat")
fun Context.createImageFileToLocalDirectory(): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    return File.createTempFile(timeStamp, ".jpg", filesDir)
}