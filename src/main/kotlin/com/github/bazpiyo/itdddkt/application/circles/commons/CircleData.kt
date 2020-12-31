package com.github.bazpiyo.itdddkt.application.circles.commons

import com.github.bazpiyo.itdddkt.domain.models.circles.Circle

data class CircleData(
  val id: Int,
  val name: String,
  val ownerId: Int,
  val memberIds: List<Int>
) {
  companion object {
    fun of(circle: Circle): CircleData =
      CircleData(
        circle.circleId.value,
        circle.circleName.value,
        circle.owner.value,
        circle.members.map { it.userId.value }
      )
  }
}