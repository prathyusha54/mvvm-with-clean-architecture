package com.prathyusha.notesapp.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prathyusha.core.data.Note

@Entity(tableName = "note")
data class NoteData(
    val title: String,
    val content: String,
    @ColumnInfo(name = "creation_time")
    val creationTime: Long,
    @ColumnInfo(name = "update_time")
    val updateTime: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
) {
    companion object {
        fun fromNote(note: Note) =
            NoteData(note.title, note.content, note.creationTime, note.updatedTime, note.id)
    }

    fun toNote() = Note(title, content, creationTime, updateTime, id)
}