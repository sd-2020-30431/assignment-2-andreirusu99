package server.service.user

import server.db.model.User

interface UserService {

    fun attemptLogin(firstName: String, lastName: String ,password: String): Int

    fun getAllUsers(): List<User>

    fun getWaste(userId: Int): Int

    fun addUser(firstName: String, lastName: String, password: String, calorieIntake: Int)

    fun updateUser(id: Int, calorieIntake: Int)

    fun deleteUser(id: Int)

    fun syncDatabase(userList: MutableList<User>)
}