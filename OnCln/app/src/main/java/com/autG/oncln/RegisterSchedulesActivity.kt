package com.autG.oncln

import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.autG.oncln.databinding.ActivityRegisterScheduleBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

internal class RegisterSchedulesActivity : Fragment() {

    private lateinit var binding: ActivityRegisterScheduleBinding

    private val DAYMILISSECONDS = 86400000

    private var action = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        TransitionManager.beginDelayedTransition(container, Fade())

        binding = ActivityRegisterScheduleBinding.inflate(inflater, container, false)
        binding.includeText.textTitulo.text = getText(R.string.title_input_new_scheduling)
        binding.btnAgendamento.botaoAzul.text = getText(R.string.txt_btn_save)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val constraintsBuilder =
            CalendarConstraints.Builder()
                .setValidator(DateValidatorPointForward.now())

        val datePicker =
            MaterialDatePicker.Builder.dateRangePicker().setCalendarConstraints(constraintsBuilder.build())
                .setTitleText(getText(R.string.hint_data))
                .build()

        binding.inputDateInitial.setOnClickListener {
            if (action == 0) {
                datePicker.show(activity?.supportFragmentManager!!, "date_picker")
                action = 1
            }
        }
        binding.inputDateFinal.setOnClickListener {
            if (action == 0) {
                datePicker.show(activity?.supportFragmentManager!!, "date_picker")
                action = 1
            }
        }

        datePicker.addOnPositiveButtonClickListener {
            val dateInitial = datePicker.selection?.first
            val dateFinal = datePicker.selection?.second
            val dateFormated = SimpleDateFormat("dd/MM/yyyy")
            binding.inputDateInitial.setText(
                dateFormated.format(
                    dateInitial?.plus(DAYMILISSECONDS) ?: DAYMILISSECONDS
                )
            )
            binding.inputDateFinal.setText(
                dateFormated.format(
                    dateFinal?.plus(DAYMILISSECONDS) ?: DAYMILISSECONDS
                )
            )
            action = 0
        }
    }

}