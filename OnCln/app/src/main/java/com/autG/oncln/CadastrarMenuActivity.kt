package com.autG.oncln

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.autG.oncln.databinding.ActivityCadastroMenuBinding
import com.autG.oncln.services.NavigationHost


internal class CadastrarMenuActivity : Fragment() {

    private lateinit var binding: ActivityCadastroMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityCadastroMenuBinding.inflate(inflater, container, false)

        binding.includeText.textTitulo.text = getText(R.string.title_register_edit)

        binding.btnSalas.buttonBorder.text = getText(R.string.title_rooms)
        binding.btnSalas.buttonBorder.setCompoundDrawablesWithIntrinsicBounds(
            R.drawable.ic_meeting_room_blue, 0, 0, 0
        )

        binding.btnOncln.buttonBorder.text = getText(R.string.title_input_oncln)
        binding.btnOncln.buttonBorder.setCompoundDrawablesWithIntrinsicBounds(
            R.drawable.ic_oncln, 0, 0, 0
        )

        binding.btnEquipamento.buttonBorder.text = getText(R.string.title_equipaments)
        binding.btnEquipamento.buttonBorder.setCompoundDrawablesWithIntrinsicBounds(
            R.drawable.ic_equipaments_blue, 0, 0, 0
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnSalas.buttonBorder.setOnClickListener {
                (activity as NavigationHost).navigateTo(SalasActivity(), addToBackStack = true)
                btnEquipamento.buttonBorder.setOnClickListener {
                }

                btnEquipamento.buttonBorder.setOnClickListener {
                    //todo implementar navigate, futuramente
                }
            }

        }
    }
}

