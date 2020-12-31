package com.github.bazpiyo.itdddkt.application.circles.create

data class CircleCreateResult(val createdCircleId: Int) {
  companion object {
    fun of(createdCircleId: Int): CircleCreateResult =
      CircleCreateResult(createdCircleId)
  }
}