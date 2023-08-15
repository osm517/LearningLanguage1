package com.example.learninglanguage1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBackSettings = requireView().findViewById<Button>(R.id.btnBackSettings)

        btnBackSettings.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_firstFragment)
        }
    }

}