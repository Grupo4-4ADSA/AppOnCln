package com.autG.oncln

import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.autG.oncln.databinding.ActivityEditSchedulingBinding

class EditSchedulingActivity : Fragment() {


    private lateinit var binding: ActivityEditSchedulingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityEditSchedulingBinding.inflate(inflater, container, false)

        binding.includeText.textTitulo.text= getText(R.string.title_edit_scheduling)
        binding.btnAgendamento.botaoAzul.text = getText(R.string.txt_btn_save)

        TransitionManager.beginDelayedTransition(container, Fade())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun cadastrarCln() {

    }


}

