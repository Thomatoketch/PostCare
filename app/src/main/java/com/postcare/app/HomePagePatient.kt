package com.postcare.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePagePatient : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage_patient)

        // Récupère le rôle
        val role = intent.getStringExtra("USER_TYPE")
        val op = intent.getStringExtra("OP_TYPE")

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
                    val nextIntent = if (op == "PRE"){
                        Intent(this, PreOpActivity_patient::class.java)
                    } else {
                        Intent(this, PostOpActivity_patient::class.java)
                    }
                    nextIntent.putExtra("USER_TYPE", role)
                    nextIntent.putExtra("OP_TYPE", op)
                    startActivity(nextIntent)
                    true
                }
                // autres items...
                else -> false
            }
        }
    }
}