package com.autG.oncln

import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.autG.oncln.databinding.ActivityCadastroAgendamentoBinding
import com.autG.oncln.menus.NavBarBottom

internal class AgendamentoActivity : Fragment() {

    private lateinit var binding: ActivityCadastroAgendamentoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityCadastroAgendamentoBinding.inflate(inflater, container, false)
        binding.includeText.textTitulo.text = "Novo agendamento"

        TransitionManager.beginDelayedTransition(container, Fade())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}