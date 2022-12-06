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
import androidx.recyclerview.widget.LinearLayoutManager
import com.autG.oncln.adapter.RoomAdapter
import com.autG.oncln.api.Rest
import com.autG.oncln.databinding.ActivitySchedulesBinding
import com.autG.oncln.dtos.responses.Rooms
import com.autG.oncln.dtos.responses.RoomsItem
import com.autG.oncln.services.Auth
import com.autG.oncln.services.NavigationHost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

internal class SchedulesActivity : Fragment() {

//    private lateinit var binding: ActivitySchedulesBinding
//    private lateinit var arrayList: ArrayList<RoomsItem>
//    private val retrofit = Rest.getInstance()
//    private lateinit var prefs: SharedPreferences
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = ActivitySchedulesBinding.inflate(inflater, container, false)
//
//        binding.includeText.textTitulo.text = getText(R.string.title_input_scheduling)
//
//
//        //TODO só deixar colorido quando o filtro estiver ativo
//        binding.itemFiltros.botaoFiltroCadastrar.text = getText(R.string.txt_btn_registers)
//        binding.itemFiltros.botaoFiltroCadastrar.setBackgroundColor(
//            ContextCompat.getColor(
//                requireContext(),
//                R.color.purple_primary
//            )
//        )
//
//        binding.itemFiltros.botaoFiltroEmUso.text = getText(R.string.txt_btn_in_use)
//        //binding.itemFiltros.botaoFiltroEmUso.setBackgroundColor(
//        //    ContextCompat.getColor(
//        //        requireContext(),
//        //        R.color.blue_secondary
//        //    )
//        //)
//
//
//        binding.itemFiltros.botaoFiltroOciosas.text = getText(R.string.txt_btn_idle)
//        //binding.itemFiltros.botaoFiltroOciosas.setBackgroundColor(
//        //    ContextCompat.getColor(
//        //        requireContext(),
//        //        R.color.green_primary
//        //    )
//        //)
//
//        binding.recycleListRoom.layoutManager = LinearLayoutManager(context)
//
//        arrayList = arrayListOf()
//        requestRooms()
//
//        TransitionManager.beginDelayedTransition(container, Fade())
//
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//    }
//
//    private fun requestRooms() {
//
//        val authRequest = retrofit
//            .create(Auth::class.java)
//
//        prefs = requireContext().getSharedPreferences("preferences", AppCompatActivity.MODE_PRIVATE)
//
//        val cacheLogin = prefs.getInt("idPredio", 0)
//
//        authRequest.requestRooms(cacheLogin).enqueue(
//            object : Callback<Rooms?> {
//                override fun onResponse(
//                    call: Call<Rooms?>,
//                    response: Response<Rooms?>
//                ) {
//                    if (response.isSuccessful) {
//
//                        binding.txtTotal.text = "Total(${response.body()?.size})"
//
//                        response.body()?.forEach {
//                            arrayList.add(it)
//                        }
//                        binding.recycleListRoom.adapter = RoomAdapter(
//                            arrayList,
//                            getText(R.string.title_input_floor).toString(),
//                            requireContext(),
//                            {id, floor ->
//                                (activity as NavigationHost).navigateTo(
//                                    EditRoomActivity(id, floor), addToBackStack = true,
//                                    R.layout.activity_rooms
//                                )
//                            },
//                            { idRoom, nameRoom ->
//                                val dialog = AlertDialog.Builder(requireContext())
//
//                                dialog.setTitle(R.string.text_confirm_delete)
//                                dialog.setMessage(nameRoom)
//                                dialog.setPositiveButton(
//                                    R.string.txt_btn_delete
//                                ) { p0, p1 -> TODO("Not yet implemented") }
//                                dialog.setNegativeButton(
//                                    R.string.txt_btn_cancel
//                                ) { p0, p1 -> TODO("Not yet implemented") }
//
//                                dialog.create().show()
//                            }
//                        )
//                    } else {
//                        Toast.makeText(
//                            requireContext(),
//                            "Falha ao carregar Agendamentos",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<Rooms?>, t: Throwable) {
//                    Toast.makeText(context, getText(R.string.txt_offline_system), Toast.LENGTH_LONG).show()
//
//                }
//            })
//    }

}