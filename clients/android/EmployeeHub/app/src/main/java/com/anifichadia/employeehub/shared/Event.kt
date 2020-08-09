package com.anifichadia.employeehub.shared

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer as AndroidXObserver

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
class Event<DataT>(
    private val data: DataT
) {
    var handled = false
        private set

    fun consume(): DataT? = if (!handled) {
        handled = true
        data
    } else {
        null
    }

    class Observer<DataT>(private val handler: (DataT) -> Unit) : AndroidXObserver<Event<DataT>> {
        override fun onChanged(event: Event<DataT>?) {
            event?.consume()?.let { data -> handler(data) }
        }
    }
}


fun <DataT> MutableLiveData<Event<DataT>>.observeEvent(owner: LifecycleOwner, handler: (DataT) -> Unit) {
    observe(owner, Event.Observer(handler))
}
