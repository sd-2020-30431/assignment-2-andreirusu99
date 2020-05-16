package com.example.wasteless.page.grocery_list

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wasteless.page.decorator.GreenReportDecorator
import com.example.wasteless.page.decorator.RedReportDecorator
import com.example.wasteless.page.main.MainActivityViewModel
import com.example.wasteless.page.report.MonthlyReport
import com.example.wasteless.page.report.Report
import com.example.wasteless.page.report.WeeklyReport
import com.example.wasteless.remote.GroceryProvider
import com.example.wasteless.remote.model.GroceryList
import com.example.wasteless.remote.model.ReportDTO
import com.example.wasteless.remote.model.User
import com.example.wasteless.remote.successOr
import com.example.wasteless.shared.utils.validators.ValidatorUtil
import kotlinx.coroutines.launch

class GroceryListsViewModel(private val groceryProvider: GroceryProvider) : ViewModel() {

    private var userId = -1
    var userCalories = 0

    var wasteLevel = MutableLiveData<Int>().apply { value = 0 }

    init {
        if (MainActivityViewModel.loggedInUserId.value != -1)
            userId = MainActivityViewModel.loggedInUserId.value!!
        getUserListsFromAPI()
        getUserWasteFromAPI()
    }

    private val _groceryLists = MutableLiveData<List<GroceryList>>()
    val groceryLists: LiveData<List<GroceryList>>
        get() = _groceryLists

    val newListName = MutableLiveData<String>().apply { value = "" }
    val newCalorieIntake = MutableLiveData<String>().apply { value = "" }

    private fun getUserListsFromAPI() {
        viewModelScope.launch {
            val lists = groceryProvider.getUserLists(userId)
            _groceryLists.value = lists.successOr(listOf())
            getUserCaloriesFromAPI()
            getUserWasteFromAPI()
        }
    }

    private fun getUserWasteFromAPI() {
        viewModelScope.launch {
            wasteLevel.postValue(groceryProvider.getUserWaste(userId).successOr(0))
        }
    }

    private fun getUserCaloriesFromAPI() {
        viewModelScope.launch {
            val users = groceryProvider.getUsers().successOr(listOf())
            var user = User()
            for (u: User in users) {
                if (u.id == userId) {
                    user = u
                }
            }
            userCalories = user.calorieIntake
        }
    }

    fun addListFromAPI(listName: String) {
        viewModelScope.launch {
            groceryProvider.addList(userId, GroceryList(listName = listName, userId = userId))
            getUserListsFromAPI()
            getUserWasteFromAPI()
        }
    }

    fun updateCalorieIntake() {
        viewModelScope.launch {
            if (ValidatorUtil.isNumberValid(newCalorieIntake.value)) {
                groceryProvider.updateUser(userId, newCalorieIntake.value?.toInt()!!)
            } else {
                println("Invalid calories!")
            }
            getUserWasteFromAPI()
            getUserCaloriesFromAPI()
        }
    }

    fun generateWeeklyReport(view: View) {
        viewModelScope.launch {
            val reportDTO = groceryProvider.getReport(userId, "WEEKLY").successOr(ReportDTO(0))
            val waste = reportDTO.wastedCalories
            val report = if (waste > userCalories) {
                RedReportDecorator(WeeklyReport(waste), waste)
            } else {
                GreenReportDecorator(WeeklyReport(waste), waste)
            }
            report.show(view.context)
        }
    }

    fun generateMonthlyReport(view: View) {
        viewModelScope.launch {
            val reportDTO = groceryProvider.getReport(userId, "MONTHLY").successOr(ReportDTO(0))
            val waste = reportDTO.wastedCalories
            val report = if (waste > userCalories) {
                RedReportDecorator(MonthlyReport(waste), waste)
            } else {
                GreenReportDecorator(MonthlyReport(waste), waste)
            }
            report.show(view.context)
        }
    }

}