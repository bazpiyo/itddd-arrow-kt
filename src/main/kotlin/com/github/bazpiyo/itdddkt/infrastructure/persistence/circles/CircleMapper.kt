package com.github.bazpiyo.itdddkt.infrastructure.persistence.circles

import com.github.bazpiyo.itdddkt.domain.models.circles.Circle
import com.github.bazpiyo.itdddkt.domain.models.circles.CircleId
import com.github.bazpiyo.itdddkt.domain.models.circles.CircleName
import com.github.bazpiyo.itdddkt.domain.models.users.UserId
import com.github.bazpiyo.itdddkt.infrastructure.persistence.datamodels.CircleDataModel
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface CircleMapper {
  fun nextId(): Int
  fun findAll(): List<CircleDataModel>
  fun findById(@Param("circleId") circleId: CircleId): CircleDataModel?
  fun findByName(@Param("circleName") circleName: CircleName): CircleDataModel?
  fun save(@Param("circle") circle: Circle)
  fun saveMember(
    @Param("userId") userId: UserId,
    @Param("circleId") circleId: CircleId
  )

  fun deleteMember(
    @Param("userId") userId: UserId,
    @Param("circleId") circleId: CircleId
  )

  fun delete(@Param("circleId") circleId: CircleId)
  fun deleteAllMember(@Param("circleId") circleId: CircleId)
}