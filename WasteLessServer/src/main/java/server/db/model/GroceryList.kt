package server.db.model

import javax.persistence.*

@Entity(name = "grocery_list")
data class GroceryList (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false, name = "id")
    var id: Int = 0,

    @Column(name = "list_name")
    var listName: String = "",

    @Column(name = "user_id")
    var userId: Int = 0
)