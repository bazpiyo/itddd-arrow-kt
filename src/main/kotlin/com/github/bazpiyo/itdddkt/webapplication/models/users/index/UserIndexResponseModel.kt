package com.github.bazpiyo.itdddkt.webapplication.models.users.index

import com.github.bazpiyo.itdddkt.webapplication.models.users.commons.UserResponseModel

data class UserIndexResponseModel(
  val users: List<UserResponseModel>
) {
  companion object {
    fun of(users: List<UserResponseModel>): UserIndexResponseModel =
      UserIndexResponseModel(users)
  }
}
