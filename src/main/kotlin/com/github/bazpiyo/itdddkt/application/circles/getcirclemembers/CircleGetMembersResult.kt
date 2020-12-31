package com.github.bazpiyo.itdddkt.application.circles.getcirclemembers

data class CircleGetMembersResult(
  val circles: List<CircleMemberData>
) : List<CircleMemberData> by circles {
  companion object {
    fun of(circles: List<CircleMemberData>): CircleGetMembersResult =
      CircleGetMembersResult(circles)
  }
}