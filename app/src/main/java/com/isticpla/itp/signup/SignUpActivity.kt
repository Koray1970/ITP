package com.isticpla.itp.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.isticpla.itp.AppNavigate
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.listofAraeCodes
import com.isticpla.itp.signup.ui.theme.ITPTheme

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(navController: NavController) {
    val context = LocalContext.current.applicationContext
    var phoneareaDropdownExpandState = remember { mutableStateOf(false) }
    var phoneNumberValue by remember { mutableStateOf("") }
    val cGrayPrimary = Color(context.getColor(R.color.grayprimary))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = context.getString(R.string.reg100),
            style = signupHeader(context),
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = context.getString(R.string.reg102),
            style = signupSubTitle(context),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .background(Color.White)
                .border(1.dp, cGrayPrimary, RoundedCornerShape(5.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(0.dp),
                colors = CardDefaults.cardColors(contentColor = Color.White),
                shape = RectangleShape,
                onClick = { phoneareaDropdownExpandState.value = true }) {
                ListItem(
                    modifier = Modifier.padding(0.dp).width(220.dp),
                    colors = ListItemDefaults.colors(containerColor = Color.White),
                    headlineContent = {
                        Text(
                            listofAraeCodes.first().first + " " + listofAraeCodes.first().second,
                            style = signupPhoneComboBox(context),
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    trailingContent = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_expand_more_24),
                            modifier = Modifier.size(12.dp),
                            contentDescription = null
                        )
                    }
                )
            }
            DropdownMenu(
                modifier = Modifier
                    .weight(1f)
                    .border(0.dp, Color.White, RectangleShape),
                expanded = phoneareaDropdownExpandState.value,
                onDismissRequest = { phoneareaDropdownExpandState.value = false }) {
                repeat(listofAraeCodes.size) {
                    DropdownMenuItem(
                        text = { Text(text = "${listofAraeCodes[it].first} ${listofAraeCodes[it].second}") },
                        onClick = { phoneareaDropdownExpandState.value = false },
                        colors = MenuItemColors(
                            cGrayPrimary,
                            cGrayPrimary,
                            cGrayPrimary,
                            cGrayPrimary,
                            cGrayPrimary,
                            cGrayPrimary
                        )
                    )
                }
            }
            VerticalDivider(
                modifier = Modifier
                    .height(28.dp)
                    .padding(horizontal = 5.dp, vertical = 2.dp),
                thickness = 1.dp,
                color = cGrayPrimary
            )
            TextField(
                value = phoneNumberValue,
                onValueChange = { phoneNumberValue = it },
                label = { Text(context.getString(R.string.reg103), color = cGrayPrimary) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RectangleShape,
                singleLine=true,
                maxLines=1,
                modifier = Modifier.border(BorderStroke(0.dp, Color.Transparent)),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    autoCorrect = false
                ),
            )
        }
    }
}

@Composable
fun VerifyPhoneNumber(navController: NavController) {

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
