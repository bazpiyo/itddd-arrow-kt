package com.github.bazpiyo.itdddkt.application.users.get

import com.github.bazpiyo.itdddkt.application.users.commons.UserData

data class UserGetResult(
  val user: UserData
) {
  companion object {
    fun of(user: UserData): UserGetResult = UserGetResult(user)
  }
}
