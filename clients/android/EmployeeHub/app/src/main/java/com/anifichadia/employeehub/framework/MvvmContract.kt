package com.anifichadia.employeehub.framework

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext
import androidx.lifecycle.ViewModel as AndroidXViewModel

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
interface MvvmContract {

    interface Navigation

    abstract class ViewModel : AndroidXViewModel(), CoroutineScope {
        private val viewModelCoroutineSupervisor = SupervisorJob()

        override val coroutineContext: CoroutineContext
            get() = Dispatchers.Main.immediate + viewModelCoroutineSupervisor


        override fun onCleared() {
            super.onCleared()
            viewModelCoroutineSupervisor.cancel()
        }
    }
}
