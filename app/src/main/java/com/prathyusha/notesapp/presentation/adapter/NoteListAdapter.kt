package com.prathyusha.notesapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.prathyusha.core.data.Note
import com.prathyusha.notesapp.R
import com.prathyusha.notesapp.presentation.NoteListInterface
import com.prathyusha.notesapp.presentation.utils.DateUtils

class NoteListAdapter(var noteList: ArrayList<Note>, val noteListInterface: NoteListInterface) : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateNotes(newList: List<Note>) {
        noteList.clear()
        noteList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = NoteViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
    )

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(noteList[position])
    }

    override fun getItemCount() = noteList.size

    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val clLayout = view.findViewById<ViewGroup>(R.id.clLayout)
        private val title = view.findViewById<TextView>(R.id.noteTitle)
        private val description = view.findViewById<TextView>(R.id.noteDescription)
        private val dateAndTime = view.findViewById<TextView>(R.id.noteDateAndTime)

        fun bind(note: Note) {
                title.text = note.title
                description.text = note.content
                dateAndTime.text = DateUtils.getCreationDateAndTime(note.creationTime)

            clLayout.setOnClickListener {
                noteListInterface.onClick(note.id)
            }
        }
    }
}