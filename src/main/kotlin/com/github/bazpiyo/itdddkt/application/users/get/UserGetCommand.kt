package com.github.bazpiyo.itdddkt.application.users.get

import com.github.bazpiyo.itdddkt.common.exception.application.NotMatchTypeUserIdException

data class UserGetCommand(
  val id: Int
) {
  companion object {
    fun of(value: String): UserGetCommand =
      value.toIntOrNull()
        ?.let { UserGetCommand(it) }
        ?: throw NotMatchTypeUserIdException(value)
  }
}
