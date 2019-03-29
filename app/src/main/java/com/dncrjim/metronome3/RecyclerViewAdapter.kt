package com.dncrjim.metronome3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dncrjim.metronome3.data.Tempo


class RecyclerViewAdapter(val launchTempoActivity: (Int?) -> Unit) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    val list = db.tempoDao().getAllTempos()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tempo_row, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.updateView(position)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.textView)
        val divider = view.findViewById<View>(R.id.divider)


        //Todo: launches TempoActivity passing the id of the list item, not the tempo

        fun updateView(index: Int) {
            textView.text = Integer.toString(list[index].tempo)
            view.setOnClickListener {
                launchTempoActivity(list[index].id)
            }
        }
    }
}
