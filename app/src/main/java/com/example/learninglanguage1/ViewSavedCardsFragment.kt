package com.example.learninglanguage1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class ViewSavedCardsFragment : Fragment(R.layout.fragment_view_saved_cards){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBackViewSavedCards = requireView().findViewById<Button>(R.id.btnBackViewSavedCards)

        btnBackViewSavedCards.setOnClickListener {
            findNavController().navigate(R.id.action_viewSavedCardsFragment_to_testFragment)
        }
    }
}