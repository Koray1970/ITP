package com.isticpla.itp.uimodules

import android.Manifest.permission.CAMERA
import android.content.Context
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.isticpla.itp.R
import com.isticpla.itp.poppinFamily
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.io.File
import java.util.Date
import java.util.Objects


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AppMediaUploader() {
    val context = LocalContext.current.applicationContext


    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        context.packageName + ".provider", file
    )
    var capturedImageUri = remember { mutableStateListOf<Uri>() }
    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            capturedImageUri.add(uri)
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }
    val textMeasurer = rememberTextMeasurer()
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp,Alignment.CenterVertically),
        horizontalAlignment = Alignment.Start
    ) {
        //region media add buttons
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start)
        ) {
            Button(
                onClick = {
                    val permissionCheckResult =
                        ContextCompat.checkSelfPermission(context, CAMERA)
                    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                        cameraLauncher.launch(uri)
                    } else {
                        // Request a permission
                        permissionLauncher.launch(CAMERA)
                    }
                },
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.blue_102,
                    contentColor = Color.White
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_camera_alt_24),
                    contentDescription = null
                )
                Text(text = "Kamera")
            }
            Button(
                onClick = {},
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.blue_102,
                    contentColor = Color.White
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_image_24),
                    contentDescription = null
                )
                Text(text = "Galeri")
            }
        }
        //endregion
        Row(
            modifier = Modifier
                //.background(Color.Red)
                .fillMaxWidth(.92f)
                .requiredHeightIn(min = 96.dp, max = 320.dp)
                .drawBehind {
                    val stroke = Stroke(
                        width = 1.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f), 0f)
                    )
                    drawRoundRect(
                        color = AppColors.blue_102,
                        style = stroke,
                        cornerRadius = CornerRadius(6.dp.toPx())
                    )
                    val measuredText =
                        textMeasurer.measure(
                            AnnotatedString(text = "...Ürün Görselleri..."),
                            constraints = Constraints.fixed(
                                width =(size.width/2f).toInt(),
                                height = (size.height/2f).toInt()
                            ),
                            style = TextStyle(
                                fontFamily = poppinFamily,
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center,
                                color = AppColors.primaryGreyDisabled
                            )
                        )
                    drawText(measuredText, topLeft = Offset(x=(size.width/6.4f).dp.toPx(),y=(size.height/5).dp.toPx()))
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp,Alignment.CenterHorizontally)
        ) {
            if (capturedImageUri.isNotEmpty()) {
                capturedImageUri.forEach { img ->
                    Image(
                        modifier = Modifier
                            .size(70.dp),
                        painter = rememberAsyncImagePainter(img),
                        contentDescription = null
                    )
                }
            }
        }

    }
}

fun Context.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir      /* directory */
    )
    return image
}