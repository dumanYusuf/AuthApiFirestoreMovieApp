package com.example.firestoremovieapp.domain.use_case.later_watches_movies_use_case

import com.example.firestoremovieapp.domain.model.LaterMovies
import com.example.firestoremovieapp.domain.repo.MoviesRepo
import com.example.firestoremovieapp.util.Resource
import javax.inject.Inject

class LaterWatchesAddFirestoreUseCase @Inject constructor(private val repo: MoviesRepo) {


    suspend fun addLaterMovies(laterMovies:LaterMovies):Resource<LaterMovies>{
        return repo.addWatcherLaterMovies(laterMovies)
    }

}