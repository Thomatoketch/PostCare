package com.postcare.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.postcare.app.databinding.HomepageBinding

class HomePage : AppCompatActivity() {

    private lateinit var binding: HomepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // On inflate le layout res/layout/homepage.xml
        binding = HomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Clic sur Médecin
        // Dans onCreate de HomePage
        binding.cardMedecin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("USER_TYPE", "MED")
            startActivity(intent)
        }

        // Clic sur Patient
        binding.cardPatient.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("USER_TYPE", "PAT")
            startActivity(intent)
        }
    }
}