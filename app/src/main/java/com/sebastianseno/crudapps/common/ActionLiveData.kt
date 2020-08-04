package com.sebastianseno.crudapps.common

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class ActionLiveData<T>(private val stopObservingOn: ((T) -> Boolean)? = null) :
    MutableLiveData<T>() {

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasObservers()) {
            throw Throwable("Only one observer at a time may subscribe to a ActionLiveData")
        }

        super.observe(owner, object : Observer<T> {
            override fun onChanged(data: T) {
                if (data == null) return
                observer.onChanged(data)
                value = null

                stopObservingOn?.let {
                    if (it.invoke(data)) removeObserver(this)
                }
            }
        })
    }

    @MainThread
    fun sendAction(data: T) {
        value = data
    }
}