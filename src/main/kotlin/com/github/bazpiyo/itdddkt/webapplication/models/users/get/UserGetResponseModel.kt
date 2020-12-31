package com.github.bazpiyo.itdddkt.webapplication.models.users.get

import com.github.bazpiyo.itdddkt.webapplication.models.users.commons.UserResponseModel

class UserGetResponseModel(
  val user: UserResponseModel
) {
  companion object {
    fun of(user: UserResponseModel): UserGetResponseModel =
      UserGetResponseModel(user)
  }
}
