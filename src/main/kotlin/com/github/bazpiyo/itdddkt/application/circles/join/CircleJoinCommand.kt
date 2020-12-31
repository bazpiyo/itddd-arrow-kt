package com.github.bazpiyo.itdddkt.application.circles.join

import com.github.bazpiyo.itdddkt.common.exception.application.NotMatchTypeCircleIdException

data class CircleJoinCommand(
  val circleId: Int,
  val memberId: Int
) {
  companion object {
    fun of(circleId: String, memberId: Int): CircleJoinCommand =
      circleId.toIntOrNull()
        ?.let { CircleJoinCommand(it, memberId) }
        ?: throw NotMatchTypeCircleIdException(circleId)
  }
}