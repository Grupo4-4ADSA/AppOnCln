package com.autG.oncln

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.autG.oncln.databinding.ActivityCadastrarMenuBinding


class CadastrarMenuActivity : Fragment() {

    private lateinit var binding: ActivityCadastrarMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityCadastrarMenuBinding.inflate(inflater, container, false)

        binding.includeText.textTitulo.text = "Cadastrar ou editar"

        binding.btnSalas.buttonBorder.text = "Salas"

        binding.btnOncln.buttonBorder.text="On Cln"
        binding.btnEquipamentos.buttonBorder.text="Equipamentos"
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}

