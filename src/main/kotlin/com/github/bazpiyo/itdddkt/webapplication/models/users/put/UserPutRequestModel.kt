package com.github.bazpiyo.itdddkt.webapplication.models.users.put

data class UserPutRequestModel(val userName: String) {
  companion object {
    fun of(userName: String): UserPutRequestModel =
      UserPutRequestModel(userName)
  }
}
