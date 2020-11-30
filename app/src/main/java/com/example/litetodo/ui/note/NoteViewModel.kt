package com.example.litetodo.ui.note

import androidx.lifecycle.Observer
import com.example.litetodo.data.entity.Note
import com.example.litetodo.data.NoteRepository
import com.example.litetodo.data.model.NoteResult
import com.example.litetodo.ui.base.BaseViewModel

class NoteViewModel : BaseViewModel<Note?, NoteViewState>(){

    init {

        viewStateLiveData.value = NoteViewState()
    }

    private var pendingNOte: Note? = null

    fun  save(note: Note){
        pendingNOte = note
    }

    fun loadNote(noteId:String){
        NoteRepository.getNoteById(noteId).observeForever {
            object : Observer<NoteResult> {
                override fun onChanged(t: NoteResult?) {
                    t ?: return
                    when(t){
                        is NoteResult.Success<*> -> {
                            viewStateLiveData.value  = NoteViewState(note = t.data as? Note)
                        }
                        is NoteResult.Error ->
                            viewStateLiveData.value = NoteViewState( error = t.error)
                    }
                }
            } }
    }

    override fun onCleared() {
        pendingNOte?.let {
            NoteRepository.saveNote(it)
        }
    }
}