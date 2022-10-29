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
import com.google.android.material.navigation.NavigationBarView

class NavBarBottom : Fragment() {

    private lateinit var binding: NavbarBottomBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NavbarBottomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NavigationBarView.OnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.layout_home -> {
                    (activity as NavigationHost).navigateTo(HomeActivity(), addToBackStack = true)
                    true
                }
                R.id.layout_salas -> {
                    (activity as NavigationHost).navigateTo(CadastroSala(), addToBackStack = true)
                    true
                }
                else -> false
            }
        }
    }
}