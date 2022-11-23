package com.autG.oncln

import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.autG.oncln.adapter.RoomAdapter
import com.autG.oncln.api.Rest
import com.autG.oncln.databinding.ActivityOnclnsBinding
import com.autG.oncln.dtos.responses.Rooms
import com.autG.oncln.dtos.responses.RoomsItem
import com.autG.oncln.menus.NavBarBottom
import com.autG.oncln.services.Auth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

internal class OnclnsActivity : Fragment() {

    private lateinit var binding: ActivityOnclnsBinding
    private lateinit var arrayList: ArrayList<RoomsItem>
    private val retrofit = Rest.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityOnclnsBinding.inflate(inflater, container, false)

        binding.includeText.textTitulo.text = getText(R.string.title_oncln)

        binding.itemFiltros.botaoFiltroCadastrar.text =  getText(R.string.txt_btn_registers)
        binding.itemFiltros.botaoFiltroCadastrar.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.purple_primary
            )
        )

        binding.itemFiltros.botaoFiltroEmUso.text =  getText(R.string.txt_btn_in_use)
        binding.itemFiltros.botaoFiltroEmUso.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.blue_secondary
            )
        )

        binding.itemFiltros.botaoFiltroOciosas.text =  getText(R.string.txt_btn_idle)
        binding.itemFiltros.botaoFiltroOciosas.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.green_primary
            )
        )

        binding.recycleListRoom.layoutManager = LinearLayoutManager(context)

        arrayList = arrayListOf()
        requestRooms()

        TransitionManager.beginDelayedTransition(container, Fade())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun requestRooms() {

        val authRequest = retrofit
            .create(Auth::class.java)

        authRequest.requestRooms().enqueue(
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
                        binding.recycleListRoom.adapter = RoomAdapter(arrayList, "nha",requireContext()) { msg ->
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