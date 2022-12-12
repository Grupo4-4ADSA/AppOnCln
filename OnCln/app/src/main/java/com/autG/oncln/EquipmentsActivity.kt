package com.autG.oncln

import android.content.SharedPreferences
import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.autG.oncln.adapter.EquipmentAdapter
import com.autG.oncln.api.Rest
import com.autG.oncln.databinding.ActivityEquipmentsBinding
import com.autG.oncln.databinding.ActivityRoomsBinding
import com.autG.oncln.dtos.requests.Equip
import com.autG.oncln.dtos.requests.Equipments
import com.autG.oncln.dtos.requests.Register
import com.autG.oncln.dtos.responses.*
import com.autG.oncln.services.Auth
import com.autG.oncln.services.NavigationHost
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.util.ArrayList

internal class EquipmentsActivity : Fragment() {

    private lateinit var binding: ActivityEquipmentsBinding
    private lateinit var arrayList: ArrayList<EquipmentsResponse>
    private val retrofit = Rest.getInstance()
    private lateinit var prefs: SharedPreferences
    var acao = 0
    var acao1 = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityEquipmentsBinding.inflate(inflater, container, false)

        binding.includeText.textTitulo.text = getText(R.string.title_equipaments)

        //TODO só deixar colorido quando o filtro estiver ativo
        binding.itemFiltros.botaoFiltroCadastrar.text = getText(R.string.txt_btn_registers)
        binding.itemFiltros.botaoFiltroCadastrar.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.purple_primary
            )
        )

        binding.itemFiltros.botaoFiltroEmUso.text = getText(R.string.txt_btn_in_use)
        //binding.itemFiltros.botaoFiltroEmUso.setBackgroundColor(
        //    ContextCompat.getColor(
        //        requireContext(),
        //        R.color.blue_secondary
        //    )
        //)


        binding.itemFiltros.botaoFiltroOciosas.text = getText(R.string.txt_btn_idle)
        //binding.itemFiltros.botaoFiltroOciosas.setBackgroundColor(
        //    ContextCompat.getColor(
        //        requireContext(),
        //        R.color.green_primary
        //    )
        //)

        binding.recycleListRoom.layoutManager = LinearLayoutManager(context)

        arrayList = arrayListOf()
        prefs = requireContext().getSharedPreferences("preferences", AppCompatActivity.MODE_PRIVATE)

        val cacheLogin = prefs.getInt("idPredio", 0)
        requestEquipments(cacheLogin)

        TransitionManager.beginDelayedTransition(container, Fade())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPlus.fab.setOnClickListener {
            (activity as NavigationHost).navigateTo(
                RegisterRoomActivity(), addToBackStack = true,
                R.layout.activity_rooms
            )
        }


    }

    private fun requestEquipments(cacheLogin: Int) {

        val authRequest = retrofit
            .create(Auth::class.java)

        authRequest.requestEquipments(cacheLogin).enqueue(
            object : Callback<EquipmentResponse?> {
                override fun onResponse(
                    call: Call<EquipmentResponse?>,
                    response: Response<EquipmentResponse?>
                ) {
                    if (response.isSuccessful) {

                        binding.txtTotal.text = "Total(${response.body()?.size})"

                        response.body()?.forEach {
                            arrayList.add(it)
                        }
                        binding.recycleListRoom.adapter = EquipmentAdapter(
                            arrayList,
                            requireContext(),
                            {id, floor ->
                                //TODO colocar um desse para editar equipamento
                                (activity as NavigationHost).navigateTo(
                                    EditEquipmentActivity(id, floor), addToBackStack = true,
                                    R.layout.activity_rooms
                                )
                            },
                            { idEquipment, name ->
                                MaterialAlertDialogBuilder(requireContext())
                                    .setTitle(resources.getString(R.string.txt_btn_delete))
                                    .setMessage(resources.getString(R.string.text_confirm_delete))
                                    .setNeutralButton(resources.getString(R.string.txt_btn_cancel)) { dialog, which ->
                                        dialog.cancel()
                                    }
                                    .setPositiveButton(resources.getString(R.string.txt_btn_delete)) { dialog, which ->
                                        deleteEquipment(idEquipment)
                                    }
                                    .show()
                            },
                            { porta, ip, action, id ->
                                actionEquipment(porta, ip, action, id)

                                register(Register(action != "off"), id)
                            }


                        )
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Falha ao carregar equipamentos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                override fun onFailure(call: Call<EquipmentResponse?>, t: Throwable) {
                    Toast.makeText(context, getText(R.string.txt_offline_system), Toast.LENGTH_LONG).show()
                }
            })
    }

    private fun deleteEquipment(id: Int) {

        val authRequest = retrofit
            .create(Auth::class.java)

        authRequest.deleteEquipments(id).enqueue(
            object : Callback<Generic?> {
                override fun onResponse(
                    call: Call<Generic?>,
                    response: Response<Generic?>
                ) {
                    if (response.isSuccessful) {

                        Toast.makeText(requireContext(), "Equipamento excluido com Sucesso", Toast.LENGTH_SHORT).show()

                        (activity as NavigationHost).navigateTo(
                            EquipmentsActivity(), addToBackStack = true,
                            R.layout.activity_equipments
                        )
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Falha deletar equipamento",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Generic?>, t: Throwable) {
                    Toast.makeText(context, getText(R.string.txt_offline_system), Toast.LENGTH_LONG).show()

                }
            })
    }


    private fun actionEquipment(porta: Int, ip: String, action: String, id: Int) {

        val baseURL = "http://$ip"

        fun getInstance(): Retrofit {
            return Retrofit
                .Builder()
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl(baseURL).build()
        }

        val authRequest = getInstance()
            .create(Auth::class.java)

        authRequest.requestAction(porta, action).enqueue(
            object : Callback<clnAction?> {
                override fun onResponse(
                    call: Call<clnAction?>,
                    response: Response<clnAction?>
                ) {
                    if (response.isSuccessful) {

                        Toast.makeText(requireContext(), "Acão concluida", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Falha ao completar ação",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<clnAction?>, t: Throwable) {
                    Toast.makeText(context, getText(R.string.txt_offline_system), Toast.LENGTH_LONG).show()

                }
            })
    }

    private fun register(registro: Register, idEquipment: Int) {

        val authRequest = retrofit
            .create(Auth::class.java)

        authRequest.register(idEquipment,registro).enqueue(
            object : Callback<RegistroResponse?> {
                override fun onResponse(
                    call: Call<RegistroResponse?>,
                    response: Response<RegistroResponse?>
                ) {
                    if (response.isSuccessful) {

                        Toast.makeText(requireContext(), "Equipamento excluido com Sucesso", Toast.LENGTH_SHORT).show()

                        (activity as NavigationHost).navigateTo(
                            EquipmentsActivity(), addToBackStack = true,
                            R.layout.activity_equipments
                        )
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Falha deletar equipamento",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<RegistroResponse?>, t: Throwable) {
                    Toast.makeText(context, getText(R.string.txt_offline_system), Toast.LENGTH_LONG).show()

                }
            })
    }

}