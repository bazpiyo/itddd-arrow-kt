package com.github.bazpiyo.itdddkt.application.users.delete

import com.github.bazpiyo.itdddkt.common.exception.application.NotMatchTypeUserIdException

data class UserDeleteCommand(
  val id: Int
) {
  companion object {
    fun of(value: String): UserDeleteCommand =
      value.toIntOrNull()
        ?.let(::UserDeleteCommand)
        ?: throw NotMatchTypeUserIdException(value)
  }
}
