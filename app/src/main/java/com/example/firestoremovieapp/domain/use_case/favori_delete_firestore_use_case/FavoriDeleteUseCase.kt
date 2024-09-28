package com.example.firestoremovieapp.domain.use_case.favori_delete_firestore_use_case

import com.example.firestoremovieapp.domain.model.FavoriModel
import com.example.firestoremovieapp.domain.repo.MoviesRepo
import com.example.firestoremovieapp.util.Resource
import javax.inject.Inject

class FavoriDeleteUseCase @Inject constructor(private val repo: MoviesRepo) {


    suspend operator fun invoke(favoriId:FavoriModel):Resource<FavoriModel>{
        return repo.deleteFavori(favoriId)
    }

}