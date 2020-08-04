package com.sebastianseno.crudapps.inject

import androidx.fragment.app.Fragment
import androidx.lifecycle.*

fun <T : Any, L : LiveData<T>> Fragment.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer(body))
}

inline fun <reified T : ViewModel> BaseActivity.getViewModel() =
    lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this, viewModelFactory)[T::class.java]
    }

inline fun <reified T : ViewModel> BaseFragment.getViewModel() =
    lazy(LazyThreadSafetyMode.NONE) {
      ViewModelProvider(this, viewModelFactory)[T::class.java]
    }