package com.example.litetodo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.litetodo.data.entity.Note
import com.example.litetodo.data.NoteRepository
import com.example.litetodo.data.model.NoteResult
import com.example.litetodo.ui.base.BaseViewModel

class MainViewModel: BaseViewModel<List<Note>?, MainViewState>() {

    private val notesObserver = object : Observer<NoteResult> {
        override fun onChanged(t: NoteResult?) {
            t ?: return
            when(t){
                is NoteResult.Success<*> -> {
                    viewStateLiveData.value  = MainViewState(notes = t.data as? List<Note>)
                }
                is NoteResult.Error ->
                    viewStateLiveData.value = MainViewState( error = t.error)
            }
        }
    }

    private val repositoryNotes = NoteRepository.getNotes()

    init {
//        viewStateLiveData.value = MainViewState()// fixme
        repositoryNotes.observeForever { notesObserver }

    }

    fun viewState(): LiveData<MainViewState> = viewStateLiveData

    override fun onCleared() {
        repositoryNotes.removeObserver(notesObserver)
        super.onCleared()
    }
}