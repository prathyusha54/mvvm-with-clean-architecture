package com.prathyusha.core.repository

import com.prathyusha.core.data.Note

interface NoteDataSource {
    suspend fun add(note:Note)

    suspend fun get(id: Long): Note?

    suspend fun getAll(): List<Note>

    suspend fun removeNote(note: Note)
}