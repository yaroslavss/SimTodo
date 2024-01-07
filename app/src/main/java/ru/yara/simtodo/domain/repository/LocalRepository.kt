package ru.yara.simtodo.domain.repository

import android.content.Context
import ru.yara.simtodo.data.db.EventEntity

interface LocalRepository {
    fun getAllEventsFromJson(context: Context?): List<EventEntity>
}