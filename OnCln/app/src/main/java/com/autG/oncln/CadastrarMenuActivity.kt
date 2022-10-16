package com.autG.oncln

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.autG.oncln.databinding.ActivityCadastrarMenuBinding
import com.autG.oncln.databinding.ActivityCentralDeAjudaBinding

class CadastrarMenuActivity : Fragment() {

    private lateinit var binding: ActivityCadastrarMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityCadastrarMenuBinding.inflate(inflater, container, false)

        binding.includeText.textTitulo.text = "Cadastrar ou editar"

        binding.btnSalas.buttonBorder.text = "Teste"
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}

