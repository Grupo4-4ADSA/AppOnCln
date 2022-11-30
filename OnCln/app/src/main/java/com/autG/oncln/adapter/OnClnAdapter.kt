package com.autG.oncln.adapter

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.autG.oncln.dtos.responses.OnclnItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.autG.oncln.R

class OnClnAdapter(private val dataSet: ArrayList<OnclnItem>):
    RecyclerView.Adapter<OnClnAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val idCln: TextView
        val txtNameRoom: TextView
        val txtFloor: TextView

        init {
            idCln = view.findViewById(R.id.txt_id_cln)
            txtNameRoom = view.findViewById(R.id.txt_room)
            txtFloor = view.findViewById(R.id.txt_floor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_clnbox, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.idCln.text= dataSet[position].idCLNBox.toString()
        holder.txtNameRoom.text= dataSet[position].sala.name
        holder.txtFloor.text= dataSet[position].sala.floor.toString()
        }

}