package ru.yara.simtodo.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.time.LocalDateTime

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEvents(list: List<EventEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(eventEntity: EventEntity)

    @Query("SELECT * FROM events WHERE date_start>=:dtStart AND date_finish<=:dtFinish")
    fun getEventsForDay(dtStart: LocalDateTime, dtFinish: LocalDateTime): List<EventEntity>
}