package ru.yara.simtodo.domain.repository

import ru.yara.simtodo.data.db.EventEntity
import java.time.LocalDateTime

interface DatabaseRepository {
    suspend fun insertAllEvents(list: List<EventEntity>)

    suspend fun insertEvent(eventEntity: EventEntity)

    fun getEventsForDay(dtStart: LocalDateTime, dtFinish: LocalDateTime): List<EventEntity>
}