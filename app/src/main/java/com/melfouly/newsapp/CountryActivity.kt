package com.melfouly.newsapp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.melfouly.newsapp.databinding.ActivityCountryBinding

class CountryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = getSharedPreferences("countries", MODE_PRIVATE)
        val checkedCode = pref.getString("code", "us")
        when(checkedCode) {
            "de" -> binding.radio.check(R.id.de)
            "fr" -> binding.radio.check(R.id.fr)
            "us" -> binding.radio.check(R.id.us)
            "gb" -> binding.radio.check(R.id.gb)
        }

        binding.radio.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.de -> getCode("de")
                R.id.fr -> getCode("fr")
                R.id.us -> getCode("us")
                R.id.gb -> getCode("gb")
            }
        }
    }

    private fun getCode(code: String) {
        val pref = getSharedPreferences("countries", MODE_PRIVATE).edit()
            .putString("code", code).apply()
        Toast.makeText(this, "Country changed successfully", Toast.LENGTH_SHORT).show()
    }
}