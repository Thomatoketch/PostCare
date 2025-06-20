package com.example.myapplication.com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class PostOpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_op)

        // Récupère le rôle
        val role = intent.getStringExtra("USER_TYPE")

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
    }
}