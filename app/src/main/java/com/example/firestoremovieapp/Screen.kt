package com.example.firestoremovieapp


sealed class Screen (val route:String){

    object HomePage:Screen("home_page")
    object FavoriPage:Screen("favori_page")
    object LaterPage:Screen("later_page")
    object PersonPage:Screen("person_page")
    object LoginPagePage:Screen("login_page")
    object RegisterPage:Screen("register_page")



}