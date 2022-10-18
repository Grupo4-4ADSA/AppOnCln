package com.autG.oncln

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
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

        binding.includeText.textTitulo.text = "Home"

        binding.btnAgendamento.buttonBorder.text="Agendamento"
        binding.btnAgendamento.buttonBorder.setCompoundDrawablesWithIntrinsicBounds(
            com.autG.oncln.R.drawable.ic_schedule,0,0,0)


        binding.btnCadastrar.buttonBorder.text="Cadastrar ou editar"
        binding.btnCadastrar.buttonBorder.setCompoundDrawablesWithIntrinsicBounds(
            com.autG.oncln.R.drawable.ic_register,0,0,0)


        binding.btnConsumo.buttonBorder.text="Consumo/equipamento"
        binding.btnConsumo.buttonBorder.setCompoundDrawablesWithIntrinsicBounds(
            com.autG.oncln.R.drawable.ic_grafico,0,0,0)


        binding.btnSalas.buttonBorder.text="Salas"
        binding.btnSalas.buttonBorder.setTextColor(getColor(requireContext(), com.autG.oncln.R.color.white))
        binding.btnSalas.buttonBorder.setCompoundDrawablesWithIntrinsicBounds(
            com.autG.oncln.R.drawable.ic_power,0,0,0)
        binding.btnSalas.buttonBorder.setBackgroundColor(getColor(requireContext(),
            com.autG.oncln.R.color.blue_secundary))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnAgendamento.buttonBorder.setOnClickListener {
                //(activity as NavigationHost).navigateTo(Agendamento(),addToBackStack = true)
            }
            btnCadastrar.buttonBorder.setOnClickListener {
                (activity as NavigationHost).navigateTo(CadastrarMenuActivity(),addToBackStack = true)
            }
        }
    }

}