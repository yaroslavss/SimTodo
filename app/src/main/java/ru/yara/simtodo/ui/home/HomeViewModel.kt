package ru.yara.simtodo.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.yara.simtodo.data.model.EventList
import ru.yara.simtodo.data.repository.LocalRepositoryImpl
import ru.yara.simtodo.domain.model.Hour
import java.time.LocalDate

class HomeViewModel : ViewModel() {

    private var localRepository = LocalRepositoryImpl()

    private val now = LocalDate.now()
    private val _hourListLiveData = MutableLiveData<List<Hour>>()
    val hourListLiveData: LiveData<List<Hour>> = _hourListLiveData

    init {
        //getEventsForDay(now)
    }

    fun getEventsForDay(day: LocalDate, context: Context?) {
        val hourList: List<Hour> = (0..23).map { hour ->
            Hour(
                startHour = day.atStartOfDay().plusHours(hour.toLong()),
                endHour = day.atStartOfDay().plusHours((hour + 1).toLong())
            )
        }

        val eventList = getAllEventsFromJson(context)
        println("!!! $eventList")

        _hourListLiveData.postValue(hourList)
    }

    private fun getAllEventsFromJson(context: Context?): EventList {
        return localRepository.getAllEventsFromJson(context)
    }
}