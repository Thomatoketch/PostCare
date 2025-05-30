package com.example.myapplication

import android.os.Bundle
import android.graphics.Color
import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Récupère le rôle
        val role = intent.getStringExtra("USER_TYPE")

        // Views
        val header     = findViewById<View>(R.id.header)
        val icon       = findViewById<ImageView>(R.id.icon_role)
        val title      = findViewById<TextView>(R.id.title_role)
        val btnLogin   = findViewById<Button>(R.id.btn_login)

        if (role == "MED") {
            // Médecin
            header.setBackgroundColor(ContextCompat.getColor(this, R.color.postcare_blue))
            icon.setImageResource(R.drawable.logo_medecin)
            title.text = "Vous êtes médecin"
            btnLogin.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.postcare_blue)))
            btnLogin.setTextColor(Color.WHITE)
        } else {
            // Patient
            header.setBackgroundColor(ContextCompat.getColor(this, R.color.postcare_green))
            icon.setImageResource(R.drawable.logo_patient)
            title.text = "Vous êtes un particulier"
            btnLogin.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.postcare_green)))
            btnLogin.setTextColor(Color.WHITE)
        }

        btnLogin.setOnClickListener {
            // ta logique de connexion...
        }
    }
}