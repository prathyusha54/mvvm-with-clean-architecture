package com.prathyusha.notesapp.framework

import android.content.Context
import com.prathyusha.core.data.Note
import com.prathyusha.core.repository.NoteDataSource
import com.prathyusha.notesapp.framework.db.DatabaseService
import com.prathyusha.notesapp.framework.db.NoteData

class RoomNoteDataSource(context: Context): NoteDataSource {
    val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteData.fromNote(note))

    override suspend fun get(id: Long): Note? = noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll(): List<Note> = noteDao.getAllNoteEntries().map { it.toNote() }

    override suspend fun removeNote(note: Note) = noteDao.deleteNoteData(NoteData.fromNote(note))
}