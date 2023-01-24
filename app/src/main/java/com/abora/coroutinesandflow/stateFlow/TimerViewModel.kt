package com.abora.coroutinesandflow.stateFlow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TimerViewModel : ViewModel() {

    //LiveData
    private val _timerLiveData = MutableLiveData<Int>()
    val timerLiveData: MutableLiveData<Int> = _timerLiveData

    fun stateTimerLiveData() {
        viewModelScope.launch {
            val list= listOf<Int>(1,1,1,2,2,3,4,5,6,6,7,8,8,8,9)
            for (i in list){
                _timerLiveData.value=i
                delay(1000)
            }
        }
    }

    //StateFlow
    private val _timerStateFlow = MutableStateFlow<Int>(0)
    val timerStateFlow: MutableStateFlow<Int> = _timerStateFlow

    fun stateTimeStateFlow() {
        viewModelScope.launch {
            val list= listOf<Int>(1,1,1,2,2,3,4,5,6,6,7,8,8,8,9)
            for (i in list){
                timerStateFlow.emit(i)
                delay(1000)
            }
        }
    }

}