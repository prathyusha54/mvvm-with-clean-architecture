package com.prathyusha.notesapp.presentation.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun getCreationDateAndTime(noteDate: Long): String {
        val sdf = SimpleDateFormat("MMM dd, HH:MM:SS")
        val result = Date(noteDate)
        return sdf.format(result)
    }

}