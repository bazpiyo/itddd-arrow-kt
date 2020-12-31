package com.github.bazpiyo.itdddkt.domain.models.circles

class CircleLeaveSpecification(private val circleRepository: ICircleRepository) {
  fun isLeave(circleId: CircleId): Boolean {
    val circle = circleRepository.find(circleId) ?: return false
    return when {
      circle.members.isEmpty() -> false
      else -> true
    }
  }
}