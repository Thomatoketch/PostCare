package com.postcare.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePageMedecin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage_medecin)

        // Récupère le rôle
        val role = intent.getStringExtra("USER_TYPE")
        val op = intent.getStringExtra("OP_TYPE")

        // Views
        val navView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val header = findViewById<View>(R.id.header)

        if (role == "MED") {
            // Médecin
            navView.setBackgroundColor(ContextCompat.getColor(this, R.color.postcare_blue))
            header.setBackgroundColor(ContextCompat.getColor(this, R.color.postcare_blue))
        } else {
            // Patient
            navView.setBackgroundColor(ContextCompat.getColor(this, R.color.postcare_green))
            header.setBackgroundColor(ContextCompat.getColor(this, R.color.postcare_green))
        }



        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val nextIntent = Intent(this, HomePageMedecin::class.java)
                    nextIntent.putExtra("USER_TYPE", role)
                    startActivity(nextIntent)
                    true
                }

                R.id.nav_suivie -> {
                    val nextIntent = Intent(this, PostOpActivity::class.java)
                    nextIntent.putExtra("USER_TYPE", role)
                    startActivity(nextIntent)
                    true
                }
                // autres items...
                else -> false
            }
        }
    }
}