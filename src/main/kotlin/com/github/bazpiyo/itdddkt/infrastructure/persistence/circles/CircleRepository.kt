package com.github.bazpiyo.itdddkt.infrastructure.persistence.circles

import com.github.bazpiyo.itdddkt.common.exception.infrastructure.CircleTableException
import com.github.bazpiyo.itdddkt.domain.models.circles.Circle
import com.github.bazpiyo.itdddkt.domain.models.circles.CircleId
import com.github.bazpiyo.itdddkt.domain.models.circles.CircleName
import com.github.bazpiyo.itdddkt.domain.models.circles.ICircleRepository
import com.github.bazpiyo.itdddkt.domain.models.users.User
import com.github.bazpiyo.itdddkt.infrastructure.persistence.CircleDataModelUtilTrait
import org.springframework.stereotype.Repository

@Repository
class CircleRepository(private val mapper: CircleMapper) : ICircleRepository, CircleDataModelUtilTrait {
  override fun findAll(): List<Circle> =
    mapper.findAll().toListCircle()

  override fun find(circleId: CircleId): Circle? =
    mapper.findById(circleId)?.toCircle()

  override fun find(circleName: CircleName): Circle? =
    mapper.findByName(circleName)?.toCircle()

  override fun save(circle: Circle) {
    val oldCircle = mapper.findById(circle.circleId)?.toCircle()
    val oldMembers = oldCircle?.members
    val nowMembers = circle.members
    when {
      oldMembers == null -> upsertCircle(circle)
      oldMembers.size < nowMembers.size -> upsertCircle(circle)
      oldMembers.size == nowMembers.size -> upsertCircle(circle)
      oldMembers.size > nowMembers.size -> deleteMember(oldMembers, nowMembers, circle.circleId)
      else -> throw CircleTableException
    }
  }

  override fun delete(circle: Circle) {
    val id = circle.circleId
    mapper.deleteAllMember(id)
    mapper.delete(id)
  }

  private fun upsertCircle(circle: Circle) {
    mapper.save(circle)
    circle.members
      .map { it.userId to circle.circleId }
      .forEach { mapper.saveMember(it.first, it.second) }
  }

  private fun deleteMember(oldMembers: List<User>, nowMembers: List<User>, circleId: CircleId) {
    oldMembers
      .filter { !nowMembers.contains(it) }
      .forEach {
        mapper.deleteMember(circleId = circleId, userId = it.userId)
      }
  }
}