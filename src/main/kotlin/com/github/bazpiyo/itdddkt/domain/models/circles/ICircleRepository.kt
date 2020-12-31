package com.github.bazpiyo.itdddkt.domain.models.circles

interface ICircleRepository {
  fun find(circleId: CircleId): Circle?
  fun find(circleName: CircleName): Circle?
  fun findAll(): List<Circle>
  fun save(circle: Circle)
  fun delete(circle: Circle)
}