package server.service.grocery_list

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import server.db.model.GroceryList
import server.db.repository.GroceryListRepo
import javax.transaction.Transactional

@Service
class GroceryListServiceImpl : GroceryListService {

    @Autowired
    private lateinit var groceryListRepo: GroceryListRepo

    override fun getUserLists(userId: Int): List<GroceryList> =
        groceryListRepo.getUserLists(userId)

    override fun addList(userId: Int, listName: String) {
        val groceryList = GroceryList(listName = listName, userId = userId)
        println("List {} stored".format(listName))
        groceryListRepo.save(groceryList)
    }

    @Transactional
    override fun updateList(listId: Int, newName: String) {
        groceryListRepo.findById(listId).let {
            it.ifPresent{ list ->
                list.listName = newName
            }
        }
    }

    override fun deleteList(listId: Int) {
        groceryListRepo.deleteById(listId)
    }
}