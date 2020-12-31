package com.github.bazpiyo.itdddkt.webapplication.models.circles.index

import com.github.bazpiyo.itdddkt.webapplication.models.circles.commons.CircleResponseModel

data class CircleIndexResponseModel(
  val circles: List<CircleResponseModel>
) {
  companion object {
    fun of(circles: List<CircleResponseModel>): CircleIndexResponseModel =
      CircleIndexResponseModel(circles)
  }
}