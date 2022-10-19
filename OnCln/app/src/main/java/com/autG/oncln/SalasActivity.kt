package com.autG.oncln

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.autG.oncln.databinding.ActivitySalasBinding

internal class SalasActivity : Fragment() {

    private lateinit var binding: ActivitySalasBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivitySalasBinding.inflate(inflater, container, false)

        binding.includeText.textTitulo.text = "Salas"

        binding.incBtnFiltrarCadastradas.botaoFiltro.text = "Cadastradas"
        binding.incBtnFiltrarCadastradas.botaoFiltro.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.purple_primary
            )
        )

        binding.incBtnFiltrarUso.botaoFiltro.text = "Em uso"
        binding.incBtnFiltrarUso.botaoFiltro.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.blue_secundary
            )
        )

        binding.incBtnFiltrarOciosas.botaoFiltro.text = "Ociosas"
        binding.incBtnFiltrarOciosas.botaoFiltro.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.green_primary
            )
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}