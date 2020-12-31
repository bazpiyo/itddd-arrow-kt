package com.github.bazpiyo.itdddkt.application.users.getall

import com.github.bazpiyo.itdddkt.application.users.commons.UserData

data class UserGetAllResult(
  val users: List<UserData>
) : List<UserData> by users {
  companion object {
    fun of(users: List<UserData>): UserGetAllResult = UserGetAllResult(users)
  }
}
