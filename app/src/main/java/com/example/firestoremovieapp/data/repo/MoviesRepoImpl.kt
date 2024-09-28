package com.example.firestoremovieapp.data.repo

import com.example.firestoremovieapp.data.remote.MoviesApi
import com.example.firestoremovieapp.data.remote.dto.CategoryMoviesDto
import com.example.firestoremovieapp.data.remote.dto.CategoryMoviesFilterDto
import com.example.firestoremovieapp.data.remote.dto.PopulerMoviesDto
import com.example.firestoremovieapp.domain.model.FavoriModel
import com.example.firestoremovieapp.domain.model.LaterMovies
import com.example.firestoremovieapp.domain.repo.MoviesRepo
import com.example.firestoremovieapp.util.Constans
import com.example.firestoremovieapp.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class MoviesRepoImpl @Inject constructor(
    private val api:MoviesApi,
    private val auth:FirebaseAuth,
    private val firestore: FirebaseFirestore
):MoviesRepo{


    // api
    override suspend fun getCategoryMovies(): CategoryMoviesDto {

        return api.getCategoryMovies(Constans.API_KEY)

    }

    override suspend fun getPopulerMovies(): PopulerMoviesDto {
        return api.getPopulerMovies(Constans.API_KEY)
    }

    override suspend fun getFilterMovies(category:Int): CategoryMoviesFilterDto {
        return api.getFilterMovies(Constans.API_KEY,category)
    }



    // firebase
    override suspend fun addFavoriFirestore(favoriModel: FavoriModel): Resource<FavoriModel> {
        return try {
            val userId = auth.currentUser?.uid
            if (userId != null) {
                firestore.collection("Users").document(userId)
                    .collection("Favori")
                    .add(favoriModel.toMap())
                    .await()

                Resource.Success(favoriModel)
            } else {
                Resource.Error(("user not founf"))
            }
        } catch (e: Exception) {
            Resource.Error("Error")
        }
    }

    override suspend fun getFavoriFiresore(): Flow<Resource<List<FavoriModel>>> = flow {
        try {
            val userId = auth.currentUser?.uid
            if (userId != null) {
                val favoriDocumentRef = firestore.collection("Users").document(userId)
                    .collection("Favori").get().await()

                val favoriList = favoriDocumentRef.documents.mapNotNull { documentSnapshot ->
                    documentSnapshot.toObject(FavoriModel::class.java)
                }
                emit(Resource.Success(favoriList))
            } else {
                emit(Resource.Error("User not logged in"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error fetching favorites: ${e.message}"))
        }
    }

    override suspend fun deleteFavori(favoriId: FavoriModel): Resource<FavoriModel> {
        return try {
            val userId=auth.currentUser?.uid
            if (userId!=null){
                val favoriDocRef=firestore.collection("Users").document(userId)
                    .collection("Favori").document(favoriId.favoriId)
                favoriDocRef.delete().await()

                Resource.Success(favoriId)
            }
            else{
                Resource.Error("User Not Founf")
            }
        }
        catch (e:Exception){
            Resource.Error("Error:${e.message}")
        }
    }

    override suspend fun addWatcherLaterMovies(laterMovies: LaterMovies): Resource<LaterMovies> {
        return try {
            val userId = auth.currentUser?.uid
            if (userId != null) {
                val documentRef = firestore.collection("Users")
                    .document(userId)
                    .collection("LaterWatches")
                    .add(laterMovies.toMap())
                    .await()
                val updatedLaterMovies = laterMovies.copy(laterMoviesId = documentRef.id)


                Resource.Success(updatedLaterMovies)
            } else {
                Resource.Error("user not found")
            }
        } catch (e: Exception) {
            Resource.Error("Error: ${e.message}")
        }
    }

    override suspend fun getLaterMoviesFiresore(): Flow<Resource<List<LaterMovies>>> = flow {

        try {
            val userId = auth.currentUser?.uid
            if (userId != null) {
                val laterDocumentRef = firestore.collection("Users").document(userId)
                    .collection("LaterWatches").get().await()

                val laterList = laterDocumentRef.documents.mapNotNull { documentSnapshot ->
                    documentSnapshot.toObject(LaterMovies::class.java)
                }
                emit(Resource.Success(laterList))
            } else {
                emit(Resource.Error("User not logged in"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error fetching favorites: ${e.message}"))
        }
    }

    override suspend fun deleteLaterMovies(Id: LaterMovies): Resource<LaterMovies> {
        return try {
            val userId=auth.currentUser?.uid
            if (userId!=null){
                val favoriDocRef=firestore.collection("Users").document(userId)
                    .collection("LaterWatches").document(Id.id.toString())
                favoriDocRef.delete().await()

                Resource.Success(Id)
            }
            else{
                Resource.Error("User Not Founf")
            }
        }
        catch (e:Exception){
            Resource.Error("Error:${e.message}")
        }
    }


}