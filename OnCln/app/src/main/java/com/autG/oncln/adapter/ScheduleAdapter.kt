package com.autG.schedule.adapter

import android.content.Context
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
import com.autG.oncln.dtos.requests.Sala
import com.autG.oncln.dtos.responses.EquipmentResponse
import com.autG.oncln.dtos.responses.RoomsItem
import com.autG.oncln.dtos.responses.SchedulesItem
import java.sql.Time
import java.util.ArrayList

class ScheduleAdapter(
    private val dataSet: ArrayList<SchedulesItem>,

    val data: String,
    val context: Context,
    onClick: (id: Int) -> Unit,
    delete: (idRoom: Int, nameRoom: String) -> Unit
) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    private val onClick = onClick
    private val delete = delete

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val data: TextView
        val horario: TextView
        val ligar: TextView
        val sala: TextView
        val floor: TextView

        val btnEditar: Button
        val btnDelete: Button

        init {
            data = view.findViewById(R.id.txt_data_start)
            floor = view.findViewById(R.id.txt_floor)
            horario = view.findViewById(R.id.txt_data_start)
            ligar = view.findViewById(R.id.txt_action)
            sala = view.findViewById(R.id.txt_room)
            btnEditar = view.findViewById(R.id.botao_editar)
            btnDelete = view.findViewById(R.id.botao_excluir)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_schedules, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // val data: TextView
        //        val horario: TextView
        //        val ligar: TextView
        //        val sala: TextView
        //        val floor: TextView
        holder.data.text = dataSet[position].data
        holder.horario.text = dataSet[position].horario
        holder.ligar.text = dataSet[position].ligar.toString()
        holder.sala.text = dataSet[position].sala.name
        holder.btnEditar.setOnClickListener {
            onClick(dataSet[position].id)
        }
        holder.btnDelete.setOnClickListener {
            delete(dataSet[position].id, dataSet[position].data )
        }
    }


}