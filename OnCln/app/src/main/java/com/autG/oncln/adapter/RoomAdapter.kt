package com.autG.oncln.adapter

import android.content.Context
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.BlendModeColorFilter
import android.graphics.ColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.recyclerview.widget.RecyclerView
import com.autG.oncln.R
import com.autG.oncln.dtos.responses.EquipmentResponse
import com.autG.oncln.dtos.responses.RoomsItem

class RoomAdapter(
    private val dataSet: ArrayList<RoomsItem>,
    val floor: String,
    val context: Context,
    onClick: (id: Int, floorItem: Int) -> Unit,
    delete: (idRoom: Int, nameRoom: String) -> Unit,
    onClickAcess: (idRoom: Int,nameRoom: String,floor: Int) -> Unit
) : RecyclerView.Adapter<RoomAdapter.ViewHolder>() {
    private val onClick = onClick
    private val delete = delete
    private val onClickAcess = onClickAcess

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgIcon: ImageView
        val txtType: TextView
        val iconPower: ImageView
        val txtFloor: TextView
        val btnEditar: Button
        val btnDelete: Button
        val itemClick: LinearLayoutCompat

        init {
            imgIcon = view.findViewById(R.id.img_icon)
            txtType = view.findViewById(R.id.txt_type)
            iconPower = view.findViewById(R.id.icon_power)
            txtFloor = view.findViewById(R.id.txt_floor)
            btnEditar = view.findViewById(R.id.botao_editar)
            btnDelete = view.findViewById(R.id.botao_excluir)
            itemClick = view.findViewById(R.id.layout_room)
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
        holder.txtFloor.text = "$floor ${dataSet[position].floor}"
        holder.iconPower.background.setTint(ContextCompat.getColor(context,R.color.green_tertiary))
        holder.btnEditar.setOnClickListener {
            onClick(dataSet[position].idRoom, dataSet[position].floor)
        }
        holder.btnDelete.setOnClickListener {
            delete(dataSet[position].idRoom, dataSet[position].name)
        }
        holder.itemClick.setOnClickListener {
            onClickAcess(dataSet[position].idRoom,dataSet[position].name,dataSet[position].floor)
        }
    }


}