package com.autG.oncln

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.autG.oncln.databinding.ActivityEditarSalaBinding

class EditarSalaActivity : Fragment() {


    private lateinit var binding: ActivityEditarSalaBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityEditarSalaBinding.inflate(inflater, container, false)

        binding.includeText.textTitulo.text= getText(R.string.title_edit_room)
        binding.btnQr.botaoAzul.text = "Salvar"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun cadastrarCln() {

    }


}

