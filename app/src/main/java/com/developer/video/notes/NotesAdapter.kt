package com.developer.video.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.video.notes.databinding.ItemNoteBinding

class NotesAdapter(val context:Context, var noteList:List<Note>) :
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>()
{
    lateinit var itemNoteBinding: ItemNoteBinding
    lateinit var notesClickListener:INotesRVAdapter
    inner class NoteViewHolder(binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)
    {

    }

    fun registerNoteClickListener(notesClickListener:INotesRVAdapter)
    {
        this.notesClickListener = notesClickListener
    }

    fun doRefresh(noteList:List<Note>)
    {
        this.noteList = noteList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        itemNoteBinding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteViewHolder(itemNoteBinding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        var note:Note = noteList[holder.adapterPosition]
        itemNoteBinding.text.text = note.text
        itemNoteBinding.deleteButton.setOnClickListener {
            notesClickListener?.onItemClick(note)
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }
}

interface INotesRVAdapter
{
    fun onItemClick(note:Note)
}