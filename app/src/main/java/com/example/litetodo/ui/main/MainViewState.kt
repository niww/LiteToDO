package com.example.litetodo.ui.main

import com.example.litetodo.data.entity.Note
import com.example.litetodo.ui.base.BaseViewState

class MainViewState(val notes: List<Note>? = null , error: Throwable? = null) :
    BaseViewState<List<Note>?>(notes, error)