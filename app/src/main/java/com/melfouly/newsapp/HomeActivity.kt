package com.melfouly.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.melfouly.newsapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            business.setOnClickListener { getCategory("business") }
            entertainment.setOnClickListener { getCategory("entertainment") }
            general.setOnClickListener { getCategory("general") }
            health.setOnClickListener { getCategory("health") }
            science.setOnClickListener { getCategory("science") }
            sports.setOnClickListener { getCategory("sports") }
            tech.setOnClickListener { getCategory("technology") }
        }
    }

    private fun getCategory(cat: String) {
        val i = Intent(this, MainActivity::class.java)
            .putExtra("cat", cat)
        startActivity(i)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val i = Intent(this, CountryActivity::class.java)
        startActivity(i)
        return super.onOptionsItemSelected(item)
    }
}