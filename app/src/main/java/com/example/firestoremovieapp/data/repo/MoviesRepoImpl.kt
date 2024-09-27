package com.example.firestoremovieapp.data.repo

import com.example.firestoremovieapp.data.remote.MoviesApi
import com.example.firestoremovieapp.data.remote.dto.CategoryMoviesDto
import com.example.firestoremovieapp.data.remote.dto.PopulerMoviesDto
import com.example.firestoremovieapp.domain.repo.MoviesRepo
import com.example.firestoremovieapp.util.Constans
import javax.inject.Inject


class MoviesRepoImpl @Inject constructor(private val api:MoviesApi):MoviesRepo{

    override suspend fun getCategoryMovies(): CategoryMoviesDto {

        return api.getCategoryMovies(Constans.API_KEY)

    }

    override suspend fun getPopulerMovies(): PopulerMoviesDto {
        return api.getPopulerMovies(Constans.API_KEY)
    }


}