package server.service.grocery_list

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import server.db.model.GroceryList
import server.db.repository.GroceryListRepo

@Service
class GroceryListServiceImpl : GroceryListService {

    @Autowired
    private lateinit var groceryListRepo: GroceryListRepo

    override fun getUserLists(userId: Int): List<GroceryList> =
        groceryListRepo.getUserLists(userId)

    override fun addList(userId: Int, listName: String) {
        TODO("Not yet implemented")
    }

    override fun updateListName(listId: String, newName: String) {
        TODO("Not yet implemented")
    }

    override fun deleteList(listId: Int) {
        TODO("Not yet implemented")
    }
}