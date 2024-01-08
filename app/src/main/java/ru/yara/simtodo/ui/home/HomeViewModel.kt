package ru.yara.simtodo.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.yara.simtodo.App
import ru.yara.simtodo.data.db.EventEntity
import ru.yara.simtodo.data.mapper.EventEntityMapper.toDomainEvent
import ru.yara.simtodo.data.repository.DatabaseRepositoryImpl
import ru.yara.simtodo.data.repository.LocalRepositoryImpl
import ru.yara.simtodo.domain.model.Hour
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject

class HomeViewModel : ViewModel() {

    private var localRepository = LocalRepositoryImpl()

    @Inject
    lateinit var databaseRepository: DatabaseRepositoryImpl

    private val _hourListLiveData = MutableLiveData<List<Hour>>()
    val hourListLiveData: LiveData<List<Hour>> = _hourListLiveData
    var currentDate: LocalDate? = null

    init {
        App.instance.dagger.inject(this)
    }

    suspend fun getEventsForDay(day: LocalDate, context: Context?) {
        val allEventList = getAllEventsFromJson(context)
        saveEventsIntoDb(allEventList)
        val eventList = getEventsForDayFromDB(
            day.atStartOfDay(),
            LocalTime.MAX.atDate(day)
        ).map { it.toDomainEvent() }

        val hourList: List<Hour> = (0..23).map { hour ->
            val start = day.atStartOfDay().plusHours(hour.toLong())
            val end = day.atStartOfDay().plusHours((hour + 1).toLong())
            Hour(
                startHour = start,
                endHour = end,
                eventList = eventList.filter { (it.dateStart >= start && it.dateStart < end) || (it.dateFinish > start && it.dateFinish <= end) }
            )
        }

        _hourListLiveData.postValue(hourList)
    }

    private fun getAllEventsFromJson(context: Context?): List<EventEntity> {
        return localRepository.getAllEventsFromJson(context)
    }

    private suspend fun saveEventsIntoDb(events: List<EventEntity>) =
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.insertAllEvents(events)
        }

    private fun getEventsForDayFromDB(dtStart: LocalDateTime, dtFinish: LocalDateTime) =
        databaseRepository.getEventsForDay(dtStart, dtFinish)
}