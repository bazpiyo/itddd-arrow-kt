package com.github.bazpiyo.itdddkt.webapplication.models.users.commons


data class UserResponseModel(
  val id: Int,
  val name: String,
  val type: String
) {
  companion object {
    fun of(id: Int, name: String, type: String): UserResponseModel =
      UserResponseModel(id, name, type)
  }
}
