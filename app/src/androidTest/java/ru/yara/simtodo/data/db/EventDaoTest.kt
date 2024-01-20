package ru.yara.simtodo.data.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDateTime
import java.time.ZoneOffset

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class EventDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: EventDao

    private val zoneOffset = ZoneOffset.systemDefault().rules.getOffset(LocalDateTime.now())

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.eventDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertEventEntity() = runTest {
        val dtStart = LocalDateTime.ofEpochSecond(
            1705557600L, 0,
            zoneOffset
        )
        val dtFinish = LocalDateTime.ofEpochSecond(
            1705559400L, 0,
            zoneOffset
        )
        val eventEntity = EventEntity(
            id = 1,
            dateStart = dtStart,
            dateFinish = dtFinish,
            name = "Test event",
            description = "Test event description"
        )

        dao.insertEvent(eventEntity)

        val allEvents = dao.getEventsForDay(dtStart, dtFinish)

        assertThat(allEvents).contains(eventEntity)
    }
}