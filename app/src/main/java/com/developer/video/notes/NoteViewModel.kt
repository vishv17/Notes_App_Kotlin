package com.developer.video.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application):AndroidViewModel(application)
{
    val allNotes:LiveData<List<Note>>
    val repository : NoteRepository
    init {
        val database:NoteDatabase = NoteDatabase.getDatabase(application)
        val dao:NoteDao = database.getNoteDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteNote(note)
    }

    fun insertNote(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertNote(note)
    }
}