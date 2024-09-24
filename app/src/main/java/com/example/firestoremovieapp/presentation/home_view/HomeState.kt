package com.example.firestoremovieapp.presentation.home_view

data class HomeState(
    val isSucsess:Boolean=false,
    val isError:String="",
    val isLoading:Boolean=false
)
