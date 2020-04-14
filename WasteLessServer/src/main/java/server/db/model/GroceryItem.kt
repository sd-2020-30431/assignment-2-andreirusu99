package server.db.model

import java.util.*
import javax.persistence.*

@Entity(name = "grocery_item")
data class GroceryItem (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false, name = "id")
    var id: Int = 0,

    @Column(name = "item_name")
    var itemName: String = "",

    @Column(name = "item_quantity")
    var itemQuantity: Int = 0,

    @Column(name = "calorie_value")
    var calorieValue: Int = 0,

    @Column(name = "purchase_date")
    var purchaseDate: Date,

    @Column(name = "consumption_date")
    var consumptionDate: Date,

    @Column(name = "expiration_date")
    var expirationDate: Date,

    @Column(name = "list_id")
    var listId: Int = 0
)