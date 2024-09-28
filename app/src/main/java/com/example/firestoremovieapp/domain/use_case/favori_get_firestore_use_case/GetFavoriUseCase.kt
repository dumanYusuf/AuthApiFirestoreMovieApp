package com.example.firestoremovieapp.domain.use_case.favori_get_firestore_use_case

import com.example.firestoremovieapp.domain.model.FavoriModel
import com.example.firestoremovieapp.domain.repo.MoviesRepo
import com.example.firestoremovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriUseCase @Inject constructor(private val repo: MoviesRepo) {


    suspend fun getFavori():Flow<Resource<List<FavoriModel>>>{
        return repo.getFavoriFiresore()
    }

}
