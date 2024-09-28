package com.example.firestoremovieapp.data.remote

import com.example.firestoremovieapp.data.remote.dto.CategoryMoviesDto
import com.example.firestoremovieapp.data.remote.dto.CategoryMoviesFilterDto
import com.example.firestoremovieapp.data.remote.dto.PopulerMoviesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    // category movies
    // https://api.themoviedb.org/3
    // /genre/movie/list?language=en&api_key=9a618ccc9cc8813ecec78a18eaf88721
    @GET("genre/movie/list?language=en&api_key=9a618ccc9cc8813ecec78a18eaf88721")
    suspend fun getCategoryMovies(
        @Query("key") key:String
    ):CategoryMoviesDto

    // populer movies
    // https://api.themoviedb.org/3/
// movie/popular?language=en-US&page=1&api_key=9a618ccc9cc8813ecec78a18eaf88721

    @GET("movie/popular?language=en-US&page=1&api_key=9a618ccc9cc8813ecec78a18eaf88721")
    suspend fun getPopulerMovies(
        @Query("key") key:String
    ):PopulerMoviesDto



    //categoryFilterMovies
    //https://api.themoviedb.org/3/
// discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc&api_key=9a618ccc9cc8813ecec78a18eaf88721

    @GET("discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc&api_key=9a618ccc9cc8813ecec78a18eaf88721")
    suspend fun getFilterMovies(
        @Query("key") key: String,
        @Query("with_genres") genreId: Int,
    ):CategoryMoviesFilterDto


}