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

class EquipmentRoomAdapter(
    private val dataSet: ArrayList<EquipmentsResponse>,
    val context: Context,
    onClick: (id: Int, nameRoom: String) -> Unit,
    delete: (idRoom: Int, nameRoom: String) -> Unit
) : RecyclerView.Adapter<EquipmentRoomAdapter.ViewHolder>() {
    private val onClick = onClick
    private val delete = delete

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgIcon: ImageView
        val txtType: TextView
        val iconPower: ImageView
        val switch: SwitchCompat
        val txtFloor: TextView
        val txtRoom: TextView

        init {
            imgIcon = view.findViewById(R.id.img_icon)
            txtType = view.findViewById(R.id.txt_type)
            txtRoom = view.findViewById(R.id.txt_room)
            switch = view.findViewById(R.id.botao_ligar_desligar)
            txtFloor = view.findViewById(R.id.txt_floor)
            iconPower = view.findViewById(R.id.icon_power)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_equipments_room, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtType.text = "${dataSet[position].tipo}"
        holder.txtFloor.text = "${context.getString(R.string.text_example_floor)} ${dataSet[position].clnBox.sala.floor}"
        holder.txtRoom.text = dataSet[position].clnBox.sala.name
        holder.switch.isChecked = dataSet[position].registro != null && dataSet[position].registro?.ligado == true
        if (dataSet[position].registro != null && dataSet[position].registro?.ligado == true){
            holder.iconPower.background.setTint(ContextCompat.getColor(context,R.color.green_tertiary))
        }else{
            holder.iconPower.background.setTint(ContextCompat.getColor(context,R.color.black))
        }
    }


}