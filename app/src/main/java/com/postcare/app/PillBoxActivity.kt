package com.postcare.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class PillBoxActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pillbox)

        var role = intent.getStringExtra("USER_TYPE")

        val navView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val header = findViewById<View>(R.id.header)

        navView.setBackgroundColor(ContextCompat.getColor(this, R.color.postcare_blue))
        header.setBackgroundColor(ContextCompat.getColor(this, R.color.postcare_blue))


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
