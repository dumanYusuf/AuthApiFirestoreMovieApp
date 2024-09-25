package com.example.firestoremovieapp.presentation.home_view

import com.example.firestoremovieapp.domain.model.CategoryMoviesModel

data class HomeState(
    val isLoading:Boolean=false,
    val categoryMoviesList: List<CategoryMoviesModel> = emptyList(),
    val errorMessage:String=""
)
