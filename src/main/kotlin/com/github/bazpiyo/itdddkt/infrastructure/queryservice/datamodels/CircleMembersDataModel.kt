package com.github.bazpiyo.itdddkt.infrastructure.queryservice.datamodels

import com.github.bazpiyo.itdddkt.infrastructure.NoArg

@NoArg
data class CircleMembersDataModel(
  val circleId: Int,
  val circleName: String,
  val ownerId: Int,
  val ownerName: String,
  val ownerType: Int,
  val members: List<MemberDataModel>
)