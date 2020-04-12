package server.db.model

import javax.persistence.*

@Entity(name = "user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false, name = "id")
    var id: Int = 0,

    @Column(name = "first_name")
    var firstName: String = "",

    @Column(name = "last_name")
    var lastName: String = "",

    @Column(name = "passwd")
    var password: String = "",

    @Column(name = "calorie_intake")
    var calorieIntake: Int = 0
)