package com.autG.oncln

import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.autG.oncln.databinding.ActivityRegisterOnClnBinding

class RegisterOnClnActivity : Fragment() {

    private lateinit var binding: ActivityRegisterOnClnBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityRegisterOnClnBinding.inflate(inflater, container, false)
        binding.includeText.textTitulo.text = getText(R.string.title_register_oncln)
        binding.btnQr.botaoAzul.text = getText(R.string.txt_register)
        TransitionManager.beginDelayedTransition(container, Fade())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO fazer o array de salas pegando as mesmas do banco
    }


    fun cadastrarCln() {

    }


}

