package ru.yara.simtodo.domain.repository

import ru.yara.simtodo.data.db.EventEntity

interface DatabaseRepository {
    suspend fun insertAllEvents(list: List<EventEntity>)
}