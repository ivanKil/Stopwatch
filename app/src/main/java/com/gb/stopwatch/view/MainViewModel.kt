package com.gb.stopwatch.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.stopwatch.model.StopwatchListOrchestrator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class MainViewModel(
    private val stopwatchListOrchestrator: StopwatchListOrchestrator
) : ViewModel() {
    private val _ldText: MutableLiveData<String> = MutableLiveData()
    val ldText: LiveData<String>
        get() = _ldText

    fun start() = stopwatchListOrchestrator.start()

    fun pause() = stopwatchListOrchestrator.pause()

    fun stop() = stopwatchListOrchestrator.stop()

    fun init() {
        CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
            stopwatchListOrchestrator.ticker.collect { _ldText.postValue(it) }
        }
    }
}