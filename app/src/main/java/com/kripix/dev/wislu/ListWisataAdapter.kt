package com.kripix.dev.wislu

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ListWisataAdapter(private val listWisata: ArrayList<Wisata>): RecyclerView.Adapter<ListWisataAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(callback: OnItemClickCallback) {
        this.onItemClickCallback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_wisata, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listWisata.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, location, map, operational, ticket, description, photo1) = listWisata[position]
        holder.ivDestinationImage.setImageResource(photo1)
        holder.tvName.text = name
        holder.tvLocation.text = location

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_wisata", listWisata[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivDestinationImage: ImageView = itemView.findViewById(R.id.iv_destination_image)
        val tvName: TextView = itemView.findViewById(R.id.tv_title)
        val tvLocation: TextView = itemView.findViewById(R.id.tvLocation)
    }

    interface OnItemClickCallback {
        fun onItemClick(data: Wisata)
    }
}


