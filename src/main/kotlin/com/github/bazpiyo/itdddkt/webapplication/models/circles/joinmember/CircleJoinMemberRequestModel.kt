package com.github.bazpiyo.itdddkt.webapplication.models.circles.joinmember

data class CircleJoinMemberRequestModel(
  val userId: Int
) {
  companion object {
    fun of(userId: Int): CircleJoinMemberRequestModel =
      CircleJoinMemberRequestModel(userId)
  }
}