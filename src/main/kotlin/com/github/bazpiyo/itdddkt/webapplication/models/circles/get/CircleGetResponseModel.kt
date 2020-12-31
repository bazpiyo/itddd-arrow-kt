package com.github.bazpiyo.itdddkt.webapplication.models.circles.get

import com.github.bazpiyo.itdddkt.webapplication.models.circles.commons.CircleResponseModel

data class CircleGetResponseModel(
  val circle: CircleResponseModel
) {
  companion object {
    fun of(circle: CircleResponseModel): CircleGetResponseModel =
      CircleGetResponseModel(circle)
  }
}