package com.example.telasoncln

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.autG.oncln.HomeActivity
import com.autG.oncln.R
import com.autG.oncln.databinding.ActivityHomeBinding
import com.autG.oncln.databinding.ActivityLoginBinding
import com.autG.oncln.services.NavigationHost

class Login : Fragment() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEntrar.setOnClickListener {
            (activity as NavigationHost).navigateTo(HomeActivity(),addToBackStack = true)
        }

    }
}