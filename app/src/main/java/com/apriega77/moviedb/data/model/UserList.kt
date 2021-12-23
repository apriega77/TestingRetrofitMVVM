package com.apriega77.moviedb.data.model

data class UserList (val data:List<User>)
data class User(val id : String?,
                val name : String?,
                val gender : String?,
                val email : String?,
                val status : String?
)

data class UserResponse(val code:Int?,
                        val meta : String?,
                        val data : User?
)