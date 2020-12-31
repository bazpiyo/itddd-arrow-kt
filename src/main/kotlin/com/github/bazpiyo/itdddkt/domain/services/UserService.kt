package com.github.bazpiyo.itdddkt.domain.services

import com.github.bazpiyo.itdddkt.domain.models.users.IUserRepository
import com.github.bazpiyo.itdddkt.domain.models.users.User

class UserService(private val userRepository: IUserRepository) {
  fun exists(user: User): Boolean =
    userRepository.find(user.userName) != null
}
