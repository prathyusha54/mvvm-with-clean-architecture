package com.prathyusha.core.usecase

import com.prathyusha.core.data.Note
import com.prathyusha.core.repository.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}