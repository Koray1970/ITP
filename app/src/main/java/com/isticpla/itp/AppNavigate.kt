package com.isticpla.itp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.isticpla.itp.splash.AppIntro
import com.isticpla.itp.splash.Splash
import com.isticpla.itp.splash.StartSelectCulture

@Composable
fun AppNavigate(){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { Splash(navController) }
        composable("startselectculture") { StartSelectCulture(navController) }
        composable("appintro") { AppIntro(navController) }
    }
}