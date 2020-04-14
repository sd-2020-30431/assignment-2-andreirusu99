package server.db.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import server.db.model.GroceryList

@Repository
interface GroceryListRepo : JpaRepository<GroceryList, Int>{

    @Query("SELECT * FROM grocery_list WHERE user_id = :userId", nativeQuery = true)
    fun getUserLists(@Param("userId") userId: Int) : List<GroceryList>

}