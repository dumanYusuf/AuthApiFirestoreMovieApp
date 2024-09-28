package com.example.firestoremovieapp.domain.repo

import com.example.firestoremovieapp.data.remote.dto.CategoryMoviesDto
import com.example.firestoremovieapp.data.remote.dto.CategoryMoviesFilterDto
import com.example.firestoremovieapp.data.remote.dto.PopulerMoviesDto
import com.example.firestoremovieapp.domain.model.FavoriModel
import com.example.firestoremovieapp.domain.model.LaterMovies
import com.example.firestoremovieapp.util.Resource
import kotlinx.coroutines.flow.Flow


interface MoviesRepo{


    // api
    suspend fun getCategoryMovies():CategoryMoviesDto
    suspend fun getPopulerMovies():PopulerMoviesDto
    suspend fun getFilterMovies(category:Int):CategoryMoviesFilterDto

    // firebase
    suspend fun addFavoriFirestore(favoriModel: FavoriModel):Resource<FavoriModel>
    suspend fun getFavoriFiresore():Flow<Resource<List<FavoriModel>>>
    suspend fun deleteFavori(favoriId:FavoriModel):Resource<FavoriModel>

    suspend fun addWatcherLaterMovies(laterMovies:LaterMovies):Resource<LaterMovies>
    suspend fun getLaterMoviesFiresore():Flow<Resource<List<LaterMovies>>>


}