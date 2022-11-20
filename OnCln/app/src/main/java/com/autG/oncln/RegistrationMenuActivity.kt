package com.autG.oncln

import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import com.autG.oncln.databinding.ActivityRegistrationMenuBinding
import com.autG.oncln.services.NavigationHost

internal class RegistrationMenuActivity : Fragment() {

    private lateinit var binding: ActivityRegistrationMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityRegistrationMenuBinding.inflate(inflater, container, false)

        binding.includeText.textTitulo.text = getText(R.string.title_register_edit)

        binding.btnSalas.buttonBorder.text = getText(R.string.title_rooms)
        binding.btnSalas.buttonBorder.setCompoundDrawablesWithIntrinsicBounds(
            R.drawable.ic_meeting_room_blue, 0, 0, 0
        )
        binding.btnSalas.buttonBorder.setTextColor(getColor(requireContext(), R.color.blue_secundary))

        binding.btnOncln.buttonBorder.text = getText(R.string.title_input_oncln)
        binding.btnOncln.buttonBorder.setCompoundDrawablesWithIntrinsicBounds(
            R.drawable.ic_oncln, 0, 0, 0
        )
        binding.btnOncln.buttonBorder.setTextColor(getColor(requireContext(), R.color.blue_secundary))

        binding.btnEquipamento.buttonBorder.text = getText(R.string.title_equipaments)
        binding.btnEquipamento.buttonBorder.setCompoundDrawablesWithIntrinsicBounds(
            R.drawable.ic_equipaments_blue, 0, 0, 0
        )
        binding.btnEquipamento.buttonBorder.setTextColor(getColor(requireContext(), R.color.blue_secundary))

        binding.btnAgenmento.buttonBorder.text = getText(R.string.title_input_scheduling)
        binding.btnAgenmento.buttonBorder.setCompoundDrawablesWithIntrinsicBounds(
            R.drawable.ic_schedule_blue, 0, 0, 0
        )
        binding.btnAgenmento.buttonBorder.setTextColor(getColor(requireContext(), R.color.blue_secundary))

        TransitionManager.beginDelayedTransition(container, Fade())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnSalas.buttonBorder.setOnClickListener {
                (activity as NavigationHost).navigateTo(
                    RoomsActivity(), addToBackStack = true,
                    R.layout.activity_rooms
                )
            }
            btnOncln.buttonBorder.setOnClickListener {
                (activity as NavigationHost).navigateTo(
                    RegisterOnClnActivity(), addToBackStack = true,
                    R.layout.activity_rooms
                )
            }
            btnEquipamento.buttonBorder.setOnClickListener {
                //todo implementar navigate, futuramente
            }

        }
    }
}

