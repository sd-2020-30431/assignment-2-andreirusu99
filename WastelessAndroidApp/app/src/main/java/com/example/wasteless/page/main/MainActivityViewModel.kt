package com.example.wasteless.page.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.wasteless.shared.utils.SingleLiveEvent
import com.example.wasteless.shared.event.BaseEvent

const val DELAY = 3000L

class MainActivityViewModel : ViewModel() {

    private val _cmd = SingleLiveEvent<Command>()
    val cmd: LiveData<Command>
        get() = _cmd

    sealed class Command : BaseEvent {
        class PerformNavAction(val direction: NavDirections) : Command()
        object LogOut : Command()
        object GoBack : Command()
    }
}
