package ru.yara.simtodo.domain.repository

import android.content.Context
import ru.yara.simtodo.data.model.EventList

interface LocalRepository {
    fun getAllEventsFromJson(context: Context?): EventList
}