package com.apriega77.moviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.apriega77.moviedb.data.model.User
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.activity_post.btnUplaod
import kotlinx.android.synthetic.main.activity_post.etEmail
import kotlinx.android.synthetic.main.activity_post.etName
import kotlinx.android.synthetic.main.activity_update_delete.*

class UpdateDeleteActivity : AppCompatActivity() {

    lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete)

        val user_id = intent.getStringExtra("user_id")
        initViewModel()
        updateUserObserver()
        deleteUserObserver()
        loadUserObser()
        user_id?.let { loadUser(it) }
        btnUplaod.setOnClickListener {
            user_id?.let { it1 -> updateUser(it1) }
        }

        btnDelete.setOnClickListener {
            user_id?.let { it1 -> deleteUser(it1) }
        }

    }

    private fun loadUser(id_user : String) {
        viewModel.getSpecificUser(id_user)
    }

    private fun updateUser(user_id : String) {
        val user = User(user_id, etName.text.toString(), "Male", etEmail.text.toString(), "Active")
        viewModel.updateUser(user_id,user)
    }

    private fun deleteUser(user_id : String) {
        viewModel.deleteUser(user_id)
    }


    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    }

    private fun updateUserObserver(){
        viewModel.updateUserObserberable().observe(this, Observer {
            if (it ==null){
                Toast.makeText(this, "Failed to update user", Toast.LENGTH_LONG).show()
            } else{
                Toast.makeText(this, "Success delete", Toast.LENGTH_LONG).show()
                finish()
            }
        })
    }

    private fun loadUserObser() {
        viewModel.getSpecificUserObserverable().observe(this, Observer {
            if (it ==null){
                Toast.makeText(this, "Failed to laod  user", Toast.LENGTH_LONG).show()
            } else{
                etName.setText(it.data?.name)
                etEmail.setText(it.data?.email)
            }
        })
    }


    private fun deleteUserObserver(){
        viewModel.deleteUserObserver().observe(this, Observer {
            if (it ==null){
                Toast.makeText(this, "Failed to delete new user", Toast.LENGTH_LONG).show()
            } else{
                Toast.makeText(this, "Success delete", Toast.LENGTH_LONG).show()
                finish()
            }
        })
    }
}