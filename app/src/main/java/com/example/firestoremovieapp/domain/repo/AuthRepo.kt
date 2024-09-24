package com.example.firestoremovieapp.domain.repo

import UserModel
import com.example.firestoremovieapp.util.Resource


interface AuthRepo {

        suspend fun registerUser(user: UserModel,password:String):Resource<UserModel>
        suspend fun loginUser(email:String,password: String):Resource<UserModel>
        fun logOut()


}