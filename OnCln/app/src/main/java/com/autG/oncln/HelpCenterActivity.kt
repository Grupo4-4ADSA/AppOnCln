package com.autG.oncln

import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.autG.oncln.databinding.ActivityHelpCenterBinding

class HelpCenterActivity:Fragment() {

    private lateinit var binding: ActivityHelpCenterBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityHelpCenterBinding.inflate(inflater,container,false)

        binding.includeText.textTitulo.text = getText(R.string.title_help_center)

        TransitionManager.beginDelayedTransition(container, Fade())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}