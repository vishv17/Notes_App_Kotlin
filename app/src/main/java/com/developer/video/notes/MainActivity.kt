package com.developer.video.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developer.video.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), INotesRVAdapter {
    lateinit var viewModel: NoteViewModel
    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var noteAdapter:NotesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
//        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {
            setAdapter(it)
        })

        activityMainBinding.btnSubmit.setOnClickListener {
            val noteText = activityMainBinding.etNote.text.toString()
            if(!noteText.isBlank() || noteText.isNotBlank())
            {
                viewModel.insertNote(Note(noteText))
            }
        }
    }

    private fun setAdapter(noteList: List<Note>?) {
        if(noteAdapter == null)
        {
            noteAdapter = noteList?.let { NotesAdapter(this, it) }!!
            noteAdapter.registerNoteClickListener(this)
            with(activityMainBinding)
            {
                rvNotes.layoutManager = LinearLayoutManager(this@MainActivity)
                rvNotes.adapter = noteAdapter
            }
        }
        else
        {
            noteAdapter.doRefresh(noteList!!)
        }
    }

    override fun onItemClick(note: Note) {
        viewModel.deleteNote(note)
    }
}