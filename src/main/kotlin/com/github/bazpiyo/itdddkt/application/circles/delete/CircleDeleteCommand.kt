package com.github.bazpiyo.itdddkt.application.circles.delete

import com.github.bazpiyo.itdddkt.common.exception.application.NotMatchTypeCircleIdException

data class CircleDeleteCommand(
  val id: Int
) {
  companion object {
    fun of(id: String): CircleDeleteCommand =
      id.toIntOrNull()
        ?.let { CircleDeleteCommand(it) }
        ?: throw NotMatchTypeCircleIdException(id)
  }
}