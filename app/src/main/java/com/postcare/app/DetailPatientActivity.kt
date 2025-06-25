package com.postcare.app

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailPatientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_patient)

        val name = intent.getStringExtra("name")
        val operation = intent.getStringExtra("operationDetails")
        val status = intent.getStringExtra("status")

        // Tu peux afficher ces infos ou les utiliser pour charger une interface personnalisée
        // Par exemple :
        // findViewById<TextView>(R.id.tvTitle).text = name
    }
}