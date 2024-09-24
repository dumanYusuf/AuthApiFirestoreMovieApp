package com.example.firestoremovieapp.data.repo

import UserModel
import com.example.firestoremovieapp.domain.repo.AuthRepo
import com.example.firestoremovieapp.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepo {

    override suspend fun registerUser(user: UserModel, password: String): Resource<UserModel> {
        return try {
            val result = auth.createUserWithEmailAndPassword(user.email, password).await()
            val firebaseUser = result.user

            firebaseUser?.let {
                val newUser = UserModel(
                    id = it.uid,
                    email = it.email.toString(),
                    userName = user.userName,
                    userLastName = user.userLastName,
                    profilUrl = "https://media.istockphoto.com/id/1332100919/tr/vekt%C3%B6r/man-icon-black-icon-person-symbol.jpg?s=612x612&w=0&k=20&c=lVmBCgzh7mw_UbIHKvFtpi7W8J21mEdrNQsfOgn0PxA=" // Profil resmi için varsayılan bir URL
                )

                firestore.collection("Users").document(newUser.id).set(newUser.toMap()).await()

                Resource.Success(newUser)
            } ?: Resource.Error("Kullanıcı kaydı sırasında bir hata oluştu.:$")

        } catch (e: Exception) {
            Resource.Error("Hata: ${e.message}")
        }
    }

    override suspend fun loginUser(email: String, password: String): Resource<UserModel> {
        return try {
          val result= auth.signInWithEmailAndPassword(email,password).await()
            val firebaseUser=result.user

            firebaseUser?.let {
                Resource.Success(it.toUser())
            }?:kotlin.run {
                Resource.Error("user not found")
            }
        }
        catch (e:Exception){
            Resource.Error("Erorr:${e.message}")
        }
    }

    override fun logOut() {
        auth.signOut()
    }

    // burda FirebaseUserı user Model sınıfımızı çeviriyoruz extension fonksiyon yardımı ile
    private fun FirebaseUser.toUser():UserModel{
        return UserModel(
            id = uid,
            email = email.toString(),
            userName = "",
            userLastName = "",
            profilUrl = photoUrl.toString()
        )
    }

}
