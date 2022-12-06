package com.autG.oncln

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import com.autG.oncln.adapter.RoomAdapter
import com.autG.oncln.api.Rest
import com.autG.oncln.databinding.ActivityHomeBinding
import com.autG.oncln.dtos.responses.Buildings
import com.autG.oncln.dtos.responses.BuildingsItem
import com.autG.oncln.dtos.responses.Rooms
import com.autG.oncln.dtos.responses.RoomsItem
import com.autG.oncln.services.Auth
import com.autG.oncln.services.NavigationHost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


internal class HomeActivity : Fragment() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var arrayList: ArrayList<BuildingsItem>
    private val retrofit = Rest.getInstance()
    private lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityHomeBinding.inflate(inflater, container, false)

        binding.includeText.textTitulo.text = getText(R.string.title_home)

        //btn agendamento
        binding.btnAgendamento.buttonBorder.text = getText(R.string.title_input_scheduling)
        binding.btnAgendamento.buttonBorder.setCompoundDrawablesWithIntrinsicBounds(
            com.autG.oncln.R.drawable.ic_schedule, 0, 0, 0
        )

        //btn cadastrar ou editar
        binding.btnCadastrar.buttonBorder.text = getText(R.string.title_register_edit)
        binding.btnCadastrar.buttonBorder.setCompoundDrawablesWithIntrinsicBounds(
            com.autG.oncln.R.drawable.ic_register, 0, 0, 0
        )

        //btn consumo por equipamento
        binding.btnConsumo.buttonBorder.text = getText(R.string.title_input_consuming_equipments)
        binding.btnConsumo.buttonBorder.setCompoundDrawablesWithIntrinsicBounds(
            com.autG.oncln.R.drawable.ic_grafico, 0, 0, 0
        )

        //btn salas
        binding.btnSalas.buttonBorder.text = getText(R.string.title_rooms)
        binding.btnSalas.buttonBorder.setTextColor(
            getColor(
                requireContext(), R.color.white
            )
        )
        binding.btnSalas.buttonBorder.clipToOutline
        binding.btnSalas.buttonBorder.invalidateOutline()

        binding.btnSalas.buttonBorder.setCompoundDrawablesWithIntrinsicBounds(
            com.autG.oncln.R.drawable.ic_power, 0, 0, 0
        )
        binding.btnSalas.buttonBorder.setBackgroundColor(
            getColor(
                requireContext(),
                com.autG.oncln.R.color.blue_secondary
            )
        )

        TransitionManager.beginDelayedTransition(container, Fade())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = requireContext().getSharedPreferences("preferences", AppCompatActivity.MODE_PRIVATE)

        getData(prefs)

        with(binding) {
            if (context?.isDarkThemeOn() == true) {
                textModoDark.text = "Light mode"
                switchMode.isChecked = true
            } else {
                textModoDark.text = "Dark mode"
            }
            switchMode.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    (activity as NavigationHost).menuAction()
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    (activity as NavigationHost).menuAction()

                }
            }
            btnAgendamento.buttonBorder.setOnClickListener {
                (activity as NavigationHost).navigateTo(
                    SchedulesActivity(), addToBackStack = true,
                    R.layout.activity_schedules
                )
            }
            btnCadastrar.buttonBorder.setOnClickListener {
                (activity as NavigationHost).navigateTo(
                    RegistrationMenuActivity(), addToBackStack = true,
                    R.layout.activity_registration_menu
                )
            }
            btnConsumo.buttonBorder.setOnClickListener {
                (activity as NavigationHost).navigateTo(
                    EquipmentConsumptionActivity(), addToBackStack = true,
                    R.layout.activity_equipment_consumption
                )
            }
            btnSalas.buttonBorder.setOnClickListener {
                (activity as NavigationHost).navigateTo(
                    RoomsActivity(), addToBackStack = true,
                    R.layout.activity_rooms
                )
            }
        }
    }

    fun Context.isDarkThemeOn(): Boolean {
        return resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == UI_MODE_NIGHT_YES
    }

    fun getData(prefs: SharedPreferences) {

        val cacheLogin = prefs.getInt("idPredio", 0).toString()

        val authRequest = retrofit
            .create(Auth::class.java)

        authRequest.requestBuildings(cacheLogin).enqueue(
            object : Callback<Buildings?> {
                override fun onResponse(
                    call: Call<Buildings?>,
                    response: Response<Buildings?>
                ) {
                    if (response.isSuccessful) {

                        response.body()?.forEach {
                            val edit = prefs.edit()
                            edit.putString("predioNome", it.nomePredio)
                            edit.putInt("andares", it.andares)
                            edit.putInt("subsolos", it.subsolos)
                            edit.apply()
                        }
                        Toast.makeText(
                            requireContext(),
                            "Deu bom",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Falha ao adquirir dados",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Buildings?>, t: Throwable) {
                    Toast.makeText(requireContext(), "Sem conexão com servidor", Toast.LENGTH_LONG).show()
                }
            })
    }
}