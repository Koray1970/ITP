package com.isticpla.itp.signup

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.navigation.NavController
import com.isticpla.itp.AppNavigate
import com.isticpla.itp.R
import com.isticpla.itp.signup.ui.theme.ITPTheme
import dagger.hilt.android.qualifiers.ApplicationContext

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ITPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    AppNavigate()
                }
            }
        }
    }
}
lateinit var context:Context
@Composable
fun SingUpHeader(context: Context, title: String, subtitle: String) {
    Text(
        text = title,//context.getString(R.string.reg100),
        style = signupHeader(context),
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(40.dp))
    Text(
        text = subtitle,//context.getString(R.string.reg102),
        style = signupSubTitle(context),
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(80.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(navController: NavController) {
    context = LocalContext.current.applicationContext
    var phoneareaDropdownExpandState = remember { mutableStateOf(false) }
    var phoneNumberValue by remember { mutableStateOf("") }
    val (approveCheckedState, onStateChangeApprove) = remember { mutableStateOf(true) }
    val (pcontractCheckedState, onStateChangepContract) = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SingUpHeader(
            context,
            context.getString(R.string.reg100),
            context.getString(R.string.reg102)
        )
        AreaPhoneTextField(modifier = Modifier, context)
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
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = approveCheckedState,
                onCheckedChange = null, // null recommended for accessibility with screenreaders
                colors = signCheckBoxColors(context)
            )
            Text(
                text = "Özel bildirim, güncelleme ve haberler hakkında tarafımla e-posta ve SMS ile iletişime geçilmesini istiyorum.",
                style = signupCheckboxLabel(context),
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
                colors = signCheckBoxColors(context)
            )
            Text(
                text = "Üyelik Sözleşmesini okudum ve kabul ediyorum.",
                style = signupCheckboxLabel(context),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { navController.navigate("verifyphonenumber") },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(context.getColor(R.color.grayprimary)),
                contentColor = Color.White,
                disabledContainerColor = Color(context.getColor(R.color.grayprimary)),
                disabledContentColor = Color.White
            )
        ) {
            Text(text = "Devam Et", style = signupSubmitButton(context))
            Spacer(modifier = Modifier.weight(1f))
            Icon(painter = painterResource(id = R.drawable.arrow_right), contentDescription = null)
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Burada yer alan bilgilerinizi asla kimseyle paylaşmıyoruz, bilgilerinizi profilinizden değiştirebilirsiniz!",
            style = signupSegmentTitle(context),
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
fun VerifyPhoneNumber(navController: NavController) {
    context=LocalContext.current.applicationContext
    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SingUpHeader(
                context,
                context.getString(R.string.reg200),
                String.format(context.getString(R.string.reg201),"+905336221493")
            )
        }
    }
}

@Composable
fun CreateUserAccount(navController: NavController) {

}

@Composable
fun AddYourBusiness(navController: NavController) {

}

@Composable
fun ChooseBusinessSalesAreas(navController: NavController) {

}
