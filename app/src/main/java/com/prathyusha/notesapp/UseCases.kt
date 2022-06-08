package com.prathyusha.notesapp

import com.prathyusha.core.usecase.AddNote
import com.prathyusha.core.usecase.GetAllNotes
import com.prathyusha.core.usecase.GetNote
import com.prathyusha.core.usecase.removeNote

data class UseCases(
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNote: removeNote
)