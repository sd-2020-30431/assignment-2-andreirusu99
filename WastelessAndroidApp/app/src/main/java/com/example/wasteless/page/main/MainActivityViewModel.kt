package com.example.wasteless.page.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.wasteless.shared.utils.SingleLiveEvent
import com.example.wasteless.shared.event.BaseEvent

class MainActivityViewModel : ViewModel() {

    private val _cmd = SingleLiveEvent<Command>()
    val cmd: LiveData<Command>
        get() = _cmd

    companion object {
        private val _loggedInUserId = MutableLiveData<Int>()
        val loggedInUserId: LiveData<Int>
            get() = _loggedInUserId

        fun setLoggedInUserId(userId: Int) {
            _loggedInUserId.postValue(userId)
        }

        private val _selectedListId = MutableLiveData<Int>()
        val selectedListId: LiveData<Int>
            get() = _selectedListId

        fun setSelectedListId(listId: Int){
            _selectedListId.postValue(listId)
        }
    }

    sealed class Command : BaseEvent {
        class PerformNavAction(val direction: NavDirections) : Command()
        object LogOut : Command()
        object GoBack : Command()
    }
}
