package com.postcare.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetailPatientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_patient)

        val role = intent.getStringExtra("USER_TYPE")

        
        val navView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val header = findViewById<View>(R.id.header)
        navView.setBackgroundColor(ContextCompat.getColor(this, R.color.postcare_blue))
        header.setBackgroundColor(ContextCompat.getColor(this, R.color.postcare_blue))

        val backArrow = findViewById<View>(R.id.back_arrow)
        backArrow.setOnClickListener {
            finish()
        }

        val button_pillulier = findViewById<ImageView>(R.id.button_pillulier)
        button_pillulier.setOnClickListener {
            val intent = Intent(this, PillBoxActivity::class.java)
            intent.putExtra("USER_TYPE", role)
            startActivity(intent)
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
                    val nextIntent = Intent(this, PostOpActivity_medecin::class.java)
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