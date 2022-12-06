package com.autG.oncln

import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
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
import com.autG.oncln.adapter.EquipmentAdapter
import com.autG.oncln.adapter.EquipmentRoomAdapter
import com.autG.oncln.adapter.RoomAdapter
import com.autG.oncln.api.Rest
import com.autG.oncln.databinding.ActivityEquipmentRoomBinding
import com.autG.oncln.dtos.responses.EquipmentResponse
import com.autG.oncln.dtos.responses.EquipmentsResponse
import com.autG.oncln.dtos.responses.Rooms
import com.autG.oncln.dtos.responses.RoomsItem
import com.autG.oncln.menus.NavBarBottom
import com.autG.oncln.services.Auth
import com.autG.oncln.services.NavigationHost
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

internal class EquipmentRoomActivity(val nameRoom: String, val floor: Int,val  idRoom: Int) : Fragment() {

    private lateinit var binding: ActivityEquipmentRoomBinding
    private lateinit var arrayList: ArrayList<EquipmentsResponse>
    private val retrofit = Rest.getInstance()
    private lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityEquipmentRoomBinding.inflate(inflater, container, false)

        binding.includeTextRoom.textTitulo.text = "${requireContext().getString(R.string.text_example_room)} $nameRoom"

        binding.includeTextFloor.textTitulo.text = "${requireContext().getString(R.string.text_example_floor)} $floor"

        binding.recycleListRoom.layoutManager =
            GridLayoutManager(
                context,
                2
            )

        arrayList = arrayListOf()

        requestEquipments(idRoom)

        TransitionManager.beginDelayedTransition(container, Fade())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun requestEquipments(cacheLogin: Int) {

        val authRequest = retrofit
            .create(Auth::class.java)

        authRequest.requestEquipmentsRoom(cacheLogin).enqueue(
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
                        binding.recycleListRoom.adapter = EquipmentRoomAdapter(
                            arrayList,
                            requireContext(),
                            { _, _ -> },
                            { _, _ -> },


                        )
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Falha ao carregar salas",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                override fun onFailure(call: Call<EquipmentResponse?>, t: Throwable) {
                    Toast.makeText(context, getText(R.string.txt_offline_system), Toast.LENGTH_LONG).show()
                }
            })
    }

}