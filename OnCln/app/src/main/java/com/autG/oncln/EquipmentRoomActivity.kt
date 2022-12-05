package com.autG.oncln

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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.autG.oncln.adapter.RoomAdapter
import com.autG.oncln.api.Rest
import com.autG.oncln.databinding.ActivityEquipmentRoomBinding
import com.autG.oncln.dtos.responses.Rooms
import com.autG.oncln.dtos.responses.RoomsItem
import com.autG.oncln.menus.NavBarBottom
import com.autG.oncln.services.Auth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

internal class EquipmentRoomActivity : Fragment() {

    private lateinit var binding: ActivityEquipmentRoomBinding
    private lateinit var arrayList: ArrayList<RoomsItem>
    private val retrofit = Rest.getInstance()
    private lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityEquipmentRoomBinding.inflate(inflater, container, false)

        //TODO colocar de acordo com a sala selecionada
        binding.includeTextRoom.textTitulo.text = getText(R.string.text_example_room)

        binding.includeTextFloor.textTitulo.text = getText(R.string.text_example_floor)

        binding.recycleListRoom.layoutManager =
            GridLayoutManager(
                context,
                2
            )

        arrayList = arrayListOf()
        requestRooms()

        TransitionManager.beginDelayedTransition(container, Fade())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun requestRooms() {

        prefs = requireContext().getSharedPreferences("preferences", AppCompatActivity.MODE_PRIVATE)

        val cacheLogin = prefs.getInt("idPredio", 0)

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
                        binding.recycleListRoom.adapter = RoomAdapter(arrayList, "nha", requireContext()) { msg ->
                            Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
                        }
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

}