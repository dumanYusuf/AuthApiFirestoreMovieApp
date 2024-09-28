package com.example.firestoremovieapp.data.remote.dto

import com.example.firestoremovieapp.domain.model.CategoryFilterMoviesModel

data class CategoryMoviesFilterDto(
    val page: Int,
    val results: List<ResultFilterMoviesDto>,
    val total_pages: Int,
    val total_results: Int
)

fun CategoryMoviesFilterDto.toFilterMovies():List<CategoryFilterMoviesModel>{
    return results.map { CategoryFilterMoviesModel(it.release_date,it.genre_ids,it.id,it.poster_path,it.backdrop_path,it.overview,it.popularity,it.original_title,it.original_language,it.title,it.vote_average) }
}

