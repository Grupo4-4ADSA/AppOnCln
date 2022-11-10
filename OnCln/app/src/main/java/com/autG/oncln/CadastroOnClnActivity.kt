package com.autG.oncln

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.autG.oncln.databinding.ActivityCadastroOnClnBinding

class CadastroOnClnActivity : Fragment() {

    private lateinit var binding: ActivityCadastroOnClnBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityCadastroOnClnBinding.inflate(inflater, container, false)
        binding.includeText.textTitulo.text = "Cadastrar on cln"
        binding.btnQr.botaoAzul.text = "Cadastrar"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO fazer o array de salas pegando as mesmas do banco
    }


    fun cadastrarCln() {

    }


}

