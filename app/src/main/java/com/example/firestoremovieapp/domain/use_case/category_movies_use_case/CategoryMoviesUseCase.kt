package com.example.firestoremovieapp.domain.use_case.category_movies_use_case

import com.example.firestoremovieapp.data.remote.dto.toCategoryMovies
import com.example.firestoremovieapp.domain.model.CategoryMoviesModel
import com.example.firestoremovieapp.domain.repo.MoviesRepo
import com.example.firestoremovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class CategoryMoviesUseCase @Inject constructor(private val repo: MoviesRepo){

    fun getCategory(): Flow<Resource<List<CategoryMoviesModel>>> = flow{
        try {
            emit(Resource.Loading())
           val result= repo.getCategoryMovies()
            emit(Resource.Success(result.toCategoryMovies()))
        }
        catch (e:Exception){
            emit(Resource.Error("Erorr:${e.localizedMessage}"))
        }
    }

}