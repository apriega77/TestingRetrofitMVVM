package com.apriega77.moviedb


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.apriega77.moviedb.MainViewModel
import com.apriega77.moviedb.R
import com.apriega77.moviedb.adapter.UserAdapter
import com.apriega77.moviedb.data.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), UserAdapter.onItemClickListener {

    private lateinit var userAdapter: UserAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerview()
        initViewModel()

        btnAddUser.setOnClickListener {
            Intent(this, PostActivity::class.java).also {
                startActivity(it)
            }
        }

        searchUser()
    }

    private fun searchUser() {
        btnSearch.setOnClickListener {
            if (!TextUtils.isEmpty(etSearch.text.toString())) {
                viewModel.searchUser(etSearch.text.toString())
            }else{
                viewModel.getUserList()
            }
        }
    }

    private fun initRecyclerview() {

        rvUser.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL )
            addItemDecoration(decoration)
            userAdapter = UserAdapter(this@MainActivity)
            adapter = userAdapter

        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getUserListObserverable().observe(this, Observer {
            if (it == null){
                Toast.makeText(this, "No result found", Toast.LENGTH_LONG).show()
            }else{
                userAdapter.userList = it.data.toMutableList()
                userAdapter.notifyDataSetChanged()
            }
        })

        viewModel.getUserList()
    }

    override fun onItemClick(user: User) {
       Intent(this,UpdateDeleteActivity::class.java).also {
           it.putExtra("user_id", user.id)
           startActivityForResult(it,1000)
       }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1000) {
            viewModel.getUserList()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


}