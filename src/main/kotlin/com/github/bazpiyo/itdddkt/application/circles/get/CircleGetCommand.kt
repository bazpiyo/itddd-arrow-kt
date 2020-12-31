package com.github.bazpiyo.itdddkt.application.circles.get

import com.github.bazpiyo.itdddkt.common.exception.application.NotMatchTypeCircleIdException

data class CircleGetCommand(
  val id: Int
) {
  companion object {
    fun of(id: Int): CircleGetCommand =
      CircleGetCommand(id)

    fun of(id: String): CircleGetCommand =
      id.toIntOrNull()
        ?.let { CircleGetCommand(it) }
        ?: throw NotMatchTypeCircleIdException(id)
  }
}