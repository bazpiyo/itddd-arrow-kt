package com.github.bazpiyo.itdddkt.application.circles.getall

import com.github.bazpiyo.itdddkt.application.circles.commons.CircleData

data class CircleGetAllResult(
  val circles: List<CircleData>
) : List<CircleData> by circles {
  companion object {
    fun of(circles: List<CircleData>): CircleGetAllResult = CircleGetAllResult(circles)
  }
}