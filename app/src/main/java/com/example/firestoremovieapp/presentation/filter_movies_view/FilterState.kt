package com.example.firestoremovieapp.presentation.filter_movies_view

import com.example.firestoremovieapp.domain.model.CategoryFilterMoviesModel


data class FilterState(
    val isLoading:Boolean=false,
    val categoryFiLTERMoviesList: List<CategoryFilterMoviesModel> = emptyList(),
    val errorMessage:String=""
)
