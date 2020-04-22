package com.example.wasteless.shared.utils.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * Extension method used for observing a non null [LiveData].
 */
fun <T> LiveData<T>.observeNonNull(lifecycleOwner: LifecycleOwner, action: (T) -> Unit) = observe(
    lifecycleOwner, Observer { it?.let(action) })

operator fun <T> MutableLiveData<List<T>>.minusAssign(model: T) {
    value = value?.filter { it != model }
}
