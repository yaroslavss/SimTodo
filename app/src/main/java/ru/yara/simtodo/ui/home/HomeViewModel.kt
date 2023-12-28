package ru.yara.simtodo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.yara.simtodo.domain.model.Hour

class HomeViewModel : ViewModel() {

    private val hourList: List<Hour> = (0..23).map {
        Hour(
            startHour = it.toString() + ":00",
            endHour = (it + 1).toString() + ":00"
        )
    }
    private val _hourListLiveData = MutableLiveData<List<Hour>>()
    val hourListLiveData: LiveData<List<Hour>> = _hourListLiveData

    init {
        _hourListLiveData.postValue(hourList)
    }
}