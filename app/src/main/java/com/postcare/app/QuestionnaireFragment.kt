package com.postcare.app.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.postcare.app.R
import com.google.android.material.button.MaterialButton

class QuestionnaireFragment : Fragment(R.layout.fragment_questionnaire) {

    private lateinit var buttonYes: MaterialButton
    private lateinit var buttonNo: MaterialButton
    private lateinit var buttonNext: MaterialButton

    private var userAnswer: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Références aux boutons
        buttonYes = view.findViewById(R.id.button_yes)
        buttonNo = view.findViewById(R.id.button_no)
        buttonNext = view.findViewById(R.id.button_next)

        // Gestion des clics OUI
        buttonYes.setOnClickListener {
            userAnswer = "OUI"
            highlightSelection(buttonYes, buttonNo)
        }

        // Gestion des clics NON
        buttonNo.setOnClickListener {
            userAnswer = "NON"
            highlightSelection(buttonNo, buttonYes)
        }

        // Bouton "Suivant"
        buttonNext.setOnClickListener {
            if (userAnswer != null) {
                // TODO : Traiter la réponse ou naviguer
                // findNavController().navigate(R.id.action_questionnaire_to_nextFragment)
            } else {
                // Afficher un message à l'utilisateur s'il n'a rien sélectionné
                // (optionnel) Toast.makeText(requireContext(), "Veuillez sélectionner une réponse", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun highlightSelection(selected: MaterialButton, other: MaterialButton) {
        selected.setBackgroundColor(resources.getColor(R.color.teal_200, null))
        selected.setTextColor(resources.getColor(android.R.color.white, null))

        other.setBackgroundResource(R.drawable.bg_button_green_outline)
        other.setTextColor(resources.getColor(R.color.teal_700, null))
    }
}
