package com.postcare.app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetailPatientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_patient)
        val navView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val header = findViewById<View>(R.id.header)
        navView.setBackgroundColor(ContextCompat.getColor(this, R.color.postcare_blue))
        header.setBackgroundColor(ContextCompat.getColor(this, R.color.postcare_blue))

        val backArrow = findViewById<View>(R.id.back_arrow)
        backArrow.setOnClickListener {
            finish()
        }
    }
}