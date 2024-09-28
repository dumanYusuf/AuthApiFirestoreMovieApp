package com.example.firestoremovieapp.domain.use_case.later_watches_delete_movies_use_case

import com.example.firestoremovieapp.domain.model.LaterMovies
import com.example.firestoremovieapp.domain.repo.MoviesRepo
import com.example.firestoremovieapp.util.Resource
import javax.inject.Inject

class DeleteLaterMoviesUseCase @Inject constructor(private val repo: MoviesRepo) {


    suspend operator fun invoke(id:LaterMovies):Resource<LaterMovies>{
        return repo.deleteLaterMovies(id)
    }

}