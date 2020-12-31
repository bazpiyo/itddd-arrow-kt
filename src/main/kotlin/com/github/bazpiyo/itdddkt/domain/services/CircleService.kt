package com.github.bazpiyo.itdddkt.domain.services

import com.github.bazpiyo.itdddkt.domain.models.circles.Circle
import com.github.bazpiyo.itdddkt.domain.models.circles.ICircleRepository

class CircleService(private val circleRepository: ICircleRepository) {
  fun exists(circle: Circle): Boolean =
    circleRepository.find(circleName = circle.circleName) != null
}