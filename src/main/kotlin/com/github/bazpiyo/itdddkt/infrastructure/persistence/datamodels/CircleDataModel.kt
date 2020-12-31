package com.github.bazpiyo.itdddkt.infrastructure.persistence.datamodels

import com.github.bazpiyo.itdddkt.infrastructure.NoArg

@NoArg
data class CircleDataModel(
  val id: Int,
  val name: String,
  val ownerId: Int,
  val member: List<UserDataModel>
)
