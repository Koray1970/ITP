package com.isticpla.itp.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.home.HomeViewMode
import com.isticpla.itp.uimodules.AppColors
import com.isticpla.itp.uimodules.AppTextFieldDefaults.Companion.TextFieldDefaultModifier
import com.isticpla.itp.uimodules.AppTextFieldWithPhoneArea

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(navController: NavController) {
    val context = LocalContext.current.applicationContext
    val homeviewModel = hiltViewModel<HomeViewMode>()
    val areacodelist =
        homeviewModel.areacodeList.collectAsStateWithLifecycle(initialValue = mutableListOf<Pair<String, String>>())
    var dropdownexpended = remember { mutableStateOf(false) }
    var phoneAreaValue = remember { mutableStateOf("") }
    var phoneNumberValue = remember { mutableStateOf("") }
    var phonenumberIsError = remember { mutableStateOf(false) }
    var phoneTextModifier = remember { mutableStateOf<Modifier>(Modifier) }
    phoneTextModifier.value.then(
        TextFieldDefaultModifier(iserror = phonenumberIsError).then(Modifier.fillMaxWidth())
    )

    val (approveCheckedState, onStateChangeApprove) = remember { mutableStateOf(true) }
    var (pcontractCheckedState, onStateChangepContract) = remember { mutableStateOf(false) }
    var chkContractedstate = remember { mutableStateOf(false) }

    BottomSheetScaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        containerColor = Color.White,
        sheetContent = {

        }
    ) { innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val headerReq = SignUpHeaderRequest()
            headerReq.title = context.getString(R.string.reg100)
            headerReq.subtitle = context.getString(R.string.reg102)
            SingUpHeader(context, headerReq)
            //AreaPhoneTextField(modifier = Modifier, context)
            AppTextFieldWithPhoneArea(
                phonetextmodifier = phoneTextModifier,
                //dropdowntextfieldmodifier=Modifier.fillMaxWidth(.40f),
                dropdownexpended = dropdownexpended,
                dropdownselectedoptionvalue = phoneAreaValue,
                phonetextfieldvalue = phoneNumberValue,
                phonetextlabel = { Text(text = "Telefon Numaranız") },
                dropdowndata = areacodelist.value.toMutableList(),
                phonetextiserror = phonenumberIsError
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .toggleable(
                        value = approveCheckedState,
                        onValueChange = { onStateChangeApprove(!approveCheckedState) },
                        role = Role.Checkbox
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.Top
            ) {
                Checkbox(
                    checked = approveCheckedState,
                    onCheckedChange = null, // null recommended for accessibility with screenreaders
                    colors = signCheckBoxColors()
                )
                Text(
                    text = "Özel bildirim, güncelleme ve haberler hakkında tarafımla e-posta ve SMS ile iletişime geçilmesini istiyorum.",
                    style = signupCheckboxLabel,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .toggleable(
                        value = pcontractCheckedState,
                        onValueChange = { onStateChangepContract(!pcontractCheckedState) },
                        role = Role.Checkbox
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = pcontractCheckedState,
                    onCheckedChange = null, // null recommended for accessibility with screenreaders
                    colors = signCheckBoxColors(isrequired = chkContractedstate)
                )
                Text(
                    text = "Üyelik Sözleşmesini okudum ve kabul ediyorum.",
                    style = signupCheckboxLabel,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    if (phoneNumberValue.value.isNullOrEmpty() || !pcontractCheckedState) {
                        if (phoneNumberValue.value.isNullOrEmpty())
                            phonenumberIsError.value = true
                        else
                            phonenumberIsError.value = true
                        chkContractedstate.value = !pcontractCheckedState
                    } else
                        navController.navigate("verifyphonenumber")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.primaryGrey,//Color(context.getColor(R.color.gray99))
                    contentColor = Color.White,
                    disabledContainerColor = AppColors.primaryGrey,//Color(context.getColor(R.color.gray99))
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
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Burada yer alan bilgilerinizi asla kimseyle paylaşmıyoruz, bilgilerinizi profilinizden değiştirebilirsiniz!",
                style = signupFooterContent,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}