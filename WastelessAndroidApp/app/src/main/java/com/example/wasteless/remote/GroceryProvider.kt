package com.example.wasteless.remote

import com.example.wasteless.remote.GroceryAPI
import com.example.wasteless.remote.Result
import com.example.wasteless.remote.model.GroceryList
import com.example.wasteless.remote.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

class GroceryProvider : KoinComponent {

    private val retrofit by inject<GroceryAPI>()

    suspend fun getUserLists(userId: Int) = withContext(Dispatchers.IO) {
        return@withContext try {
            Result.Success(retrofit.getUserLists(userId))
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }

    suspend fun attemptLogin(user: User) = withContext(Dispatchers.IO){
        return@withContext try{
            Result.Success(retrofit.attemptLogin(user))
        } catch (ex: Exception){
            Result.Error(ex)
        }
    }

    suspend fun addList(userId: Int, groceryList: GroceryList) = withContext(Dispatchers.IO){
        return@withContext try {
            Result.Success(retrofit.addList(userId, groceryList))
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }


}
