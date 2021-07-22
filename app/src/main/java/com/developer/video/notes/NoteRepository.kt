package com.developer.video.notes

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao)
{
    val allNotes:LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insertNote(note:Note)
    {
        noteDao.insert(note)
    }

    suspend fun deleteNote(note:Note)
    {
        noteDao.delete(note)
    }
}