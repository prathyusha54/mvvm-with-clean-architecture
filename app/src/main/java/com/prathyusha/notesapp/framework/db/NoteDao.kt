package com.prathyusha.notesapp.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert(onConflict = REPLACE)
    suspend fun addNoteEntity(noteData: NoteData)

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteEntity(id: Long): NoteData?

    @Query("SELECT * FROM note")
    suspend fun getAllNoteEntries(): List<NoteData>

    @Delete
    suspend fun deleteNoteData(noteData: NoteData)
}