package com.github.bazpiyo.itdddkt.webapplication.models.users.post

class UserPostRequestModel(
  val userName: String
) {
  companion object {
    fun of(userName: String): UserPostRequestModel =
      UserPostRequestModel(userName)
  }
}
