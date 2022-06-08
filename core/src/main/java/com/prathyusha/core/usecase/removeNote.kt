package com.prathyusha.core.usecase

import com.prathyusha.core.data.Note
import com.prathyusha.core.repository.NoteRepository

class removeNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.removeNote(note)
}