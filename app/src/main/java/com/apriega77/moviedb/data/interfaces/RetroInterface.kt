package com.apriega77.moviedb.data.interfaces

import com.apriega77.moviedb.data.model.User
import com.apriega77.moviedb.data.model.UserList
import com.apriega77.moviedb.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface RetroInterface {
    //https://gorest.co.in/public/v1/users
    @GET("users")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun getUserList() : Call<UserList>

    //https://gorest.co.in/public/v1/users?name=
    @GET("users")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun searchUser(@Query("name") searchText : String) : Call<UserList>


    //https://gorest.co.in/public/v1/users/11
    @GET("users/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun getSpecificUser(@Path("user_id") userId : String) : Call<UserResponse>

    @POST("users")
    @Headers ("Accept:application/json" ,
        "Content-Type:application/json" ,
        "Authorization: Bearer 6796f652a9035be00dbb22181540756c1dbbea237ce0a67d70f2a2aae5647675")
    fun createUser(@Body params : User) : Call<UserResponse>


    @PATCH("users/{user_id}")
    @Headers ("Accept:application/json" ,
        "Content-Type:application/json" ,
        "Authorization: Bearer 6796f652a9035be00dbb22181540756c1dbbea237ce0a67d70f2a2aae5647675")
    fun updateUser(@Path("user_id") userId: String, @Body params : User) : Call<UserResponse>


    @DELETE("users/{user_id}")
    @Headers ("Accept:application/json" ,
        "Content-Type:application/json" ,
        "Authorization: Bearer 6796f652a9035be00dbb22181540756c1dbbea237ce0a67d70f2a2aae5647675")
    fun deleteUser(@Path("user_id") userId: String) : Call<UserResponse>

}