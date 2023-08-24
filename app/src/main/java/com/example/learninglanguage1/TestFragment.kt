package com.example.learninglanguage1

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class TestFragment : Fragment(R.layout.fragment_test){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBackTest = requireView().findViewById<Button>(R.id.btnBackTest)

        btnBackTest.setOnClickListener {
            findNavController().navigate(R.id.action_testFragment_to_firstFragment)
        }

        val btnViewSavedCards = requireView().findViewById<Button>(R.id.btnViewSavedCards)

        btnViewSavedCards.setOnClickListener {
            findNavController().navigate(R.id.action_testFragment_to_viewSavedCardsFragment)
        }
    }
}