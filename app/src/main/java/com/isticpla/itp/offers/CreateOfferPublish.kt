package com.isticpla.itp.offers

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.isticpla.itp.R
import com.isticpla.itp.poppinFamily
import com.isticpla.itp.uimodules.AppColors

@Composable
fun CreateOfferPublish(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.mipmap.confeti),
            contentDescription = null,
            modifier = Modifier.size(271.dp)
        )
        Spacer(modifier = Modifier.height(22.dp))
        Text(text = buildAnnotatedString {
            withStyle(style = offerFinalTitle) {
                append("Teklif Talebin Alındı\n\n")
            }
            withStyle(style = offerFinalSubTitle) {
                append("Ürün bilgilerinizin incelenmesinin ardından teklif operasyonları başlatılacaktır\n\n")
            }
            withStyle(style = offerFinalCoupon1) {
                append("TAKİP KODU: ")
            }
            withStyle(style = offerFinalCoupon2) {
                append("XR47HYGFV")
            }
        }, style = offerFinalStyle)
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedButton(
            onClick = { navController.navigate("offer/detail/dashboard") },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White),
            border = BorderStroke(width = 1.dp, color = AppColors.red_100),
            modifier = Modifier.fillMaxWidth(.60f)
        ) {
            Text(
                text = "Teklif Detaylarına Git",
                style = TextStyle(
                    fontFamily = poppinFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppColors.red_100
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.round_arrow_right_alt_24),
                contentDescription = null,
                tint = AppColors.red_100
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(.60f),
            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                HorizontalDivider(thickness = 1.dp, color = AppColors.grey_113)
            }
            Box(
                modifier = Modifier
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(5.dp)
                ) {
                    Text(
                        text = "Ya da",
                        style = TextStyle(
                            fontFamily = poppinFamily,
                            fontSize = 14.sp,
                            color = AppColors.grey_168
                        )
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedButton(
            onClick = {},
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White),
            border = BorderStroke(width = 1.dp, color = AppColors.red_100),
            modifier = Modifier.fillMaxWidth(.60f)
        ) {
            Text(
                text = "Kopya Oluştur",
                style = TextStyle(
                    fontFamily = poppinFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppColors.red_100
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.round_arrow_right_alt_24),
                contentDescription = null,
                tint = AppColors.red_100
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(.60f),
            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                HorizontalDivider(thickness = 1.dp, color = AppColors.grey_113)
            }
            Box(
                modifier = Modifier
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(5.dp)
                ) {
                    Text(
                        text = "Veya",
                        style = TextStyle(
                            fontFamily = poppinFamily,
                            fontSize = 14.sp,
                            color = AppColors.grey_168
                        )
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Button(
            onClick = { navController.navigate("home") },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppColors.red_100,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(.6f)
        ) {
            Text(
                text = "Ana Sayfa",
                style = TextStyle(
                    fontFamily = poppinFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.round_arrow_right_alt_24),
                contentDescription = null,
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Button(
            onClick = { navController.navigate("feed") },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppColors.red_100,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(.6f)
        ) {
            Text(
                text = "Benim Sayfam",
                style = TextStyle(
                    fontFamily = poppinFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.round_arrow_right_alt_24),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}