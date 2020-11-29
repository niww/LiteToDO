package com.example.litetodo.data.entity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

object NoteRepository {

    private val notesLiveData = MutableLiveData<List<Note>>()

    private val notes: MutableList<Note> = mutableListOf(
        Note( UUID.randomUUID().toString(),"Note1", "Text 1", Note.Color.WHITE),
        Note( UUID.randomUUID().toString(),"Note1", "Text 1", Note.Color.BLUE),
        Note( UUID.randomUUID().toString(),"Note1", "Text 1", Note.Color.GREEN),
        Note( UUID.randomUUID().toString(),"Note1", "Text 1", Note.Color.PINK),
        Note( UUID.randomUUID().toString(),"Note1", "Text 1", Note.Color.VIOLET),
        Note( UUID.randomUUID().toString(),"Note1", "Text 1", Note.Color.RED)
    )

    init {
        notesLiveData.value = notes
    }

    fun saveNote(note: Note){
        addOrReplace(note)
        notesLiveData.value = notes
    }

    private fun addOrReplace(note: Note){
        for (i in notes.indices)
            if (notes[i] == note){
                notes[i] = note
                return
            }
    }

    fun getNotes(): LiveData<List<Note>>{
        return  notesLiveData
    }
}