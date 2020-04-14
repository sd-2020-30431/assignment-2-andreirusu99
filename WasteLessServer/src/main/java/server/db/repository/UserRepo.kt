package server.db.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import server.db.model.User

@Repository
interface UserRepo : JpaRepository<User, Int>