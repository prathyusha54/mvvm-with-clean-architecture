package com.prathyusha.notesapp.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.prathyusha.core.data.Note
import com.prathyusha.notesapp.UseCases
import com.prathyusha.notesapp.framework.di.ApplicationModule
import com.prathyusha.notesapp.framework.di.DaggerViewModelComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteListViewModel(application: Application):AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var useCases: UseCases
    init {
        DaggerViewModelComponent
            .builder()
            .applicationModule(ApplicationModule(getApplication()))
            .build()
            .inject(this)
    }

    /*val repository = NoteRepository(RoomNoteDataSource(application))

    val useCases = UseCases(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        removeNote(repository)
    )*/

    val notes = MutableLiveData<List<Note>>()

    fun getNotes() {
        coroutineScope.launch {
            val notesList = useCases.getAllNotes()
            notes.postValue(notesList)
        }
    }
}