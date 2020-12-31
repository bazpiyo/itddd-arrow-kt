package com.github.bazpiyo.itdddkt.infrastructure.queryservice.datamodels

import com.github.bazpiyo.itdddkt.infrastructure.NoArg

@NoArg
class MemberDataModel(
  val memberId: Int,
  val memberName: String,
  val memberType: Int
)
