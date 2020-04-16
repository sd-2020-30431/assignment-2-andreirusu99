package server.db.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import server.db.model.GroceryItem

@Repository
interface GroceryItemRepo : JpaRepository<GroceryItem, Int> {

    @Query("SELECT * FROM grocery_item WHERE list_id = :listId", nativeQuery = true)
    fun getListItems(@Param("listId") listId: Int) : List<GroceryItem>
}