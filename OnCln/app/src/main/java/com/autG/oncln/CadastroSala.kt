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
import com.autG.oncln.databinding.ActivityCadastroSalaBinding

class CadastroSala : Fragment() {


    private lateinit var binding: ActivityCadastroSalaBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityCadastroSalaBinding.inflate(inflater, container, false)
        binding.includeText.textTitulo.text = "Cadastrar sala"

        binding.btnBlue.botaoAzul.text = "Cadastrar"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    fun cadastrarCln() {

    }


}

