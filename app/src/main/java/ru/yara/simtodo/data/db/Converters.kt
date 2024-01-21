package ru.yara.simtodo.data.db

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.ZoneOffset

class Converters {

    private val zoneOffset = ZoneOffset.systemDefault().rules.getOffset(LocalDateTime.now())

    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDateTime? {
        return value?.let { LocalDateTime.ofEpochSecond(value, 0, zoneOffset) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): Long? {
        return date?.toEpochSecond(zoneOffset)
    }
}