package com.medbuddy.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.medbuddy.ui.screen.LoginScreen
import com.medbuddy.ui.screen.Medicines

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavItems.Login.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){
        composable(NavItems.Login.route) {
            LoginScreen(navController)
        }
        composable("${NavItems.Medicines.route}/{name}") {navBackStackEntry ->
            val name = navBackStackEntry.arguments?.getString("name")
            requireNotNull(name)
            Medicines(navController,name)
        }
    }

}