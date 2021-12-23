package com.apriega77.moviedb

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apriega77.moviedb.data.interfaces.RetroInterface
import com.apriega77.moviedb.data.model.User
import com.apriega77.moviedb.data.model.UserList
import com.apriega77.moviedb.data.model.UserResponse
import com.apriega77.moviedb.data.repository.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    lateinit var rvListUser : MutableLiveData<UserList>
    lateinit var createNewUserLiveData: MutableLiveData<UserResponse?>
    lateinit var updateUserLiveData: MutableLiveData<UserResponse?>
    lateinit var deleteUser: MutableLiveData<UserResponse?>
    lateinit var specificUserLiveData: MutableLiveData<UserResponse?>

    init {
        rvListUser = MutableLiveData()
        createNewUserLiveData = MutableLiveData()
        updateUserLiveData = MutableLiveData()
        deleteUser = MutableLiveData()
        specificUserLiveData = MutableLiveData()
    }



    fun getUserListObserverable() : MutableLiveData<UserList> {
        return rvListUser
    }

    fun getUserList() {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroInterface::class.java)
        val call = retroInstance.getUserList()
        call.enqueue(object : Callback<UserList>{
            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                if (response.isSuccessful){
                    rvListUser.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<UserList>, t: Throwable) {
                rvListUser.postValue(null)
            }

        })
    }

    fun searchUser(search : String) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroInterface::class.java)
        val call = retroInstance.searchUser(search)
        call.enqueue(object : Callback<UserList>{
            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                if (response.isSuccessful){
                    rvListUser.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<UserList>, t: Throwable) {
                rvListUser.postValue(null)
            }

        })
    }


    fun getCreateNewUserObserberable() : MutableLiveData<UserResponse?> {
        return createNewUserLiveData
    }

    fun createUser(user : User) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroInterface::class.java)
        val call = retroInstance.createUser(user)
        call.enqueue(object : Callback<UserResponse?>{
            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful){
                    createNewUserLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                createNewUserLiveData.postValue(null)
            }

        })
    }


    fun updateUserObserberable() : MutableLiveData<UserResponse?> {
        return updateUserLiveData
    }

    fun updateUser(id : String,user : User) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroInterface::class.java)
        val call = retroInstance.updateUser(id,user)
        call.enqueue(object : Callback<UserResponse?>{
            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful){
                    updateUserLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                updateUserLiveData.postValue(null)
            }

        })
    }


    fun deleteUserObserver() : MutableLiveData<UserResponse?> {
        return deleteUser
    }

    fun deleteUser(id : String) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroInterface::class.java)
        val call = retroInstance.deleteUser(id)
        call.enqueue(object : Callback<UserResponse?>{
            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful){
                    deleteUser.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                deleteUser.postValue(null)
            }

        })
    }


    fun getSpecificUserObserverable() : MutableLiveData<UserResponse?> {
        return specificUserLiveData
    }

    fun getSpecificUser(id:String) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroInterface::class.java)
        val call = retroInstance.getSpecificUser(id)
        call.enqueue(object : Callback<UserResponse?>{
            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful){
                    specificUserLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                rvListUser.postValue(null)
            }

        })
    }

}



