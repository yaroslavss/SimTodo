package ru.yara.simtodo.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.yara.simtodo.data.db.EventEntity
import ru.yara.simtodo.data.mapper.EventMapper.toDomainEvent
import ru.yara.simtodo.data.mapper.EventMapper.toEventEntity
import ru.yara.simtodo.data.model.EventList
import ru.yara.simtodo.domain.model.Event
import ru.yara.simtodo.domain.repository.LocalRepository

class LocalRepositoryImpl : LocalRepository {
    override fun getAllEventsFromJson(context: Context?): List<EventEntity> {
        val jsonString = readJsonFromAssets(context)
        return parseJsonToEntity(jsonString)
    }

    private fun readJsonFromAssets(context: Context?): String {
        return context?.assets?.open(JSON_DATA_FILE)?.bufferedReader()?.use { it.readText() } ?: ""
    }

    private fun parseJsonToModel(jsonString: String): List<Event> {
        val gson = Gson()
        val eventList: EventList =
            gson.fromJson(jsonString, object : TypeToken<EventList>() {}.type)
        return eventList.map { it.toDomainEvent() }
    }

    private fun parseJsonToEntity(jsonString: String): List<EventEntity> {
        val gson = Gson()
        val eventList: EventList =
            gson.fromJson(jsonString, object : TypeToken<EventList>() {}.type)
        return eventList.map { it.toEventEntity() }
    }

    companion object {
        const val JSON_DATA_FILE = "events.json"
    }
}