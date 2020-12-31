package com.github.bazpiyo.itdddkt.application.users.commons

import com.github.bazpiyo.itdddkt.domain.models.users.User

data class UserData(
  val id: Int,
  val name: String,
  val type: String
) {
  companion object {
    fun of(user: User): UserData = UserData(
      user.userId.value,
      user.userName.value,
      user.userType.name
    )
  }
}
