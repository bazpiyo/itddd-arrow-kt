package com.github.bazpiyo.itdddkt.application.users.update

import com.github.bazpiyo.itdddkt.common.exception.application.NotMatchTypeUserIdException

class UserUpdateCommand(
  val id: Int,
  val name: String
) {
  companion object {
    fun of(id: String, name: String): UserUpdateCommand =
      id.toIntOrNull()
        ?.let { UserUpdateCommand(it, name) }
        ?: throw NotMatchTypeUserIdException(id)
  }
}
