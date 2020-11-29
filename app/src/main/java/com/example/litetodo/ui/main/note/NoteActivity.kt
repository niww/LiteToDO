package com.example.litetodo.ui.main.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.litetodo.R
import com.example.litetodo.data.entity.Note
import kotlinx.android.synthetic.main.activity_note.*
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : AppCompatActivity() {
    companion object {
        private val EXTRA_NOTE = NoteActivity::class.java.name + "extra.NOTE"
        private const val DATE_TIME_FORMAT = "dd.MM.yy HH:mm"
        private const val SAVE_DELAY = 2000L

        fun start(context: Context, note: Note? = null) {
            val intent = Intent(context, NoteActivity::class.java)
            intent.putExtra(EXTRA_NOTE, note)
            context.startActivity(intent)
        }
    }

    private var note: Note? = null
    lateinit var viewModel: NoteViewModel

    val textChangeListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(p0: Editable?) {
            saveNote()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        note = intent.getParcelableExtra(EXTRA_NOTE)
        setSupportActionBar(toolbarNote)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        supportActionBar?.title = note?.let {
            SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(it.lastChanged)
        } ?: getString(R.string.app_name)
        initView()
    }

    fun initView() {
        note?.let {
            note_text.setText(it.title)
            note_text.setText(it.text)
            val color = when (note!!.color) {
                Note.Color.WHITE -> R.color.white
                Note.Color.YELLOW -> R.color.purple_500
                Note.Color.GREEN -> R.color.purple_500
                Note.Color.BLUE -> R.color.teal_200
                Note.Color.RED -> R.color.teal_700
                Note.Color.VIOLET -> R.color.white
                Note.Color.PINK -> R.color.purple_500
            }
        }

        note_title.addTextChangedListener(textChangeListener)
        note_text.addTextChangedListener(textChangeListener)
    }


    fun saveNote() {
        if (note_text.text == null || note_text.text!!.length < 3) return
        Handler().postDelayed({
            note = note?.copy(
                title = note_title.text.toString(),
                text = note_text.text.toString(),
                lastChanged = Date()
            ) ?: createNewNote()

        }, SAVE_DELAY)
    }

    private fun createNewNote(): Note = Note(
        UUID.randomUUID().toString(),
        note_title.text.toString(),
        note_text.text.toString(),
    )

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){

        android.R.id.home -> {
            onBackPressed()
            true
        }
        else ->  super.onOptionsItemSelected(item)
    }

}