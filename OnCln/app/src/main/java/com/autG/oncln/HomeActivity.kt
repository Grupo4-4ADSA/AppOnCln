package com.autG.oncln

import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding.btnAgendamento.buttonBorder.setTextColor(
            getColor(
                requireContext(),
                com.autG.oncln.R.color.black
            )
        )

        //btn cadastrar ou editar
        binding.btnCadastrar.buttonBorder.text = getText(R.string.title_register_edit)
        binding.btnCadastrar.buttonBorder.setCompoundDrawablesWithIntrinsicBounds(
            com.autG.oncln.R.drawable.ic_register, 0, 0, 0
        )
        binding.btnCadastrar.buttonBorder.setTextColor(
            getColor(
                requireContext(),
                com.autG.oncln.R.color.black
            )
        )

        //btn consumo por equipamento
        binding.btnConsumo.buttonBorder.text = getText(R.string.title_input_consuming_equipments)
        binding.btnConsumo.buttonBorder.setCompoundDrawablesWithIntrinsicBounds(
            com.autG.oncln.R.drawable.ic_grafico, 0, 0, 0
        )
        binding.btnConsumo.buttonBorder.setTextColor(
            getColor(
                requireContext(),
                com.autG.oncln.R.color.black
            )
        )

        //btn salas
        binding.btnSalas.buttonBorder.text = getText(R.string.title_rooms)
        binding.btnSalas.buttonBorder.setTextColor(
            getColor(
                requireContext(),
                com.autG.oncln.R.color.white
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
                com.autG.oncln.R.color.blue_secundary
            )
        )

        TransitionManager.beginDelayedTransition(container, Fade())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnAgendamento.buttonBorder.setOnClickListener {
                (activity as NavigationHost).navigateTo(CadastrarSalaActivity(),addToBackStack = true,
                    R.layout.activity_cadastrar_sala)
            }
            btnCadastrar.buttonBorder.setOnClickListener {
                (activity as NavigationHost).navigateTo(CadastrarMenuActivity(),addToBackStack = true,
                    R.layout.activity_cadastro_menu)
            }
            btnSalas.buttonBorder.setOnClickListener {
                (activity as NavigationHost).navigateTo(SalasActivity(),addToBackStack = true,
                    R.layout.activity_salas)
            }
        }
    }

}