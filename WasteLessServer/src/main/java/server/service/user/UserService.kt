package server.service.user

import server.db.model.User

interface UserService {

    fun getAllUsers(): List<User>

    fun addUser(firstName: String, lastName: String, password: String, calorieIntake: Int)

    fun updateUser(id: Int, firstName: String, lastName: String, password: String, calorieIntake: Int)

    fun deleteUser(id: Int)

    fun syncDatabase(userList: MutableList<User>)
}