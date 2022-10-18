package com.autG.oncln

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.autG.oncln.databinding.ActivityCadastrarMenuBinding
import com.autG.oncln.databinding.ActivityLoginBinding
import com.autG.oncln.services.NavigationHost

internal class Login : Fragment() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnEntrar.setOnClickListener {
                (activity as NavigationHost).navigateTo(HomeActivity(),addToBackStack = false)
            }
        }

    }
}