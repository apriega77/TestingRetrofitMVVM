package com.apriega77.moviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.apriega77.moviedb.data.model.User
import kotlinx.android.synthetic.main.activity_post.*

class PostActivity : AppCompatActivity() {

    lateinit var viewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        initViewModel()
        createUserObserver()

        btnUplaod.setOnClickListener {
            createUser()
        }
    }

    private fun createUser() {
        val user = User("", etName.text.toString(), "Male", etEmail.text.toString(), "Active")

        viewModel.createUser(user)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    }

    private fun createUserObserver(){
        viewModel.getCreateNewUserObserberable().observe(this, Observer {
            if (it ==null){
                Toast.makeText(this, "Failed to create new user", Toast.LENGTH_LONG).show()
            } else{
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
                finish()
            }
        })
    }
}