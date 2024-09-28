package com.example.firestoremovieapp.domain.repo

import com.example.firestoremovieapp.data.remote.dto.CategoryMoviesDto
import com.example.firestoremovieapp.data.remote.dto.CategoryMoviesFilterDto
import com.example.firestoremovieapp.data.remote.dto.PopulerMoviesDto



interface MoviesRepo{


    suspend fun getCategoryMovies():CategoryMoviesDto
    suspend fun getPopulerMovies():PopulerMoviesDto
    suspend fun getFilterMovies(category:Int):CategoryMoviesFilterDto


}