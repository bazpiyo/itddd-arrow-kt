package com.github.bazpiyo.itdddkt.webapplication.models.circles.post

data class CirclePostRequestModel(
  val circleName: String,
  val ownerId: Int
) {
  companion object {
    fun of(circleName: String, ownerId: Int): CirclePostRequestModel =
      CirclePostRequestModel(circleName, ownerId)
  }
}