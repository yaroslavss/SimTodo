package ru.yara.simtodo.domain.repository

import android.content.Context
import ru.yara.simtodo.domain.model.Event

interface LocalRepository {
    fun getAllEventsFromJson(context: Context?): List<Event>
}