package com.example.firestoremovieapp.domain.use_case.populer_movies_use_case

import android.util.Log
import com.example.firestoremovieapp.data.remote.dto.toPopulerMovies
import com.example.firestoremovieapp.domain.model.PopulerMoviesModel
import com.example.firestoremovieapp.domain.repo.MoviesRepo
import com.example.firestoremovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PopulerMoviesUseCase @Inject constructor(private val repo: MoviesRepo) {

    fun getPopulerMovies(): Flow<Resource<List<PopulerMoviesModel>>> = flow {
        try {
            emit(Resource.Loading())
            val populerList= repo.getPopulerMovies()
            emit(Resource.Success(populerList.toPopulerMovies()))
            Log.e("success","data found:${populerList.results}")
        }
        catch (e:Exception){
            emit(Resource.Error("Error:${e.localizedMessage}"))
            Log.e("dara not found","not found:${e.localizedMessage}")
        }
    }

}