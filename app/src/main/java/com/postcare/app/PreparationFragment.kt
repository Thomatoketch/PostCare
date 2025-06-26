package com.postcare.app

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class PreparationFragment : Fragment(R.layout.fragment_preparation) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Clique vers l'écran du questionnaire
        val link = view.findViewById<TextView>(R.id.go_to_questionnaire)
        link.setOnClickListener {
            findNavController().navigate(R.id.action_preparation_to_questionnaire)
        }

        // Références aux boutons ronds cochables (RadioButtons)
        val checkEtape2 = view.findViewById<RadioButton>(R.id.checkbox_etape2)
        val checkEtape3 = view.findViewById<RadioButton>(R.id.checkbox_etape3)
        val checkEtape4 = view.findViewById<RadioButton>(R.id.checkbox_etape4)
        val checkEtape5 = view.findViewById<RadioButton>(R.id.checkbox_etape5)

        // TODO : Enregistrements ou actions si besoin (SharedPreferences, etc.)
    }
}
