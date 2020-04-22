package com.example.wasteless.application

import com.example.wasteless.page.grocery_list.GroceryListViewModel
import com.example.wasteless.page.main.MainActivityViewModel
import com.example.wasteless.remote.GroceryAPI
import com.example.wasteless.remote.GroceryProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModules {
    private val viewModels = module {
        viewModel { MainActivityViewModel() }
        viewModel { GroceryListViewModel(get()) }
    }

    private val remoteModule = module {
        single { GroceryAPI.create()}
        single { GroceryProvider()}
    }
    val modules = viewModels + remoteModule
}
