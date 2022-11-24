package com.autG.oncln

import android.os.Bundle
import android.text.Selection.setSelection
import android.transition.Fade
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.autG.oncln.databinding.ActivityRegisterEquipamentBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.time.Duration.Companion.days

class RegisterEquipamentActivity : Fragment() {

    private lateinit var binding: ActivityRegisterEquipamentBinding

    private val DAYMILISSECONDS = 86400000

    private var action = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = ActivityRegisterEquipamentBinding.inflate(inflater, container, false)
        binding.includeText.textTitulo.text = getText(R.string.title_register_equipment)
        binding.btnEquipment.botaoAzul.text = getText(R.string.txt_register)

        TransitionManager.beginDelayedTransition(container, Fade())
//dialog
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText(getText(R.string.hint_data))
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

        binding.datePickerActions.setOnClickListener {
            if (action == 0) {
                datePicker.show(activity?.supportFragmentManager!!, "date_picker")
                action = 1
            }
        }

        datePicker.addOnPositiveButtonClickListener {
            val date = datePicker.selection?.let { it1 -> Date(it1) }
            val dateFormated = SimpleDateFormat("dd/MM/yyyy")
            binding.datePickerActions.setText(
                dateFormated.format(
                    date?.time?.plus(DAYMILISSECONDS) ?: DAYMILISSECONDS
                )
            )
            action = 0
        }

        //TODO fazer o array de salas pegando as mesmas do banco
        val items = arrayOf(
            getText(R.string.txt_air).toString(),
            getText(R.string.txt_set_of_lamps).toString(),
            getText(R.string.txt_socket_set).toString()
        )
        (binding.textFieldEquip.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)
    }
}