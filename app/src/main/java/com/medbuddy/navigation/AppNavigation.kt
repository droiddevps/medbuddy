package com.medbuddy.navigation

enum class Screens{
    Login,
    Medicines
}

sealed class NavItems(val route: String){
    data object Login: NavItems(Screens.Login.name)
    data object Medicines: NavItems(Screens.Medicines.name)
}