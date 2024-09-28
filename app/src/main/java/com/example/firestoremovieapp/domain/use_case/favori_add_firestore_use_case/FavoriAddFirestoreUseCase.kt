package com.example.firestoremovieapp.domain.use_case.favori_add_firestore_use_case

import com.example.firestoremovieapp.domain.model.FavoriModel
import com.example.firestoremovieapp.domain.repo.MoviesRepo
import com.example.firestoremovieapp.util.Resource
import javax.inject.Inject

class FavoriAddFirestoreUseCase @Inject constructor(private val repo: MoviesRepo) {



    suspend fun addFavori(favori:FavoriModel):Resource<FavoriModel>{
        return repo.addFavoriFirestore(favori)
    }
}