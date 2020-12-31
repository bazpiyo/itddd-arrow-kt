package com.github.bazpiyo.itdddkt.webapplication.models.circles.circlemembers


class CircleMemberResponseModel(
  val circleId: Int,
  val circleName: String,
  val ownerId: Int,
  val ownerName: String,
  val ownerType: Int,
  val members: List<MemberResponseModel>
)