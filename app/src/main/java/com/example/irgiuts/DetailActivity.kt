package com.example.irgiuts

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.irgiuts.databinding.ActivityDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nama = intent.getStringExtra("nama")

        val api = ApiConfig.getApiService().getDetailUser(nama!!)
        api.enqueue(object : Callback<UsersItem> {
            override fun onResponse(call: Call<UsersItem>, response: Response<UsersItem>) {
                if (response.isSuccessful){
                    val res = response.body()

                    if (res != null) {
                        Glide.with(this@DetailActivity)
                            .load(res.avatarUrl)
                            .into(binding.fotoProfile)
                    }

                    binding.namaProfile.text = res?.login
                    binding.bioProfile.text = res?.bio ?: "Bio belum diisi"
                }
            }

            override fun onFailure(call: Call<UsersItem>, t: Throwable) {
                Log.e("Detail", t.message.toString())
            }

        })
    }
}