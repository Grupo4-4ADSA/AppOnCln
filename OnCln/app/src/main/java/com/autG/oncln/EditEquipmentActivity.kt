package com.autG.oncln

import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.autG.oncln.databinding.ActivityEditEquipmentBinding
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class EditEquipmentActivity : Fragment() {

    //TODO colocar o nome do equipamento de acordo com o clique no btn editar, exemplo:
    //TODO se eu clicar em editar um conjunto de lâmpadas, aparecer de acordo com o que se refere

    private lateinit var binding: ActivityEditEquipmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = ActivityEditEquipmentBinding.inflate(inflater, container, false)
        binding.includeText.textTitulo.text = getText(R.string.title_register_equipment)
        binding.btnEquipment.botaoAzul.text = getText(R.string.txt_register)

        TransitionManager.beginDelayedTransition(container, Fade())

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO fazer o array de salas pegando as mesmas do banco
        val items = arrayOf(
            getText(R.string.txt_air).toString(),
            getText(R.string.txt_set_of_lamps).toString(),
            getText(R.string.txt_socket_set).toString())
        (binding.textFieldEquip.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)
    }
}