package ru.yara.simtodo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.yara.simtodo.domain.model.Hour
import java.time.LocalDate

class HomeViewModel : ViewModel() {

    private val now = LocalDate.now()

    private val hourList: List<Hour> = (0..23).map { hour ->
        Hour(
            startHour = now.atStartOfDay().plusHours(hour.toLong()),
            endHour = now.atStartOfDay().plusHours((hour + 1).toLong())
        )
    }
    private val _hourListLiveData = MutableLiveData<List<Hour>>()
    val hourListLiveData: LiveData<List<Hour>> = _hourListLiveData

    init {
        _hourListLiveData.postValue(hourList)
    }
}