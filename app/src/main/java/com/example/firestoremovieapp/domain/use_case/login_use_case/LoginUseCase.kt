package com.example.firestoremovieapp.domain.use_case.login_use_case

import UserModel
import com.example.firestoremovieapp.domain.repo.AuthRepo
import com.example.firestoremovieapp.util.Resource
import javax.inject.Inject


class LoginUseCase @Inject constructor(private val repo: AuthRepo) {

    suspend operator fun invoke(email:String,password:String):Resource<UserModel>{
        return repo.loginUser(email,password)
    }
}