package com.example.firestoremovieapp.data.remote.dto

import com.example.firestoremovieapp.domain.model.CategoryMoviesModel

data class CategoryMoviesDto(
    val genres: List<GenreDto>
)

fun CategoryMoviesDto.toCategoryMovies():List<CategoryMoviesModel>{
    return genres.map { CategoryMoviesModel(it.id,it.name) }
}