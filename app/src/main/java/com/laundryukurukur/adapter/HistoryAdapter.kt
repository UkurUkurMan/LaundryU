package com.laundryukurukur.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.laundryukurukur.R
import com.laundryukurukur.database.Order

class HistoryAdapter (private val rwyt: ArrayList<Order>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    class HistoryViewHolder(view: View) :  RecyclerView.ViewHolder(view) {
        val textKtg: TextView = view.findViewById(R.id.textKtg)
        val textName: TextView = view.findViewById(R.id.textNama)
        val textNo: TextView = view.findViewById(R.id.textNo)
        val textPkt: TextView = view.findViewById(R.id.textPkt)
        val textHrg: TextView = view.findViewById(R.id.textHrg)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view_history, parent, false)
        return HistoryViewHolder(v)
    }
    override fun getItemCount(): Int {
        return rwyt.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val onProses = rwyt[position]
        holder.textKtg.text = onProses.kategori
        holder.textName.text = onProses.name
        holder.textNo.text = onProses.phone
        holder.textPkt.text = onProses.paket
        holder.textHrg.text = onProses.biaya.toString()
    }
    fun setData(list: List<Order>) {
        rwyt.clear()
        Log.d("Debug Set Data", "RESPONSE: $list")
        rwyt.addAll(list)
    }

}