package com.autG.oncln

import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.autG.oncln.databinding.ItemConfirmDeleteBinding

class ItemDeleteActivity :  DialogFragment() {

    private lateinit var binding: ItemConfirmDeleteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        TransitionManager.beginDelayedTransition(container, Fade())
        binding = ItemConfirmDeleteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}