package com.postcare.app.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.postcare.app.R
import com.google.android.material.button.MaterialButton

class QuestionnaireFragment : Fragment(R.layout.fragment_questionnaire) {

    private val viewModel: QuestionnaireViewModel by viewModels()

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

        // Restaure la réponse sauvegardée, le cas échéant
        viewModel.answer.value?.let {
            userAnswer = it
            if (it == "OUI") {
                highlightSelection(buttonYes, buttonNo)
            } else if (it == "NON") {
                highlightSelection(buttonNo, buttonYes)
            }
        }

        // Gestion des clics OUI
        buttonYes.setOnClickListener {
            userAnswer = "OUI"
            viewModel.setAnswer("OUI")
            highlightSelection(buttonYes, buttonNo)
        }

        // Gestion des clics NON
        buttonNo.setOnClickListener {
            userAnswer = "NON"
            viewModel.setAnswer("NON")
            highlightSelection(buttonNo, buttonYes)
        }

        // Bouton "Suivant"
        buttonNext.setOnClickListener {
            val answer = viewModel.answer.value
            if (answer != null) {
                viewModel.saveAnswer()
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Veuillez sélectionner une réponse", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun highlightSelection(selected: MaterialButton, other: MaterialButton) {
        selected.setBackgroundColor(resources.getColor(R.color.teal_200, null))
        selected.setTextColor(resources.getColor(android.R.color.white, null))

        other.setBackgroundResource(R.drawable.circle_green)
        other.setTextColor(resources.getColor(R.color.teal_700, null))
    }
}
