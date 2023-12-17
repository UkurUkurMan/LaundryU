package com.laundryukurukur.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.laundryukurukur.R
import com.laundryukurukur.database.Order
import com.laundryukurukur.databinding.AdapterBinding

class ProsesAdapter (private val proses: ArrayList<Order>) : RecyclerView.Adapter<ProsesAdapter.ProsesViewHolder>() {
    class ProsesViewHolder(view: View) :  RecyclerView.ViewHolder(view) {
        val textKtg: TextView = view.findViewById(R.id.textKtg)
        val textName: TextView = view.findViewById(R.id.textNama)
        val textNo: TextView = view.findViewById(R.id.textNo)
        val textPkt: TextView = view.findViewById(R.id.textPkt)
        val textQnt: TextView = view.findViewById(R.id.textQnt)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProsesViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return ProsesViewHolder(v)
    }
    override fun getItemCount(): Int {
        return proses.size
    }

    override fun onBindViewHolder(holder: ProsesViewHolder, position: Int) {
        val onProses = proses[position]
        holder.textKtg.text = onProses.kategori
        holder.textName.text = onProses.name
        holder.textNo.text = onProses.phone
        holder.textPkt.text = onProses.paket
        holder.textQnt.text = onProses.kuantitas.toString()
    }
    fun setData(list: List<Order>) {
        proses.clear()
        Log.d("Debug Set Data", "RESPONSE: $list")
        proses.addAll(list)
    }

}