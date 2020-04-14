package server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import server.db.model.GroceryList
import server.service.grocery_list.GroceryListService

@RestController
@RequestMapping("/wasteless")
class GroceryListController {

    @Autowired
    private lateinit var groceryListService: GroceryListService

    @RequestMapping("/lists/{userId}", method = [RequestMethod.GET])
    fun getUserLists(@PathVariable userId: Int): ResponseEntity<List<GroceryList>>{
        return ResponseEntity.ok(groceryListService.getUserLists(userId))
    }


}