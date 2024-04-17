package com.example.irgiuts

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.irgiuts.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val adapter = MainAdapter()

        val api = ApiConfig.getApiService().getUsers()
        api.enqueue(object : Callback<GithubRes> {
            override fun onResponse(call: Call<GithubRes>, response: Response<GithubRes>) {
                binding.rv.let {
                    it.layoutManager = LinearLayoutManager(this@MainActivity)
                    it.adapter = adapter
                    adapter.submitList(response.body()!!.items)
                }
            }

            override fun onFailure(call: Call<GithubRes>, t: Throwable) {
                Log.e("Main", t.message.toString())
            }
        })
    }
}