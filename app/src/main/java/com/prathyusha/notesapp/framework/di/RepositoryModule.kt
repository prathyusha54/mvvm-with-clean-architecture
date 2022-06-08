package com.prathyusha.notesapp.framework.di

import android.app.Application
import com.prathyusha.core.repository.NoteRepository
import com.prathyusha.notesapp.framework.RoomNoteDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(application: Application) = NoteRepository(RoomNoteDataSource(application))
}