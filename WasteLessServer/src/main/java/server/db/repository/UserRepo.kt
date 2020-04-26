package server.db.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import server.db.model.User

@Repository
interface UserRepo : JpaRepository<User, Int>{
    @Query("SELECT * FROM user WHERE first_name = :firstName AND last_name = :lastName AND passwd = :password", nativeQuery = true)
    fun getMatchingUser(@Param("firstName") firstName: String,
                        @Param("lastName") lastName: String,
                        @Param("password") password: String) : List<User>
}

