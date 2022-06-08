package com.prathyusha.notesapp.framework.di

import com.prathyusha.notesapp.framework.NoteListViewModel
import com.prathyusha.notesapp.framework.NoteVieModel
import dagger.Component

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {
    fun inject(noteVieModel: NoteVieModel)
    fun inject(noteListViewModel: NoteListViewModel)
}