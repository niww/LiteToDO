package com.example.litetodo.data

import com.example.litetodo.data.entity.Note
import com.example.litetodo.data.provider.FireStoreProvider
import com.example.litetodo.data.provider.RemoteDataProvider

object NoteRepository {

    private val remoteProvider : RemoteDataProvider = FireStoreProvider()

    fun getNotes( ) = remoteProvider.subscribeToAllNotes()
    fun saveNote(note: Note)  = remoteProvider.saveNote(note)
    fun getNoteById(id: String) = remoteProvider.getNoteById(id)
}