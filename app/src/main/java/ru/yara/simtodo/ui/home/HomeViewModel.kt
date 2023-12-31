package ru.yara.simtodo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.yara.simtodo.domain.model.Hour
import java.time.LocalDate

class HomeViewModel : ViewModel() {

    private val now = LocalDate.now()
    private val _hourListLiveData = MutableLiveData<List<Hour>>()
    val hourListLiveData: LiveData<List<Hour>> = _hourListLiveData

    init {
        getEventsForDay(now)
    }

    fun getEventsForDay(day: LocalDate) {
        val hourList: List<Hour> = (0..23).map { hour ->
            Hour(
                startHour = day.atStartOfDay().plusHours(hour.toLong()),
                endHour = day.atStartOfDay().plusHours((hour + 1).toLong())
            )
        }
        _hourListLiveData.postValue(hourList)
    }
}