package server.db.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import server.db.model.GroceryItem

@Repository
interface GroceryItemRepo : JpaRepository<GroceryItem, Int> {
}