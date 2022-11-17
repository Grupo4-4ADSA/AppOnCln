package com.autG.oncln

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.autG.oncln.databinding.ActivityEditarEquipamentoBinding

class EditarEquipamentoActivity : Fragment() {

    private lateinit var binding: ActivityEditarEquipamentoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityEditarEquipamentoBinding.inflate(inflater,container,false)

        //TODO colocar o nome do equipamento de acordo com o clique no btn editar, exemplo:
        //TODO se eu clicar em editar um conjunto de lâmpadas, aparecer de acordo com o que se refere
        binding.includeText.textTitulo.text = "Editar Ar-condicionado"
        binding.btnEquipment.botaoAzul.text= "Salvar"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}