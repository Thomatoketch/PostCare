package com.postcare.app

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class PostOpActivity_patient_image : AppCompatActivity() {

    private lateinit var pickImageLauncher: ActivityResultLauncher<String>
    private lateinit var classifier: ImageClassifier

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_send)

        classifier = ImageClassifier(this)

        // Récupère le rôle
        val role = intent.getStringExtra("USER_TYPE")
        val op = intent.getStringExtra("OP_TYPE")

        // Views
        val navView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val header = findViewById<View>(R.id.header)
        navView.setBackgroundColor(ContextCompat.getColor(this, R.color.postcare_green))
        header.setBackgroundColor(ContextCompat.getColor(this, R.color.postcare_green))


        val imageView = findViewById<ImageView>(R.id.iv_selected_image)
        val resultView = findViewById<TextView>(R.id.tv_prediction_result)

        pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                imageView.setImageURI(it)
                imageView.visibility = View.VISIBLE
                val result = try {
                    contentResolver.openInputStream(it)?.use { stream ->
                        val bitmap = BitmapFactory.decodeStream(stream)
                        classifier.classifyReadable(bitmap)
                    } ?: "Analyse impossible"
                } catch (e: Exception) {
                    Log.e("PostOpActivity", "Erreur pendant l'analyse de l'image", e)
                    e.printStackTrace()
                    "Erreur lors de l'analyse"
                }
                resultView.text = result
                resultView.visibility = View.VISIBLE
            }
        }
        val predictButton = findViewById<Button>(R.id.btn_predict)
        predictButton.setOnClickListener {
            pickImageLauncher.launch("image/*")
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
                    val nextIntent = if (op == "PRE"){
                        Intent(this, PreOpActivity_patient::class.java)
                    } else {
                        Intent(this, PostOpActivity_patient_image::class.java)
                    }
                    nextIntent.putExtra("USER_TYPE", role)
                    nextIntent.putExtra("OP_TYPE", op)
                    startActivity(nextIntent)
                    true
                }
                // autres items...
                else -> false
            }
        }
    }
}