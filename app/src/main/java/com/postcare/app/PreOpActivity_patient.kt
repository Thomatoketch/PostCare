package com.postcare.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PreOpActivity_patient : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_preparation)

        // Récupère le rôle
        val role = intent.getStringExtra("USER_TYPE")

        // Views
        val navView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val header = findViewById<View>(R.id.header)
        navView.setBackgroundColor(ContextCompat.getColor(this, R.color.postcare_green))
        header.setBackgroundColor(ContextCompat.getColor(this, R.color.postcare_green))


        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val nextIntent = Intent(this, HomePagePatient::class.java)
                    nextIntent.putExtra("USER_TYPE", role)
                    startActivity(nextIntent)
                    true
                }
                R.id.nav_suivie -> {
                    val nextIntent = Intent(this, PreOpActivity_patient::class.java)
                    nextIntent.putExtra("USER_TYPE", role)
                    startActivity(nextIntent)
                    true
                }
                // autres items...
                else -> false
            }
        }
    }

    private fun loadPatientsFromJson(): List<Patient> {
        val json: String = assets.open("patients.json").bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<Patient>>() {}.type
        return Gson().fromJson(json, type)
    }
}