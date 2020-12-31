package com.github.bazpiyo.itdddkt.webapplication.models.circles.put

data class CirclePutRequestModel(val circleName: String) {
  companion object {
    fun of(circleName: String): CirclePutRequestModel =
      CirclePutRequestModel(circleName)
  }
}