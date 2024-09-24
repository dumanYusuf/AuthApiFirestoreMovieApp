package com.example.firestoremovieapp.domain.use_case.register_use_case

import UserModel
import com.example.firestoremovieapp.domain.repo.AuthRepo
import com.example.firestoremovieapp.util.Resource
import javax.inject.Inject


class RegisterUseCase @Inject constructor(private val repo: AuthRepo) {

    suspend fun register(user:UserModel,password:String):Resource<UserModel>{
        return repo.registerUser(user,password)
    }

}