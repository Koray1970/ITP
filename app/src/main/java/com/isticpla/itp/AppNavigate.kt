package com.isticpla.itp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.isticpla.itp.signup.*
import com.isticpla.itp.splash.*

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun AppNavigate() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "feed") {
        composable("splash") { Splash(navController) }
        composable("startselectculture") { StartSelectCulture(navController) }
        composable("appintro") { AppIntro(navController) }
        composable("signup") { SignUp(navController) }
        composable("verifyphonenumber") { VerifyPhoneNumber(navController) }
        composable("createuseraccount") { CreateUserAccount(navController) }
        composable("addyourbusiness") { AddYourBusiness(navController) }
        composable("choosebusinesssalesareas") { ChooseBusinessSalesAreas(navController) }

        composable("home") { Home(navController) }
        composable("home/notifications") { Notifications(navController) }
        composable("home/jobs") { Jobs(navController) }
        composable("home/messages") { Messages(navController) }

        composable("feed") { FeedDashboard(navController) }
        composable("feed/productdetail") { FeedProductDetail(navController) }

        composable("newlist") { NewsListDashboard(navController) }
        composable("newslist/detail") { NewsListDetail(navController) }
    }
}