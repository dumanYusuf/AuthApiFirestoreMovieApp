package com.example.firestoremovieapp.domain.repo

import com.example.firestoremovieapp.data.remote.dto.CategoryMoviesDto


interface MoviesRepo{


    suspend fun getCategoryMovies():CategoryMoviesDto

}