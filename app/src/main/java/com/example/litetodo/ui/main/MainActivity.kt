package com.example.litetodo.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.litetodo.R
import com.example.litetodo.data.entity.NotesAdapter
import com.example.litetodo.ui.main.note.NoteActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvNotes = findViewById<RecyclerView>(R.id.rv_notes)

        setSupportActionBar(findViewById(R.id.toolbar))

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        rvNotes.layoutManager =
            GridLayoutManager(this, 2)

        adapter = NotesAdapter{note ->
            NoteActivity.start(this, note)
        }
        rvNotes.adapter = adapter

        viewModel.viewState().observe(this, Observer {
            it?.let {
                adapter.notes = it.notes
            }
        })

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            NoteActivity.start(this)
        }
    }

}