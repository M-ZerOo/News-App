package com.melfouly.newsapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.melfouly.newsapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getNews()
        binding.refresher.setOnRefreshListener { getNews() }

    }

    private fun getNews() {
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val newApiService = retrofit.create(ApiService::class.java)

        val cat = intent.getStringExtra("cat")!!
        val code = getSharedPreferences("countries", MODE_PRIVATE)
            .getString("code", "us")!!

        newApiService.getNews(cat, code).enqueue(object : Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                binding.progress.visibility = View.GONE
                val articles = response.body()!!.articles
                val adapter = NewsAdapter(this@MainActivity, articles)
                binding.rv.adapter = adapter
                binding.refresher.isRefreshing = false
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.localizedMessage}", Toast.LENGTH_LONG)
                    .show()
            }

        })
    }
}