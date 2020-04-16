package server.service.grocery_item

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import server.db.model.GroceryItem
import server.db.repository.GroceryItemRepo
import java.util.*
import javax.transaction.Transactional

@Service
class GroceryItemServiceImpl : GroceryItemService{

    @Autowired
    private lateinit var groceryItemRepo: GroceryItemRepo

    override fun getListItems(listId: Int): List<GroceryItem> =
        groceryItemRepo.getListItems(listId)


    override fun addItem(listId: Int, itemName: String, itemQuantity: Int, calorieValue: Int, purchaseDate: Date, consumptionDate: Date, expirationDate: Date
    ) {
        val groceryItem = GroceryItem(itemName = itemName, itemQuantity = itemQuantity, calorieValue = calorieValue, purchaseDate = purchaseDate, consumptionDate = consumptionDate, expirationDate = expirationDate)
        println("Item {} stored".format(itemName))
        groceryItemRepo.save(groceryItem)
    }

    @Transactional
    override fun updateItem(
        itemId: Int,
        itemName: String,
        itemQuantity: Int,
        calorieValue: Int,
        purchaseDate: Date,
        consumptionDate: Date,
        expirationDate: Date
    ) {
        groceryItemRepo.findById(itemId).let {
            it.ifPresent{item ->
                item.itemName = itemName
                item.itemQuantity = itemQuantity
                item.calorieValue = calorieValue
                item.purchaseDate = purchaseDate
                item.consumptionDate = consumptionDate
                item.expirationDate = expirationDate
            }
        }
    }

    override fun deleteItem(itemId: Int) {
        groceryItemRepo.deleteById(itemId)
    }
}