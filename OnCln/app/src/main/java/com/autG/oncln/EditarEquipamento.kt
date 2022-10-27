package com.autG.oncln

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.autG.oncln.R
import com.autG.oncln.databinding.ActivityCadastrarEquipamentoBinding
import com.autG.oncln.databinding.ActivityEditarEquipamentoBinding

class EditarEquipamento : Fragment() {

    private lateinit var binding: ActivityEditarEquipamentoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityEditarEquipamentoBinding.inflate(inflater,container,false)

        binding.btnBlue.botaoAzul.text= "Salvar"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}