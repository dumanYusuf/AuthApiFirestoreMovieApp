data class UserModel(
    val id: String,
    val email: String,
    val userName: String,
    val userLastName: String,
    val profilUrl: String
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "email" to email,
            "userName" to userName,
            "userLastName" to userLastName,
            "profilUrl" to profilUrl
        )
    }

    companion object {
        fun fromMap(map: Map<String, Any>): UserModel {
            return UserModel(
                id = map["id"] as String,
                email = map["email"] as String,
                userName = map["userName"] as String,
                userLastName = map["userLastName"] as String,
                profilUrl = map["profilUrl"] as String
            )
        }
    }
}
