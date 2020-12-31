package com.github.bazpiyo.itdddkt.infrastructure.persistence.datamodels

import com.github.bazpiyo.itdddkt.infrastructure.NoArg

@NoArg
data class UserDataModel(
  val id: Int,
  val name: String,
  val type: Int
)
