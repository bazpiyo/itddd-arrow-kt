package com.github.bazpiyo.itdddkt.application.circles.get

import com.github.bazpiyo.itdddkt.application.circles.commons.CircleData

data class CircleGetResult(
  val circle: CircleData
) {
  companion object {
    fun of(circleData: CircleData): CircleGetResult =
      CircleGetResult(circleData)
  }
}