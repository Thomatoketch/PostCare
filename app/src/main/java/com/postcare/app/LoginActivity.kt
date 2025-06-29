package com.postcare.app

import android.os.Bundle
import android.graphics.Color
import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import android.widget.EditText
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val role = intent.getStringExtra("USER_TYPE")

        // Récupère le rôle
        val header     = findViewById<View>(R.id.header)
        val icon       = findViewById<ImageView>(R.id.icon_role)
        val title      = findViewById<TextView>(R.id.title_role)
        val btnLogin   = findViewById<Button>(R.id.btn_login)
        val editTextUsername = findViewById<EditText>(R.id.field_username)
        val editTextPassword = findViewById<EditText>(R.id.field_password)

        // Personnalisation selon le rôle
        if (role == "MED") {
            header.setBackgroundColor(ContextCompat.getColor(this, R.color.postcare_blue))
            icon.setImageResource(R.drawable.logo_medecin)
            title.text = "Vous êtes médecin"
            btnLogin.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.postcare_blue)))
            btnLogin.setTextColor(Color.WHITE)
        } else {
            header.setBackgroundColor(ContextCompat.getColor(this, R.color.postcare_green))
            icon.setImageResource(R.drawable.logo_patient)
            title.text = "Vous êtes un patient"
            btnLogin.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.postcare_green)))
            btnLogin.setTextColor(Color.WHITE)
        }

        // Action de connexion
        btnLogin.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()

            if (username != "admin" || password != "admin") {
                Toast.makeText(this, "Connexion réussie", Toast.LENGTH_SHORT).show()

                val nextIntent = if (role == "MED") {
                    Intent(this, HomePageMedecin::class.java)
                } else {
                    Intent(this, HomePagePatient::class.java)
                }

                nextIntent.putExtra("USER_TYPE", role)
                nextIntent.putExtra("OP_TYPE", "PRE")
                startActivity(nextIntent)
                finish()

            } else if (username == "Thomas") {
                Toast.makeText(this, "Connexion réussie", Toast.LENGTH_SHORT).show()

                val nextIntent = if (role == "MED") {
                    Intent(this, HomePageMedecin::class.java)
                } else {
                    Intent(this, HomePagePatient::class.java)
                }

                nextIntent.putExtra("OP_TYPE", "PRE")
                nextIntent.putExtra("USER_TYPE", role)
                startActivity(nextIntent)
                finish()

            } else if (username == "Lea") {
                Toast.makeText(this, "Connexion réussie", Toast.LENGTH_SHORT).show()
                intent.putExtra("OP_TYPE", "POST")

                val nextIntent = if (role == "MED") {
                    Intent(this, PostOpActivity::class.java)
                } else {
                    Intent(this, HomePagePatient::class.java)
                }

                nextIntent.putExtra("OP_TYPE", "POST")
                nextIntent.putExtra("USER_TYPE", role)
                startActivity(nextIntent)
                finish()

            } else {
                Toast.makeText(this, "Identifiants incorrects", Toast.LENGTH_SHORT).show()
            }
        }
    }
}