package com.autG.oncln.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.autG.oncln.R
import com.autG.oncln.dtos.responses.EquipmentsResponse

class EquipmentAdapter(
    private val dataSet: ArrayList<EquipmentsResponse>,
    val context: Context,
    onClick: (id: Int, nameRoom: String) -> Unit,
    delete: (idRoom: Int, nameRoom: String) -> Unit
) : RecyclerView.Adapter<EquipmentAdapter.ViewHolder>() {
    private val onClick = onClick
    private val delete = delete

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgIcon: ImageView
        val txtType: TextView
        val switch: SwitchCompat
        val txtFloor: TextView
        val txtRoom: TextView
        val btnEditar: Button
        val btnDelete: Button

        init {
            imgIcon = view.findViewById(R.id.img_icon)
            txtType = view.findViewById(R.id.txt_type)
            txtRoom = view.findViewById(R.id.txt_room)
            switch = view.findViewById(R.id.botao_ligar_desligar)
            txtFloor = view.findViewById(R.id.txt_floor)
            btnEditar = view.findViewById(R.id.botao_editar)
            btnDelete = view.findViewById(R.id.botao_excluir)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_equipment, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtType.text = dataSet[position].tipo
        holder.txtFloor.text = "andar: ${dataSet[position].clnBox.sala.floor}"
        holder.txtRoom.text = dataSet[position].clnBox.sala.name
        holder.switch.isChecked = dataSet[position].registro != null && dataSet[position].registro?.ligado == true
        holder.btnEditar.setOnClickListener {
            onClick(dataSet[position].idEquipamento, dataSet[position].clnBox.sala.name)
        }
        holder.btnDelete.setOnClickListener {
            delete(dataSet[position].idEquipamento, dataSet[position].clnBox.sala.name)
        }
    }


}