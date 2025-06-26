package com.postcare.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HomePageMedecin : AppCompatActivity() {
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
            val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPatients)
            recyclerView.layoutManager = LinearLayoutManager(this)

            val patients = loadPatientsFromJson()

            val patientAdapter = PatientAdapter(patients) { patient ->
                val intent = Intent(this, DetailPatientActivity::class.java)
                intent.putExtra("name", patient.name)
                intent.putExtra("operationDetails", patient.operationDetails)
                intent.putExtra("status", patient.status)
                startActivity(intent)
            }
            recyclerView.adapter = patientAdapter
        } else {
            // Patient
            navView.setBackgroundColor(ContextCompat.getColor(this, R.color.postcare_green))
            header.setBackgroundColor(ContextCompat.getColor(this, R.color.postcare_green))
        }
    }

    private fun loadPatientsFromJson(): List<Patient> {
        val json: String = assets.open("patients.json").bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<Patient>>() {}.type
        return Gson().fromJson(json, type)
    }
}