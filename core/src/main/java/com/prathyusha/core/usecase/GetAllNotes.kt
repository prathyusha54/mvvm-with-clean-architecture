package com.prathyusha.core.usecase

import com.prathyusha.core.repository.NoteRepository

class GetAllNotes(private val noteRepository: NoteRepository) {

    suspend operator fun invoke() = noteRepository.getAllNotes()
}