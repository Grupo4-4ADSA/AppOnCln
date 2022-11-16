package com.autG.oncln.menus

import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.autG.oncln.MainActivity
import com.autG.oncln.databinding.ComponentMenuLateralBinding
import com.autG.oncln.services.NavigationHost

class NavigationBar: Fragment() {

    private lateinit var binding: ComponentMenuLateralBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ComponentMenuLateralBinding.inflate(inflater, container,false)

        TransitionManager.beginDelayedTransition(container, Slide(Gravity.RIGHT).setDuration(500))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewVoid.setOnClickListener {
            TransitionManager.beginDelayedTransition(this.binding.root, Slide(Gravity.RIGHT).setDuration(500))
            (activity as NavigationHost).menuAction()
        }
    }

    override fun onDetach() {
        super.onDetach()
    }
}