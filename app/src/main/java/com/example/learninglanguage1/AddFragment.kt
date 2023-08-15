package com.example.learninglanguage1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class AddFragment : Fragment(R.layout.fragment_add) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnAddCards = requireView().findViewById<Button>(R.id.btnAddCards)
        val btnBackAdd = requireView().findViewById<Button>(R.id.btnBackAdd)

        btnAddCards.setOnClickListener {
            findNavController().navigate(R.id.action_addFragment_to_addTextFragment)
        }

        btnBackAdd.setOnClickListener {
            findNavController().navigate(R.id.action_addFragment_to_firstFragment)
        }
    }

}