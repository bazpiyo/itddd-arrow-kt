package com.github.bazpiyo.itdddkt.domain.models.circles

import com.github.bazpiyo.itdddkt.domain.models.users.User

interface ICircleFactory {
  fun create(circleName: CircleName, owner: User): Circle
}