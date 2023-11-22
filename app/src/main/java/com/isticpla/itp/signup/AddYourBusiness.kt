package com.isticpla.itp.signup

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.isticpla.itp.R
import com.isticpla.itp.database.Account
import com.isticpla.itp.database.AccountViewModel
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.mediaworks.MediaWorksEvents
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.AppDropdown
import com.isticpla.itp.uimodules.AppTextField
import com.isticpla.itp.uimodules.AppTextFieldDefaults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.File
import java.util.UUID

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun AddYourBusiness(
    navController: NavController
) {
    val context = LocalContext.current.applicationContext
    val scope = rememberCoroutineScope()
    val homeviewModel = hiltViewModel<HomeViewMode>()

    val accountViewModel = hiltViewModel<AccountViewModel>()
    val getaccountdb =
        accountViewModel.getAccount.collectAsStateWithLifecycle(initialValue = Account())

    val empoyeepositions =
        homeviewModel.employeePositions.collectAsStateWithLifecycle(initialValue = emptyList<Pair<String, String>>())
    var companynameValue = rememberSaveable { mutableStateOf("") }
    var companynameError = remember { mutableStateOf(false) }

    var positionValue = rememberSaveable { mutableStateOf("") }
    var positionKey = rememberSaveable { mutableStateOf("") }
    var positionExpand = remember { mutableStateOf(false) }


    val uriparse = Uri.parse("android.resource://${context.packageName}/${R.raw.profilephoto}")
    var imageUri = remember { mutableStateOf(uriparse) }

    var coilPainter = rememberAsyncImagePainter(model = uriparse)
    var bitmap: Bitmap? = null
    val pickMedia =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { uri ->
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (uri != null) {
                imageUri.value = uri
                val loadBitmap = scope.launch(Dispatchers.IO) {
                    val loader = ImageLoader(context)
                    val request = ImageRequest.Builder(context)
                        .data(uri)
                        .allowHardware(false)
                        .build()
                    val result = loader.execute(request)
                    if (result is SuccessResult) {
                        bitmap = (result.drawable as BitmapDrawable).bitmap
                    } else if (result is ErrorResult) {
                        cancel(result.throwable.localizedMessage ?: "ErrorResult", result.throwable)
                    }
                }
                Log.d("PhotoPicker", "Selected URI: $uri")
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }
    LaunchedEffect(Unit) {
        delay(1000)
        if (!getaccountdb.value.companyname.isNullOrEmpty())
            companynameValue.value = getaccountdb.value.companyname!!
        if (getaccountdb.value.employeeposition != null)
            positionKey.value = getaccountdb.value.employeeposition!!.toString()
        homeviewModel.getEmployeePositionResul(positionKey.value).collectLatest {
            if (it.isNotEmpty())
                positionValue.value = it.first().second
        }
        if (!getaccountdb.value.companylogo.isNullOrEmpty()) {
            imageUri.value = Uri.parse(getaccountdb.value.companylogo)
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(0.dp),
        containerColor = Color.White
    ) { innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 72.dp, bottom = 30.dp)
                    .height(10.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .weight(.33f)
                        .height(10.dp)
                        .background(color = AppColors.yellow_100)
                )
                Spacer(modifier = Modifier.weight(.05f))
                Box(
                    modifier = Modifier
                        .weight(.33f)
                        .height(10.dp)
                        .background(color = AppColors.yellow_100)
                )
                Spacer(modifier = Modifier.weight(.05f))
                Box(
                    modifier = Modifier
                        .weight(.33f)
                        .height(10.dp)
                        .background(color = AppColors.grey_127)
                )
            }
            val headerReq = SignUpHeaderRequest()
            headerReq.title = "İşletmenizi ekleyin"
            headerReq.subtitle = "İşletmenizin bilgilerini giriniz!"
            SingUpHeader(context = context, request = headerReq)
            //region File loader
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .size(100.dp)
                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(10.dp),
                            clip = true
                        ),
                    contentAlignment = Alignment.Center

                ) {

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUri.value)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .border(5.dp, Color.White, RoundedCornerShape(10.dp))
                    )
                }

                Column {
                    Text(
                        text = "İşletme Logo",
                        style = TextStyle(
                            lineHeight = 14.em,
                            fontSize = 12.sp,
                            color = AppColors.grey_118
                        )
                    )
                    Text(
                        text = "1024 x 1024, .png, .jpg",
                        style = TextStyle(
                            lineHeight = 15.em,
                            fontSize = 14.sp,
                            color = AppColors.primaryGrey
                        ),
                        modifier = Modifier.padding(vertical = 3.dp)
                    )
                    Button(
                        onClick = {
                            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        },
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppColors.blue_104,
                            contentColor = Color.White,
                            disabledContainerColor = AppColors.blue_104,
                            disabledContentColor = Color.White
                        ),
                        modifier = Modifier.requiredHeight(48.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_edit_24),
                            contentDescription = null
                        )
                        Text(
                            text = "Logo Yükle",
                            style = TextStyle(fontSize = 16.sp),
                            modifier = Modifier.padding(horizontal = 6.dp)
                        )
                    }
                }
            }
            //endregion
            Spacer(modifier = Modifier.height(40.dp))

            AppTextField(
                modifier = AppTextFieldDefaults.TextFieldDefaultModifier(
                    fillmaxwidth = 1f,
                    iserror = companynameError
                ),
                txtvalue = companynameValue,
                label = {
                    Text(
                        text = "İşletme Adı (zorunlu)",
                        style = AppTextFieldDefaults.TextFieldTextStyle
                    )
                },
                isError = companynameError,
                singleLine = true,
            )
            AppDropdown(
                selectedOptionText = positionValue,
                selectedOptionKey = positionKey,
                expended = positionExpand,
                listdata = empoyeepositions.value,
                dropdownlabel = {
                    Text(
                        "İşletmediki Pozisyonunuz",
                        style = AppTextFieldDefaults.TextFieldTextStyle
                    )
                }
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
                    if (companynameValue.value.isEmpty()) {
                        companynameError.value = true
                    } else {
                        scope.launch {
                            getaccountdb.value.companyname = companynameValue.value
                            if (positionKey.value.isNotEmpty())
                                getaccountdb.value.employeeposition =
                                    positionKey.value.toInt()
                            if (bitmap != null) {
                                val mediaWorksEvents = MediaWorksEvents(context)
                                val newfilename = UUID.randomUUID().toString()
                                if (bitmap != null) {
                                    mediaWorksEvents.saveImageToInternalStorage(
                                        newfilename,
                                        bitmap!!
                                    )
                                }
                                delay(400)
                                val file = File(context.filesDir, "$newfilename.jpg")
                                if (file.exists())
                                    getaccountdb.value.companylogo = file.absolutePath
                                else
                                    getaccountdb.value.companylogo = null
                            }
                            accountViewModel.UpsertAccount(getaccountdb.value)
                            delay(400)
                            navController.navigate("choosebusinesssalesareas")
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.primaryGrey,//context.getColor(R.color.gray99)
                    contentColor = Color.White,
                    disabledContainerColor = AppColors.primaryGrey,//context.getColor(R.color.gray99)
                    disabledContentColor = Color.White
                )
            ) {
                Text(text = "Devam Et", style = signupSubmitButton)
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = null
                )
            }

        }
    }
}