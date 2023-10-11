package com.isticpla.itp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.isticpla.itp.signup.*
import com.isticpla.itp.splash.*

@Composable
fun AppNavigate() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { Splash(navController) }
        composable("startselectculture") { StartSelectCulture(navController) }
        composable("appintro") { AppIntro(navController) }
        composable("signup") { SignUp(navController) }
        composable("verifyphonenumber") { VerifyPhoneNumber(navController) }
        composable("createuseraccount") { CreateUserAccount(navController) }
        composable("addyourbusiness") { AddYourBusiness(navController) }
        composable("choosebusinesssalesareas") { ChooseBusinessSalesAreas(navController) }
    }
}