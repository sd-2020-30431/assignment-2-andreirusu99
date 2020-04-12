package server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import server.db.model.User
import server.db.repository.UserRepository

@Service
class UserServiceImpl : UserService {

    @Autowired
    private lateinit var userRepo: UserRepository

    override fun getAllUsers(): List<User> = userRepo.findAll()

    override fun addUser(firstName: String, lastName: String, password: String, calorieIntake: Int) {
        println("entered @add")
        val user = User(firstName = firstName, lastName = lastName, password = password, calorieIntake = calorieIntake)
        println("User stored : $user")
        userRepo.save(user)
    }

    @Transactional
    override fun updateUser(id: Int, firstName: String, lastName: String, password: String, calorieIntake: Int) =
        userRepo.findById(id).let {
            it.ifPresent { user ->
                user.firstName = firstName
                user.lastName = lastName
                user.password = password
                user.calorieIntake = calorieIntake
            }
        }

    override fun deleteUser(id: Int) {
        userRepo.deleteById(id)
    }

    @Transactional
    override fun syncDatabase(userList: MutableList<User>) {
        userRepo.findAll().forEach {
            userList.find { user -> user.id == it.id }?.let { user ->
                it.apply {
                    lastName = user.lastName
                    firstName = user.firstName
                    password = user.password
                    calorieIntake = user.calorieIntake
                }
                userList.remove(user)
            } ?: userRepo.delete(it)
        }
        userList.forEach {
            userRepo.save(it)
        }
    }

}