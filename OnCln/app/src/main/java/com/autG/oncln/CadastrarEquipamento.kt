package com.autG.oncln

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.autG.oncln.R
import com.autG.oncln.databinding.ActivityCadastrarEquipamentoBinding

class CadastrarEquipamento : Fragment() {

    private lateinit var binding: ActivityCadastrarEquipamentoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityCadastrarEquipamentoBinding.inflate(inflater,container,false)
        binding.includeText.textTitulo.text = "Cadastrar equipamento"

        binding.btnBlue.botaoAzul.text= "Cadastrar"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}