package com.github.bazpiyo.itdddkt.infrastructure.persistence

import com.github.bazpiyo.itdddkt.domain.models.circles.Circle
import com.github.bazpiyo.itdddkt.domain.models.circles.CircleId
import com.github.bazpiyo.itdddkt.domain.models.circles.CircleName
import com.github.bazpiyo.itdddkt.domain.models.users.User
import com.github.bazpiyo.itdddkt.domain.models.users.UserId
import com.github.bazpiyo.itdddkt.domain.models.users.UserName
import com.github.bazpiyo.itdddkt.domain.models.users.UserType
import com.github.bazpiyo.itdddkt.infrastructure.persistence.datamodels.CircleDataModel
import com.github.bazpiyo.itdddkt.infrastructure.persistence.datamodels.UserDataModel

interface DataModelUtilTrait : CircleDataModelUtilTrait

interface CircleDataModelUtilTrait : UserDataModelUtilTrait {
  fun List<CircleDataModel>.toListCircle(): List<Circle> =
    map { it.toCircle() }

  fun CircleDataModel.toCircle(): Circle =
    Circle.of(
      CircleId.of(id),
      CircleName.of(name),
      UserId.of(ownerId),
      member.toListUser()
    )
}

interface UserDataModelUtilTrait {
  fun UserDataModel.toUser(): User = User.of(
    userId = UserId.of(this.id),
    userName = UserName.of(this.name),
    userType = UserType.of(this.type)
  )

  fun List<UserDataModel>.toListUser(): List<User> =
    map { it.toUser() }
}