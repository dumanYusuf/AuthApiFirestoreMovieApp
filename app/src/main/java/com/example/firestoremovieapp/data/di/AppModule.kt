package com.example.firestoremovieapp.data.di

import com.example.firestoremovieapp.data.remote.MoviesApi
import com.example.firestoremovieapp.data.repo.AuthRepoImpl
import com.example.firestoremovieapp.data.repo.MoviesRepoImpl
import com.example.firestoremovieapp.domain.repo.AuthRepo
import com.example.firestoremovieapp.domain.repo.MoviesRepo
import com.example.firestoremovieapp.util.Constans
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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


    @Provides
    @Singleton
    fun providesRetrofit():MoviesApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constans.BASE_URL).build().create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun providesMoviesRepo(api: MoviesApi,auth: FirebaseAuth,firestore: FirebaseFirestore): MoviesRepo {
        return MoviesRepoImpl(api,auth,firestore)
    }

}