package com.example.firestoremovieapp.domain.use_case.get_user_use_case

import UserModel
import com.example.firestoremovieapp.domain.repo.MoviesRepo
import com.example.firestoremovieapp.util.Resource
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repo: MoviesRepo) {

    suspend fun getUser():Resource<List<UserModel>>{
        return repo.getUser()
    }

}