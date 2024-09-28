package com.example.firestoremovieapp.domain.use_case.catogory_filter_movies_use_case

import com.example.firestoremovieapp.data.remote.dto.toFilterMovies
import com.example.firestoremovieapp.domain.model.CategoryFilterMoviesModel
import com.example.firestoremovieapp.domain.repo.MoviesRepo
import com.example.firestoremovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FilterMoviesUseCase @Inject constructor(private val repo: MoviesRepo) {


    fun filterMovies(category:Int):Flow<Resource<List<CategoryFilterMoviesModel>>> = flow{
        try {
            emit(Resource.Loading())
            val result=repo.getFilterMovies(category)
            emit(Resource.Success(result.toFilterMovies()))
        }
        catch (e:Exception){
            emit(Resource.Error("Error:${e.message}"))
        }
    }

}