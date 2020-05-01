package server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import server.db.model.User
import server.service.user.UserService

@RestController
@RequestMapping("/wasteless")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @RequestMapping("users/login", method = [RequestMethod.POST])
    fun loginUser(@RequestBody user: User): ResponseEntity<Int> =
            ResponseEntity.ok(userService.attemptLogin(user.firstName, user.lastName, user.password))

    @RequestMapping("/users/all", method = [RequestMethod.GET])
    fun getAllUsers(): ResponseEntity<List<User>> {
        println("\"/users/all\" received")
        return ResponseEntity.ok(userService.getAllUsers())
    }

    @RequestMapping("/users/add", method = [RequestMethod.POST])
    fun addUser(@RequestBody user: User) =
        ResponseEntity.ok(userService.addUser(user.firstName, user.lastName, user.password, user.calorieIntake))

    @RequestMapping("/users/update/{userId}", method = [RequestMethod.PUT])
    fun updateUser(@PathVariable userId: Int, @RequestBody calories: Int) =
        ResponseEntity.ok(userService.updateUser(userId, calories))

    @RequestMapping("/users/delete/{userId}", method = [RequestMethod.DELETE])
    fun deleteUser(@PathVariable userId: Int) =
        ResponseEntity.ok(userService.deleteUser(userId))

    @RequestMapping("/users/sync", method = [RequestMethod.POST])
    fun syncDatabase(@RequestBody userList: List<User>) =
        ResponseEntity.ok(userService.syncDatabase(userList.toMutableList()))
}