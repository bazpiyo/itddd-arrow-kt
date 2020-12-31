package com.github.bazpiyo.itdddkt.webapplication.models.circles.circlemembers

class CircleMembersResponseModel(
  val circleMembers: List<CircleMemberResponseModel>
) {
  companion object {
    fun of(circleMembers: List<CircleMemberResponseModel>): CircleMembersResponseModel =
      CircleMembersResponseModel(circleMembers)
  }
}