package com.github.bazpiyo.itdddkt.application.circles.update

import com.github.bazpiyo.itdddkt.common.exception.application.NotMatchTypeCircleIdException

data class CircleUpdateCommand(
  val name: String,
  val ownerId: Int
) {
  companion object {
    fun of(name: String, ownerId: Int): CircleUpdateCommand =
      CircleUpdateCommand(name, ownerId)

    fun of(name: String, ownerId: String): CircleUpdateCommand =
      ownerId.toIntOrNull()
        ?.let { CircleUpdateCommand(name, it) }
        ?: throw NotMatchTypeCircleIdException(ownerId)
  }
}