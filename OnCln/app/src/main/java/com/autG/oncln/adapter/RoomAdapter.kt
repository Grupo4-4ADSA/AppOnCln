package com.autG.oncln.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.autG.oncln.R
import com.autG.oncln.dtos.responses.RoomsItem

class RoomAdapter(private val dataSet: ArrayList<RoomsItem>, onClick: (msg: String) -> Unit): RecyclerView.Adapter<RoomAdapter.ViewHolder>() {
    private val onClick = onClick
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imgIcon: ImageView
        val txtType: TextView
        val iconPower: ImageView
        val txtFloor: TextView
        val btnEditar: ImageView

        init {
            imgIcon = view.findViewById(R.id.img_icon)
            txtType = view.findViewById(R.id.txt_type)
            iconPower = view.findViewById(R.id.icon_power)
            txtFloor = view.findViewById(R.id.txt_floor)
            btnEditar = view.findViewById(R.id.botao_editar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_room, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.txtType.text = dataSet[position].name
        holder.txtFloor.text = dataSet[position].floor.toString()
        holder.btnEditar.setOnClickListener { onClick("Nha") }
    }

}