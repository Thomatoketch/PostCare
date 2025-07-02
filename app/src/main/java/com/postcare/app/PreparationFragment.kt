package com.postcare.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.postcare.app.ui.QuestionnaireFragment

class PreparationFragment : Fragment(R.layout.fragment_preparation) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Clique vers l'écran du questionnaire
        val link = view.findViewById<TextView>(R.id.go_to_questionnaire)
        link.setOnClickListener {
            findNavController().navigate(R.id.fragment_questionnaire)
        }

        // Références aux boutons ronds cochables (RadioButtons)
        view.findViewById<RadioButton>(R.id.checkbox_etape2)
        view.findViewById<RadioButton>(R.id.checkbox_etape3)
        view.findViewById<RadioButton>(R.id.checkbox_etape4)
        view.findViewById<RadioButton>(R.id.checkbox_etape5)

        // TODO : Enregistrements ou actions si besoin (SharedPreferences, etc.)
    }
}
