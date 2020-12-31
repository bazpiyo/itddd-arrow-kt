package com.github.bazpiyo.itdddkt.infrastructure.persistence.circles

import com.github.bazpiyo.itdddkt.domain.models.circles.Circle
import com.github.bazpiyo.itdddkt.domain.models.circles.CircleId
import com.github.bazpiyo.itdddkt.domain.models.circles.CircleName
import com.github.bazpiyo.itdddkt.domain.models.circles.ICircleFactory
import com.github.bazpiyo.itdddkt.domain.models.users.User
import org.springframework.stereotype.Repository

@Repository
class CircleFactory(private val mapper: CircleMapper) : ICircleFactory {
  override fun create(circleName: CircleName, owner: User): Circle =
    mapper.nextId().let { id ->
      Circle.of(
        CircleId.of(id),
        circleName,
        owner.userId,
        emptyList()
      )
    }
}