package com.github.bazpiyo.itdddkt.application.circles.create

data class CircleCreateCommand(
  val name: String,
  val ownerId: Int
) {
  companion object {
    fun of(name: String, ownerId: Int): CircleCreateCommand =
      CircleCreateCommand(name, ownerId)
  }
}