package com.example.firestoremovieapp.data.remote.dto

import com.example.firestoremovieapp.domain.model.PopulerMoviesModel

data class PopulerMoviesDto(
    val page: Int,
    val results: List<ResultDto>,
    val total_pages: Int,
    val total_results: Int
)

fun PopulerMoviesDto.toPopulerMovies():List<PopulerMoviesModel>{
    return results.map { PopulerMoviesModel(it.release_date,it.genre_ids,it.id,it.poster_path,it.backdrop_path,it.overview,it.popularity,it.original_title,it.original_language,it.title,it.vote_average) }
}

