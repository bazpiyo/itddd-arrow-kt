package com.github.bazpiyo.itdddkt.webapplication.models.users.post

import java.net.URI

data class UserPostResponseModel(
  val createdUserId: Int
) {
  fun toURI(base: String): URI =
    URI.create("${base}/${createdUserId}")

  companion object {
    fun of(createdUserId: Int): UserPostResponseModel =
      UserPostResponseModel(createdUserId)
  }
}
