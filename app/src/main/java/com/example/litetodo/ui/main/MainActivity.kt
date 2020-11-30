package com.example.litetodo.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.litetodo.R
import com.example.litetodo.data.entity.Note
import com.example.litetodo.data.entity.NotesAdapter
import com.example.litetodo.ui.base.BaseActivity
import com.example.litetodo.ui.note.NoteActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<List<Note>?, MainViewState>() {

    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    override val layoutRes: Int = R.layout.activity_main
    lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvNotes = rv_notes

        setSupportActionBar(findViewById(R.id.toolbar))

        rvNotes.layoutManager =
            GridLayoutManager(this, 2)

        adapter = NotesAdapter{note ->
            NoteActivity.start(this, note.id)
        }
        rvNotes.adapter = adapter

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            NoteActivity.start(this)
        }
    }

    override fun renderData(data: List<Note>?) {
        data?.let {
            adapter.notes = it
        }
    }

}