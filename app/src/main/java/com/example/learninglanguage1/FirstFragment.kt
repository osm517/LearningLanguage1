package com.example.learninglanguage1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class FirstFragment : Fragment(R.layout.fragment_first) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnGoToTestScreen = requireView().findViewById<Button>(R.id.btnGoToTestScreen)
        val btnGoToAddScreen = requireView().findViewById<Button>(R.id.btnGoToAddScreen)
        val btnGoToDisplayScreen = requireView().findViewById<Button>(R.id.btnGoToDisplayScreen)
        val btnGoToSettingsScreen = requireView().findViewById<Button>(R.id.btnGoToSettingsScreen)

        btnGoToTestScreen.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_testFragment)
        }

        btnGoToAddScreen.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_addFragment)
        }

        btnGoToDisplayScreen.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_displayFragment)
        }

        btnGoToSettingsScreen.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_settingsFragment)
        }
    }

}