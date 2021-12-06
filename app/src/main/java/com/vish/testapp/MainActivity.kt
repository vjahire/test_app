package com.vish.testapp

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vish.testapp.adapters.UsersAdapter
import com.vish.testapp.api.RetrofitClient
import com.vish.testapp.databinding.ActivityMainBinding
import com.vish.testapp.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private lateinit var usersAdapter: UsersAdapter
    private var users = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Log.i(TAG, "onCreate: ")

        //Init
        binding.btnStartActivity.setOnClickListener {
            startActivity(Intent(this@MainActivity, SecondActivity::class.java))
        }
        initRecyclerView()

        CoroutineScope(IO).launch {
//            withContext(Main)
            val users = RetrofitClient.api.getUsers()
            if (users.isSuccessful) {
                Log.i(TAG, "onCreate: ${users.body()}")
                users.body()?.let {
                    this@MainActivity.users.clear()
                    this@MainActivity.users.addAll(it)
                    runOnUiThread {
                        usersAdapter.notifyItemRangeInserted(0, it.size)
                    }
                }

            } else
                Log.i(TAG, "onCreate: Something went wrong ${users.message()}")

        }

    }

    private fun initRecyclerView() {
        usersAdapter = UsersAdapter(users)
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = usersAdapter
        }

    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart: ")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: ")
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.i(TAG, "onSaveInstanceState: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
    }
}