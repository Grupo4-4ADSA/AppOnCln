package com.autG.oncln.menus

import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.autG.oncln.*
import com.autG.oncln.AgendamentosActivity
import com.autG.oncln.CadastrarMenuActivity
import com.autG.oncln.HomeActivity
import com.autG.oncln.SalasActivity
import com.autG.oncln.dataClass.MenuData
import com.autG.oncln.databinding.ComponentMenuLateralBinding
import com.autG.oncln.services.NavigationHost
import com.squareup.picasso.Picasso

class NavigationBar : Fragment() {

    private lateinit var binding: ComponentMenuLateralBinding
    private lateinit var containerFragment: ViewGroup
    lateinit var data: MenuData

    companion object{
        fun newIntance() = NavigationBar()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ComponentMenuLateralBinding.inflate(inflater, container, false)
        if (container != null) {
            containerFragment = container
        }
        TransitionManager.beginDelayedTransition(container, Slide(Gravity.RIGHT).setDuration(300))

        val data = arguments?.getSerializable("screen") as MenuData
        binding.navigationMenuView?.setCheckedItem(filter(data))

        Picasso.get().load("http://servidordb.ddns.net:9651/profile/SteveProfile.png").into(binding.profileImage);

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewVoid.setOnClickListener {
            (activity as NavigationHost).menuAction()
        }

        binding.navigationMenuView?.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.layout_home -> {
                    (activity as NavigationHost).navigateTo(
                        HomeActivity(), addToBackStack = true,
                        R.layout.activity_home
                    )
                    true
                }
                R.id.layout_salas -> {
                    (activity as NavigationHost).navigateTo(
                        SalasActivity(), addToBackStack = true,
                        R.layout.activity_salas
                    )
                    true
                }
                R.id.layout_equipments -> {
                    (activity as NavigationHost).navigateTo(
                        CadastrarEquipamentoActivity(), addToBackStack = true,
                        R.layout.activity_equipamentos
                    )
                    true
                }
                R.id.layout_agendar -> {
                    (activity as NavigationHost).navigateTo(
                        AgendamentosActivity(), addToBackStack = true,
                        R.layout.activity_cadastrar_agendamento
                    )
                    true
                }
                R.id.layout_cadastrar -> {
                    (activity as NavigationHost).navigateTo(
                        CadastrarMenuActivity(), addToBackStack = true,
                        R.layout.activity_cadastro_menu
                    )
                    true
                }
                else -> false
            }
        }

    }

    override fun onPause() {
        super.onPause()
        TransitionManager.beginDelayedTransition(
            containerFragment,
            Slide(Gravity.RIGHT).setDuration(500)
        )
    }

    fun filter(item: MenuData): Int {
        return when (item.setPage) {
            R.layout.activity_home -> {
                R.id.layout_home
            }
            R.layout.activity_salas -> {
                R.id.layout_salas
            }
            R.layout.activity_equipamentos -> {
                R.id.layout_equipments
            }
            R.layout.activity_cadastrar_agendamento -> {
                R.id.layout_agendar
            }
            R.layout.activity_cadastro_menu -> {
                R.id.layout_cadastrar
            }
            else -> item.setBackLayout!!
        }

    }
}