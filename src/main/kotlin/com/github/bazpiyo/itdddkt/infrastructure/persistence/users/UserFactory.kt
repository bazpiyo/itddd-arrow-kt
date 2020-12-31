package com.github.bazpiyo.itdddkt.infrastructure.persistence.users

import com.github.bazpiyo.itdddkt.domain.models.users.*
import org.springframework.stereotype.Repository

@Repository
class UserFactory(private val mapper: UserMapper) : IUserFactory {
  override fun create(userName: UserName): User {
    val nextId = mapper.nextId()
    return User.of(
      userId = UserId.of(nextId),
      userName = userName,
      userType = UserType.Normal
    )
  }
}
