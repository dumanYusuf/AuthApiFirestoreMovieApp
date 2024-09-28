package com.example.firestoremovieapp.domain.model

data class FavoriModel(
    val favoriId: String="",
    val backdrop_path: String = "",
    val genre_ids: List<Int> = emptyList(),
    val id: Int = 0,
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val vote_average: Double = 0.0
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "favoriId" to favoriId,
            "backdrop_path" to backdrop_path,
            "genre_ids" to genre_ids,
            "id" to id,
            "original_language" to original_language,
            "original_title" to original_title,
            "overview" to overview,
            "popularity" to popularity,
            "poster_path" to poster_path,
            "release_date" to release_date,
            "title" to title,
            "vote_average" to vote_average
        )
    }
}
