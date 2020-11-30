package com.example.litetodo.data.provider

import androidx.lifecycle.LiveData
import com.example.litetodo.data.entity.Note
import com.example.litetodo.data.model.NoteResult

interface RemoteDataProvider {
    fun  subscribeToAllNotes(): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun saveNote(note: Note): LiveData<NoteResult>
}