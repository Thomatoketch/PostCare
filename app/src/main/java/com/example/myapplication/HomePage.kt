package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.HomepageBinding

class HomePage : AppCompatActivity() {

    private lateinit var binding: HomepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // On inflate le layout res/layout/homepage.xml
        binding = HomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Clic sur Médecin
        binding.cardMedecin.setOnClickListener {
            Toast.makeText(this, "Médecin sélectionné", Toast.LENGTH_SHORT).show()
            // startActivity(Intent(this, MedecinActivity::class.java))
        }

        // Clic sur Patient
        binding.cardPatient.setOnClickListener {
            Toast.makeText(this, "Patient sélectionné", Toast.LENGTH_SHORT).show()
            // startActivity(Intent(this, PatientActivity::class.java))
        }
    }
}