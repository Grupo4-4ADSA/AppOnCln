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
import com.autG.oncln.SchedulesActivity
import com.autG.oncln.RegistrationMenuActivity
import com.autG.oncln.HomeActivity
import com.autG.oncln.RoomsActivity
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
                R.id.layout_cadastrar -> {
                    (activity as NavigationHost).navigateTo(
                        RegistrationMenuActivity(), addToBackStack = true,
                        R.layout.activity_registration_menu
                    )
                    true
                }
                R.id.layout_salas -> {
                    (activity as NavigationHost).navigateTo(
                        RoomsActivity(), addToBackStack = true,
                        R.layout.activity_rooms
                    )
                    true
                }
                R.id.layout_agendar -> {
                    (activity as NavigationHost).navigateTo(
                        SchedulesActivity(), addToBackStack = true,
                        R.layout.activity_schedules
                    )
                    true
                }
                R.id.layout_equipments -> {
                    (activity as NavigationHost).navigateTo(
                        EquipmentsActivity(), addToBackStack = true,
                        R.layout.activity_equipments
                    )
                    true
                }
                R.id.layout_consumo_equipments -> {
                    (activity as NavigationHost).navigateTo(
                        EquipmentConsumptionActivity(), addToBackStack = true,
                        R.layout.activity_equipment_consumption
                    )
                    true
                }
                R.id.layout_consumo -> {
                    (activity as NavigationHost).navigateTo(
                        ConsumptionSummaryActivity(), addToBackStack = true,
                        R.layout.activity_consumption_summary
                    )
                    true
                }
                R.id.layout_ajuda -> {
                    (activity as NavigationHost).navigateTo(
                        HelpCenterActivity(), addToBackStack = true,
                        R.layout.activity_help_center
                    )
                    true
                }
                R.id.layout_configuracoes -> {
                    (activity as NavigationHost).navigateTo(
                        SettingsActivity(), addToBackStack = true,
                        R.layout.activity_settings
                    )
                    true
                }
                else -> false
            }
        }
    }

    override fun onPause() {
        super.onPause()

        TransitionManager.beginDelayedTransition(containerFragment, Slide(Gravity.RIGHT).setDuration(300))
    }

    fun filter(item: MenuData): Int {
        return when (item.setPage) {
            R.id.layout_home -> {
                R.layout.activity_home
            }
            R.id.layout_cadastrar -> {
                R.layout.activity_registration_menu
            }
            R.id.layout_salas -> {
                R.layout.activity_rooms
            }
            R.id.layout_agendar -> {
                R.layout.activity_schedules
            }
            R.id.layout_equipments -> {
                R.layout.activity_equipments
            }
            R.id.layout_consumo_equipments -> {
                R.layout.activity_equipment_consumption
            }
            R.id.layout_consumo -> {
                R.layout.activity_consumption_summary
            }
            R.id.layout_ajuda -> {
                R.layout.activity_help_center
            }
            R.id.layout_configuracoes -> {
                R.layout.activity_settings
            }
            else -> item.setBackLayout!!
        }
    }
}