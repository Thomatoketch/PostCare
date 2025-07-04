package com.postcare.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import android.widget.TextView

class PreOpActivity_patient : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_preparation)

        // Récupère le rôle
        val role = intent.getStringExtra("USER_TYPE")
        val op = intent.getStringExtra("OP_TYPE")

        // Views
        val navView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val header = findViewById<View>(R.id.header)
        navView.setBackgroundColor(ContextCompat.getColor(this, R.color.postcare_green))
        header.setBackgroundColor(ContextCompat.getColor(this, R.color.postcare_green))

        val lienQuestionnaire = findViewById<TextView>(R.id.lien_questionnaire)
        lienQuestionnaire.setOnClickListener {
            val intent = Intent(this, QuestionnaireActivity::class.java)
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
}