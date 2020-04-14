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

    override fun getAllItems(listId: Int): List<GroceryItem> {
        TODO("Not yet implemented")
    }

    override fun addItem(
        listId: Int,
        itemName: String,
        itemQuantity: Int,
        calorieValue: Int,
        purchaseDate: Date,
        consumptionDate: Date,
        expirationDate: Date
    ) {
        TODO("Not yet implemented")
    }

    override fun removeItem(itemId: Int) {
        TODO("Not yet implemented")
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
        TODO("Not yet implemented")
    }

    override fun deleteItem(itemId: Int) {
        TODO("Not yet implemented")
    }
}