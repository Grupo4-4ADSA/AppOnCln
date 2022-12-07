package com.autG.oncln

import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.autG.oncln.adapter.RoomAdapter
import com.autG.oncln.api.Rest
import com.autG.oncln.databinding.ActivityRoomsBinding
import com.autG.oncln.dtos.responses.Generic
import com.autG.oncln.dtos.responses.Rooms
import com.autG.oncln.dtos.responses.RoomsItem
import com.autG.oncln.services.Auth
import com.autG.oncln.services.NavigationHost
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

internal class RoomsActivity : Fragment() {

    private lateinit var binding: ActivityRoomsBinding
    private lateinit var arrayList: ArrayList<RoomsItem>
    private val retrofit = Rest.getInstance()
    private lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityRoomsBinding.inflate(inflater, container, false)

        binding.includeText.textTitulo.text = getText(R.string.title_rooms)

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
        requestRooms(cacheLogin)

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

    private fun requestRooms(cacheLogin: Int) {

        val authRequest = retrofit
            .create(Auth::class.java)

        authRequest.requestRooms(cacheLogin).enqueue(
            object : Callback<Rooms?> {
                override fun onResponse(
                    call: Call<Rooms?>,
                    response: Response<Rooms?>
                ) {
                    if (response.isSuccessful) {

                        binding.txtTotal.text = "Total(${response.body()?.size})"

                        response.body()?.forEach {
                            arrayList.add(it)
                        }
                        binding.recycleListRoom.adapter = RoomAdapter(
                            arrayList,
                            getText(R.string.title_input_floor).toString(),
                            requireContext(),
                            {id, floor ->
                                (activity as NavigationHost).navigateTo(
                                    EditRoomActivity(id, floor), addToBackStack = true,
                                    R.layout.activity_rooms
                                )
                        },
                            { idRoom, nameRoom ->
                                MaterialAlertDialogBuilder(requireContext())
                                    .setTitle(resources.getString(R.string.txt_btn_delete))
                                    .setMessage(resources.getString(R.string.text_confirm_delete))
                                    .setNeutralButton(resources.getString(R.string.txt_btn_cancel)) { dialog, which ->
                                        dialog.cancel()
                                    }
                                    .setPositiveButton(resources.getString(R.string.txt_btn_delete)) { dialog, which ->
                                        deleteRooms(idRoom)
                                    }
                                    .show()
                            },
                            {
                                idRoom, nameRoom, floor ->

                                (activity as NavigationHost).navigateTo(
                                    EquipmentRoomActivity(nameRoom, floor, idRoom), addToBackStack = true,
                                    R.layout.activity_equipments
                                )

                            }


                        )
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Falha ao carregar salas",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                override fun onFailure(call: Call<Rooms?>, t: Throwable) {
                    Toast.makeText(context, getText(R.string.txt_offline_system), Toast.LENGTH_LONG).show()
                }
            })
    }

    private fun deleteRooms(id: Int) {

        val authRequest = retrofit
            .create(Auth::class.java)

        authRequest.deleteRooms(id).enqueue(
            object : Callback<Generic?> {
                override fun onResponse(
                    call: Call<Generic?>,
                    response: Response<Generic?>
                ) {
                    if (response.isSuccessful) {

                        Toast.makeText(requireContext(), "sala excluida com Sucesso", Toast.LENGTH_SHORT).show()

                        (activity as NavigationHost).navigateTo(
                            RoomsActivity(), addToBackStack = true,
                            R.layout.activity_rooms
                        )
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Falha ao carregar salas",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Generic?>, t: Throwable) {
                    Toast.makeText(context, getText(R.string.txt_offline_system), Toast.LENGTH_LONG).show()

                }
            })
    }

}