package com.autG.oncln

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.autG.oncln.R
import com.autG.oncln.databinding.ActivityCadastrarEquipamentoBinding
import com.autG.oncln.databinding.ActivityCadastroOnClnBinding
import com.autG.oncln.databinding.ActivityEditarSalaBinding

class EditarSala : Fragment() {


    private lateinit var binding: ActivityEditarSalaBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityEditarSalaBinding.inflate(inflater, container, false)

        binding.btnBlue.botaoAzul.text = "Salvar"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    fun cadastrarCln() {

    }


}

