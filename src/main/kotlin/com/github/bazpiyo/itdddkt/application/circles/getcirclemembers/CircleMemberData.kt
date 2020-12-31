package com.github.bazpiyo.itdddkt.application.circles.getcirclemembers

data class CircleMemberData(
  val circleId: Int,
  val circleName: String,
  val ownerId: Int,
  val ownerName: String,
  val ownerType: Int,
  val members: List<MemberData>
)