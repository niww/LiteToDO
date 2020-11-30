package com.example.litetodo.data.provider

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.litetodo.data.entity.Note
import com.example.litetodo.data.model.NoteResult
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot

class FireStoreProvider : RemoteDataProvider {

    companion object {
        private const val NOTES_COLLECTION = "notes"

    }

    private val store:FirebaseFirestore = FirebaseFirestore.getInstance()
    private val noteReference = store.collection(NOTES_COLLECTION)


    override fun subscribeToAllNotes(): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()
        noteReference.addSnapshotListener { snapshot, error ->
            error?.let {
                result.value = NoteResult.Error(error)
            } ?: let {
                snapshot?.let {
                    val notes = mutableListOf<Note>()
                    for (doc: QueryDocumentSnapshot in snapshot) {
                        notes.add(doc.toObject(Note::class.java))
                    }
                    result.value = NoteResult.Success(notes)
                }
            }
        }
        return result
    }

    override fun getNoteById(id: String): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()
        noteReference.document(id).get()
            .addOnSuccessListener {
                result.value = NoteResult.Success(it.toObject(Note::class.java))
            }.addOnFailureListener {
                result.value = NoteResult.Error(it)
            }

        return result
    }

    override fun saveNote(note: Note): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()
        noteReference.document(note.id).set(note)
            .addOnSuccessListener {
                result.value = NoteResult.Success(note)
            }.addOnFailureListener {
                result.value = NoteResult.Error(it)
            }

        return result
    }
}