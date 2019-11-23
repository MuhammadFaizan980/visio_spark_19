package com.squadtechs.markhor.visiospark19.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squadtechs.markhor.visiospark19.R
import com.squadtechs.markhor.visiospark19.models.ModelMain

class ListAdapter(
    private val context: Context,
    private val list: List<ModelMain>,
    private val key: Int
) :
    RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_design,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = 5

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val obj = list[position]
        when (key) {
            0 -> {
                if (obj.type == "OUTGOING") {
                    holder.txtNumber.text = obj.number
                    holder.txtDuration.text = obj.duration
                }
            }
            1 -> {
                if (obj.type == "INCOMING") {
                    holder.txtNumber.text = obj.number
                    holder.txtDuration.text = obj.duration
                }
            }
            3 -> {
                if (obj.type == "MISSED") {
                    holder.txtNumber.text = obj.number
                    holder.txtDuration.text = obj.duration
                }
            }
            else -> {
                holder.txtNumber.text = obj.number
                holder.txtDuration.text = obj.duration
            }
        }
    }

    class MyViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val txtNumber: TextView = mView.findViewById(R.id.txt_number)
        val txtDuration: TextView = mView.findViewById(R.id.txt_duration)
    }
}