package com.autG.oncln

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.autG.oncln.databinding.ActivityCadastrarEquipamentoBinding
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class CadastrarEquipamentoActivity : Fragment() {

    private lateinit var binding: ActivityCadastrarEquipamentoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = ActivityCadastrarEquipamentoBinding.inflate(inflater, container, false)
        binding.includeText.textTitulo.text = getText(R.string.register_equipment_title)
        binding.btnEquipment.botaoAzul.text = "Cadastrar"
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO fazer o array de salas pegando as mesmas do banco
        val items = arrayOf("Ar-condicionado", "Conjunto de lâmpadas", "Conjunto de tomadas")
        (binding.textFieldEquip.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)
    }
}