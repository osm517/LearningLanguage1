package com.example.learninglanguage1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class DisplayFragment : Fragment(R.layout.fragment_display) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBackDisplay = requireView().findViewById<Button>(R.id.btnBackDisplay)

        btnBackDisplay.setOnClickListener {
            findNavController().navigate(R.id.action_displayFragment_to_firstFragment)
        }
    }

}