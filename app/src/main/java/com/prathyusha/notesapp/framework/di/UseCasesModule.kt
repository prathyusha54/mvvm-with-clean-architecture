package com.prathyusha.notesapp.framework.di

import com.prathyusha.core.repository.NoteRepository
import com.prathyusha.core.usecase.AddNote
import com.prathyusha.core.usecase.GetAllNotes
import com.prathyusha.core.usecase.GetNote
import com.prathyusha.core.usecase.removeNote
import com.prathyusha.notesapp.UseCases
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    fun getUseCases(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        removeNote(repository)
    )
}