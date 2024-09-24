package com.example.firestoremovieapp.data.di

import com.example.firestoremovieapp.data.repo.AuthRepoImpl
import com.example.firestoremovieapp.domain.repo.AuthRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
     fun providesAuth():FirebaseAuth=FirebaseAuth.getInstance()

    @Provides
    @Singleton
   fun  providesAuthRepo(auth:FirebaseAuth,firestore:FirebaseFirestore):AuthRepo{
       return AuthRepoImpl(auth,firestore)
   }

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()
}