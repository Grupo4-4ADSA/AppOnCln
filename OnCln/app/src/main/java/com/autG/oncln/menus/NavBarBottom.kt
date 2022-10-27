package com.autG.oncln.menus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import com.autG.oncln.*
import com.autG.oncln.HomeActivity
import com.autG.oncln.databinding.NavbarBottomBinding
import com.autG.oncln.services.NavigationHost

class NavBarBottom : Fragment() {

    private lateinit var binding: NavbarBottomBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NavbarBottomBinding.inflate(inflater, container, false)
        binding.txtHome.setTextColor(getColor(requireContext(), R.color.blue_secundary))
        binding.imgHome.setColorFilter(getColor(requireContext(), R.color.blue_secundary))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            layoutHome.setOnClickListener {
                (activity as NavigationHost).navigateTo(HomeActivity(), addToBackStack = true)
            }
            imgSalas.setOnClickListener {
                (activity as NavigationHost).navigateTo(CadastroSala(), addToBackStack = true)
            }
        }

    }
}