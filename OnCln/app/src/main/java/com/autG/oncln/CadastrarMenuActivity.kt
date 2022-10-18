package com.autG.oncln

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import com.autG.oncln.databinding.ActivityCadastrarMenuBinding


internal class CadastrarMenuActivity : Fragment() {

    private lateinit var binding: ActivityCadastrarMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityCadastrarMenuBinding.inflate(inflater, container, false)

        binding.includeText.textTitulo.text = "Cadastrar ou editar"

        //cor_titulos
        binding.btnSalas.buttonBorder.text = "Salas"

        binding.btnSalas.buttonBorder.setCompoundDrawablesWithIntrinsicBounds(
            R.drawable.ic_meeting_room_blue, 0, 0, 0
        )

        binding.btnOncln.buttonBorder.text = "OnCln"
        binding.btnOncln.buttonBorder.setCompoundDrawablesWithIntrinsicBounds(
            R.drawable.ic_oncln, 0, 0, 0
        )

        binding.btnEquipamento.buttonBorder.text = "Equipamentos"
        binding.btnEquipamento.buttonBorder.setCompoundDrawablesWithIntrinsicBounds(
            R.drawable.ic_equipaments_blue, 0, 0, 0
        )
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}

