package com.gb.stopwatch.di

import com.gb.stopwatch.model.*
import com.gb.stopwatch.view.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainScreen = module {
    factory<TimestampProvider> {
        object : TimestampProvider {
            override fun getMilliseconds(): Long {
                return System.currentTimeMillis()
            }
        }
    }
    single { CoroutineScope(Dispatchers.Main + SupervisorJob()) }
    single { StopwatchListOrchestrator(stopwatchStateHolder = get(), scope = get()) }

    single { StopwatchStateCalculator(timestampProvider = get(), elapsedTimeCalculator = get()) }
    single { ElapsedTimeCalculator(timestampProvider = get()) }
    single { TimestampMillisecondsFormatter() }
    single {
        StopwatchStateHolder(
            stopwatchStateCalculator = get(),
            elapsedTimeCalculator = get(),
            timestampMillisecondsFormatter = get()
        )
    }
    viewModel { MainViewModel(stopwatchListOrchestrator = get()) }
}
