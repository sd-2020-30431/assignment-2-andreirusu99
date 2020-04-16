package server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import server.db.model.GroceryItem
import server.service.grocery_item.GroceryItemService

@RestController
@RequestMapping("/wasteless")
class GroceryItemController {

    @Autowired
    private lateinit var groceryItemService: GroceryItemService

    @RequestMapping("/items/get/{listId}", method = [RequestMethod.GET])
    fun getListItems(@PathVariable listId: Int): ResponseEntity<List<GroceryItem>> =
            ResponseEntity.ok(groceryItemService.getListItems(listId))

    @RequestMapping("/items/add/{listId}", method = [RequestMethod.POST])
    fun addItem(@PathVariable listId: Int, @RequestBody item: GroceryItem) =
            ResponseEntity.ok(groceryItemService.addItem(listId, item.itemName, item.itemQuantity, item.calorieValue, item.purchaseDate, item.consumptionDate, item.expirationDate))

    @RequestMapping("/items/update/{itemId}", method = [RequestMethod.PUT])
    fun updateItem(@PathVariable itemId: Int, @RequestBody item: GroceryItem) =
            ResponseEntity.ok(groceryItemService.updateItem(itemId, item.itemName, item.itemQuantity, item.calorieValue, item.purchaseDate, item.consumptionDate, item.expirationDate))

    @RequestMapping("/items/delete/{itemId}", method = [RequestMethod.DELETE])
    fun deleteItem(@PathVariable itemId: Int) =
            ResponseEntity.ok(groceryItemService.deleteItem(itemId))
}