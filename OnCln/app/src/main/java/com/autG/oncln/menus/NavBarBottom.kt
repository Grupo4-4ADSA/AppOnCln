package com.autG.oncln.menus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.autG.oncln.*
import com.autG.oncln.HomeActivity
import com.autG.oncln.dataClass.MenuData
import com.autG.oncln.databinding.NavbarBottomBinding
import com.autG.oncln.services.NavigationHost

class NavBarBottom : Fragment() {

    lateinit var binding: NavbarBottomBinding
    lateinit var data: MenuData

    companion object{
        fun newIntance() = NavBarBottom()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = NavbarBottomBinding.inflate(inflater, container, false)

        val data = arguments?.getSerializable("screen") as MenuData
        binding.bottomNavigation.selectedItemId = filter(data)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.layout_home -> {
                    (activity as NavigationHost).navigateTo(HomeActivity(), addToBackStack = true,
                    R.layout.activity_home)
                    true
                }
                R.id.layout_salas -> {
                    (activity as NavigationHost).navigateTo(SalasActivity(), addToBackStack = true,
                    R.layout.activity_salas)
                    true
                }
                R.id.layout_equipments -> {
                    (activity as NavigationHost).navigateTo(EquipamentoActivity(), addToBackStack = true,
                    R.layout.activity_equipamento)
                    true
                }
                R.id.layout_agendar -> {
                    (activity as NavigationHost).navigateTo(Agendamento(), addToBackStack = true,
                    R.layout.activity_cadastro_agendamento)
                    true
                }
                R.id.layout_cadastrar -> {
                    (activity as NavigationHost).navigateTo(CadastrarMenuActivity(), addToBackStack = true,
                    R.layout.activity_cadastrar_menu)
                    true
                }
                else -> false
            }
        }
    }

    fun filter(item:MenuData): Int{
        return when (item.setPage) {
             R.layout.activity_home -> {
                R.id.layout_home
            }
             R.layout.activity_salas -> {
                 R.id.layout_salas
            }
             R.layout.activity_equipamento -> {
                R.id.layout_equipments
            }
             R.layout.activity_cadastro_agendamento -> {
                R.id.layout_agendar
            }
             R.layout.activity_cadastrar_menu -> {
                R.id.layout_cadastrar
            }
            else -> item.setBackLayout!!
        }
    }
}