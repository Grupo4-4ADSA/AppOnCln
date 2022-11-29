package com.autG.oncln

import android.content.Context
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import com.autG.oncln.databinding.ActivityHomeBinding
import com.autG.oncln.services.NavigationHost


internal class HomeActivity : Fragment() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityHomeBinding.inflate(inflater, container, false)

        binding.includeText.textTitulo.text = getText(R.string.title_home)

        //btn agendamento
        binding.btnAgendamento.buttonBorder.text = getText(R.string.title_input_scheduling)
        binding.btnAgendamento.buttonBorder.setCompoundDrawablesWithIntrinsicBounds(
            com.autG.oncln.R.drawable.ic_schedule, 0, 0, 0
        )

        //btn cadastrar ou editar
        binding.btnCadastrar.buttonBorder.text = getText(R.string.title_register_edit)
        binding.btnCadastrar.buttonBorder.setCompoundDrawablesWithIntrinsicBounds(
            com.autG.oncln.R.drawable.ic_register, 0, 0, 0
        )

        //btn consumo por equipamento
        binding.btnConsumo.buttonBorder.text = getText(R.string.title_input_consuming_equipments)
        binding.btnConsumo.buttonBorder.setCompoundDrawablesWithIntrinsicBounds(
            com.autG.oncln.R.drawable.ic_grafico, 0, 0, 0
        )

        //btn salas
        binding.btnSalas.buttonBorder.text = getText(R.string.title_rooms)
        binding.btnSalas.buttonBorder.setTextColor(
            getColor(
                requireContext(), R.color.white
            )
        )
        binding.btnSalas.buttonBorder.clipToOutline
        binding.btnSalas.buttonBorder.invalidateOutline()

        binding.btnSalas.buttonBorder.setCompoundDrawablesWithIntrinsicBounds(
            com.autG.oncln.R.drawable.ic_power, 0, 0, 0
        )
        binding.btnSalas.buttonBorder.setBackgroundColor(
            getColor(
                requireContext(),
                com.autG.oncln.R.color.blue_secondary
            )
        )

        TransitionManager.beginDelayedTransition(container, Fade())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            if (context?.isDarkThemeOn() == true){
                textModoDark.text = "Light mode"
                switchMode.isChecked = true
            }else{
                textModoDark.text = "Dark mode"
            }
            switchMode.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    (activity as NavigationHost).menuAction()
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    (activity as NavigationHost).menuAction()

                }
            }
            btnAgendamento.buttonBorder.setOnClickListener {
                (activity as NavigationHost).navigateTo(SchedulesActivity(),addToBackStack = true,
                    R.layout.activity_schedules)
            }
            btnCadastrar.buttonBorder.setOnClickListener {
                (activity as NavigationHost).navigateTo(RegistrationMenuActivity(),addToBackStack = true,
                    R.layout.activity_registration_menu)
            }
            btnConsumo.buttonBorder.setOnClickListener {
                (activity as NavigationHost).navigateTo(EquipmentConsumptionActivity(),addToBackStack = true,
                    R.layout.activity_equipment_consumption)
            }
            btnSalas.buttonBorder.setOnClickListener {
                (activity as NavigationHost).navigateTo(RoomsActivity(),addToBackStack = true,
                    R.layout.activity_rooms)
            }
        }
    }

    fun Context.isDarkThemeOn(): Boolean{
        return resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == UI_MODE_NIGHT_YES
    }
}