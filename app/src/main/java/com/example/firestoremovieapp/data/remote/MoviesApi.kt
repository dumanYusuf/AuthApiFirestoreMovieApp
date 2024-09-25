package com.example.firestoremovieapp.data.remote

import com.example.firestoremovieapp.data.remote.dto.CategoryMoviesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {


    // https://api.themoviedb.org/3
    // /genre/movie/list?language=en&api_key=9a618ccc9cc8813ecec78a18eaf88721
    @GET("genre/movie/list?language=en&api_key=9a618ccc9cc8813ecec78a18eaf88721")
    suspend fun getCategoryMovies(
        @Query("key") key:String
    ):CategoryMoviesDto

}