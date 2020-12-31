package com.github.bazpiyo.itdddkt.webapplication.models.circles.leavemember

data class CircleLeaveMemberRequestModel(
  val userId: Int
) {
  companion object {
    fun of(userId: Int): CircleLeaveMemberRequestModel =
      CircleLeaveMemberRequestModel(userId)
  }
}