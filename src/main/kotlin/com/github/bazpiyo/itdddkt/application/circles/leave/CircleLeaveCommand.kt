package com.github.bazpiyo.itdddkt.application.circles.leave

import com.github.bazpiyo.itdddkt.common.exception.application.NotMatchTypeCircleIdException

data class CircleLeaveCommand(
  val circleId: Int,
  val memberId: Int
) {
  companion object {
    fun of(circleId: String, memberId: Int): CircleLeaveCommand =
      circleId.toIntOrNull()
        ?.let { CircleLeaveCommand(it, memberId) }
        ?: throw NotMatchTypeCircleIdException(circleId)
  }
}