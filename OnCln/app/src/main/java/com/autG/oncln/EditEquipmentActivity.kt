package com.autG.oncln

import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.autG.oncln.databinding.ActivityEditEquipmentBinding

class EditEquipmentActivity : Fragment() {

    private lateinit var binding: ActivityEditEquipmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityEditEquipmentBinding.inflate(inflater,container,false)

        //TODO colocar o nome do equipamento de acordo com o clique no btn editar, exemplo:
        //TODO se eu clicar em editar um conjunto de lâmpadas, aparecer de acordo com o que se refere
        binding.includeText.textTitulo.text = getText(R.string.title_input_edit_equipment)
        binding.btnEquipment.botaoAzul.text= getText(R.string.txt_btn_save)

        TransitionManager.beginDelayedTransition(container, Fade())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}