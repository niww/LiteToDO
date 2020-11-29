package com.example.litetodo.data.entity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.litetodo.R

class NotesAdapter(val onItemViewClick: ((note: Note) -> Unit)? = null  ): RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(notes[position])
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(note: Note)  = with(itemView){
            findViewById<TextView>(R.id.tv_title).text = note.title
            findViewById<TextView>(R.id.tv_text).text = note.text

            val color = when(note.color){
                Note.Color.WHITE -> R.color.white
                Note.Color.YELLOW ->R.color.purple_500
                Note.Color.GREEN ->R.color.purple_500
                Note.Color.BLUE ->R.color.teal_200
                Note.Color.RED ->R.color.teal_700
                Note.Color.VIOLET ->R.color.white
                Note.Color.PINK -> R.color.purple_500
            }

            (this as CardView).setCardBackgroundColor(ContextCompat.getColor(itemView.context, color))

            itemView.setOnClickListener {
                onItemViewClick?.invoke(note)
            }
        }
    }
}