package com.github.bazpiyo.itdddkt.infrastructure.queryservice

import com.github.bazpiyo.itdddkt.infrastructure.queryservice.datamodels.CircleMembersDataModel
import org.apache.ibatis.annotations.Mapper

@Mapper
interface CircleQueryMapper {
  fun getCircleMembers(limit: Int, offset: Int): List<CircleMembersDataModel>
}