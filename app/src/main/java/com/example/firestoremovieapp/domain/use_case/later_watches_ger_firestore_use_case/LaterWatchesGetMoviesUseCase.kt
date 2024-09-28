package com.example.firestoremovieapp.domain.use_case.later_watches_ger_firestore_use_case

import com.example.firestoremovieapp.domain.model.LaterMovies
import com.example.firestoremovieapp.domain.repo.MoviesRepo
import com.example.firestoremovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LaterWatchesGetMoviesUseCase @Inject constructor(private val repo: MoviesRepo) {


    suspend fun getLaterMovies(): Flow<Resource<List<LaterMovies>>>{
        return repo.getLaterMoviesFiresore()
    }

}