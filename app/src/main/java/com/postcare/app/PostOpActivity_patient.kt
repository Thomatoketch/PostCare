package com.postcare.app

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class PostOpActivity_patient : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_patient)

        // Récupère le rôle
        val role = intent.getStringExtra("USER_TYPE")
        val op = intent.getStringExtra("OP_TYPE")

        // Views
        val navView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val header = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.header)
        navView.setBackgroundColor(ContextCompat.getColor(this, R.color.postcare_green))
        header.setBackgroundColor(ContextCompat.getColor(this, R.color.postcare_green))

        val button_symptome = findViewById<ImageView>(R.id.button_symptome)
        button_symptome.setOnClickListener {
            val intent = Intent(this, PostOpActivity_patient_image::class.java)
            intent.putExtra("USER_TYPE", role)
            intent.putExtra("OP_TYPE", op)
            startActivity(intent)
            finish()
        }


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