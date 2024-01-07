package ru.yara.simtodo.data.repository

import ru.yara.simtodo.data.db.EventDao
import ru.yara.simtodo.data.db.EventEntity
import ru.yara.simtodo.domain.repository.DatabaseRepository

class DatabaseRepositoryImpl(private val eventDao: EventDao) : DatabaseRepository {

    override suspend fun insertAllEvents(list: List<EventEntity>) {
        eventDao.insertAllEvents(list)
    }
}