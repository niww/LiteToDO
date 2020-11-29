package com.example.litetodo.ui.main.note

import androidx.lifecycle.ViewModel
import com.example.litetodo.data.entity.Note
import com.example.litetodo.data.entity.NoteRepository

class NoteViewModel : ViewModel(){
    private var pendingNOte: Note? = null

    fun  save(note: Note){
        pendingNOte = note
    }

    override fun onCleared() {
        pendingNOte?.let {
            NoteRepository.saveNote(it)
        }
    }
}