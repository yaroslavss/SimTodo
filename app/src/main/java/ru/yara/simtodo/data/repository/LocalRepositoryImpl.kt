package ru.yara.simtodo.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.yara.simtodo.data.model.EventList
import ru.yara.simtodo.domain.repository.LocalRepository

class LocalRepositoryImpl : LocalRepository {
    override fun getAllEventsFromJson(context: Context?): EventList {
        val jsonString = readJsonFromAssets(context)
        return parseJsonToModel(jsonString)
    }

    private fun readJsonFromAssets(context: Context?): String {
        return context?.assets?.open(JSON_DATA_FILE)?.bufferedReader()?.use { it.readText() } ?: ""
    }

    private fun parseJsonToModel(jsonString: String): EventList {
        val gson = Gson()
        return gson.fromJson(jsonString, object : TypeToken<EventList>() {}.type)
    }

    companion object {
        const val JSON_DATA_FILE = "events.json"
    }
}