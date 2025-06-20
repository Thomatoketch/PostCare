package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SuiviActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var patientAdapter: PatientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_op)

        recyclerView = findViewById(R.id.recyclerViewPatients)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val patients = listOf(
            Patient("Yannick BARBE", "Opération du foie le 30 mai 2024 à 11H", "EN COURS"),
            Patient("Thomas RAYMOND", "Opération des yeux le 1er juin 2024 à 9h", "COMPLET"),
            Patient("Marie DUPONT", "Opération du cœur le 15 juin 2024 à 14h", "À VÉRIFIER")
        )

        patientAdapter = PatientAdapter(patients)
        recyclerView.adapter = patientAdapter
    }
}
