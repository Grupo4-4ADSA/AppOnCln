package com.autG.oncln

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.autG.oncln.databinding.ActivityHomeBinding
import com.autG.oncln.services.NavigationHost

internal class HomeActivity: Fragment() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnAgendamento.setOnClickListener {
                //(activity as NavigationHost).navigateTo(Agendamento(),addToBackStack = true)
            }
        }
    }
}