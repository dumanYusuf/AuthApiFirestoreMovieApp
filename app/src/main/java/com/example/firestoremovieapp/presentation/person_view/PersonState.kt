package com.example.firestoremovieapp.presentation.person_view

import UserModel

data class PersonState(
    val isSucsess:List<UserModel> = emptyList(),
    val isError:String="",
    val isLoading:Boolean=false
)
