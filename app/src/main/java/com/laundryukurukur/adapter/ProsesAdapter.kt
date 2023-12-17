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
    class ProsesViewHolder(val view: View) :  RecyclerView.ViewHolder(view) {
        val textview = view.findViewById<TextView>(R.id.textKtg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProsesViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return ProsesViewHolder(v)
    }
    override fun getItemCount() = proses.size

    override fun onBindViewHolder(holder: ProsesViewHolder, position: Int) {
        val onProses = proses[position]
        holder.textview.text = onProses.kategori
        Log.d("HOLDER:", "Response: ${onProses.kategori}")
    }
    fun setData(list: List<Order>) {
        proses.clear()
        Log.d("Debug Set Data", "RESPONSE: $list")
        proses.addAll(list)
        notifyDataSetChanged()
    }

}