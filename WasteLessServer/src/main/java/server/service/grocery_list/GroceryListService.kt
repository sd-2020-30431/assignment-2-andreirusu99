package server.service.grocery_list

import server.db.model.GroceryList

interface GroceryListService {

    fun getUserLists(userId: Int): List<GroceryList>

    fun addList(userId: Int, listName: String)

    fun updateListName(listId: String, newName: String)

    fun deleteList(listId: Int)
}