package ru.yara.simtodo.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.yara.simtodo.data.repository.LocalRepositoryImpl
import ru.yara.simtodo.domain.model.Event
import ru.yara.simtodo.domain.model.Hour
import java.time.LocalDate

class HomeViewModel : ViewModel() {

    private var localRepository = LocalRepositoryImpl()

    private val _hourListLiveData = MutableLiveData<List<Hour>>()
    val hourListLiveData: LiveData<List<Hour>> = _hourListLiveData
    var currentDate: LocalDate? = null

    fun getEventsForDay(day: LocalDate, context: Context?) {
        val allEventList = getAllEventsFromJson(context)

        val hourList: List<Hour> = (0..23).map { hour ->
            val start = day.atStartOfDay().plusHours(hour.toLong())
            val end = day.atStartOfDay().plusHours((hour + 1).toLong())
            Hour(
                startHour = start,
                endHour = end,
                eventList = allEventList.filter { (it.dateStart >= start && it.dateStart < end) || (it.dateFinish > start && it.dateFinish <= end) }
            )
        }

        _hourListLiveData.postValue(hourList)
    }

    private fun getAllEventsFromJson(context: Context?): List<Event> {
        return localRepository.getAllEventsFromJson(context)
    }
}