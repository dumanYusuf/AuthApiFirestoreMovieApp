package com.example.firestoremovieapp.presentation.home_view

import com.example.firestoremovieapp.domain.model.CategoryMoviesModel
import com.example.firestoremovieapp.domain.model.PopulerMoviesModel

data class HomeState(
    val isLoading:Boolean=false,
    val categoryMoviesList: List<CategoryMoviesModel> = emptyList(),
    val errorMessage:String=""
)

data class HomePopulerState(
    val isLoading:Boolean=false,
    val populerMoviesList: List<PopulerMoviesModel> = emptyList(),
    val errorMessage:String=""
)


