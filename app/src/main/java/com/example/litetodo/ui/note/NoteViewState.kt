package com.example.litetodo.ui.note

import com.example.litetodo.data.entity.Note
import com.example.litetodo.ui.base.BaseViewState

class NoteViewState(note: Note? = null, error: Throwable? = null) :
    BaseViewState<Note?>(note, error) {
}