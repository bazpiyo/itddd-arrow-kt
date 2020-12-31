package com.github.bazpiyo.itdddkt.webapplication.models.circles.post

import java.net.URI

data class CirclePostResponseModel(
  val createdCircleId: Int
) {
  fun toURI(base: String): URI =
    URI.create("${base}/${createdCircleId}")

  companion object {
    fun of(createdCircleId: Int): CirclePostResponseModel =
      CirclePostResponseModel(createdCircleId)
  }
}