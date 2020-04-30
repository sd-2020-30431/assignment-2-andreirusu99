package com.example.wasteless.application

import com.example.wasteless.page.grocery_item.GroceryItemsViewModel
import com.example.wasteless.page.grocery_list.GroceryListsViewModel
import com.example.wasteless.page.login.LoginViewModel
import com.example.wasteless.page.main.MainActivityViewModel
import com.example.wasteless.remote.GroceryAPI
import com.example.wasteless.remote.GroceryProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModules {
    private val viewModels = module {
        viewModel { MainActivityViewModel() }

        viewModel { LoginViewModel(get()) }
        viewModel { GroceryListsViewModel(get()) }
        viewModel { GroceryItemsViewModel(get()) }
    }

    private val remoteModule = module {
        single { GroceryAPI.create()}
        single { GroceryProvider()}
    }
    val modules = viewModels + remoteModule
}
