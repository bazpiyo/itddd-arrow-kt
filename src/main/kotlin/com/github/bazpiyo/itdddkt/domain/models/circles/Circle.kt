package com.github.bazpiyo.itdddkt.domain.models.circles

import com.github.bazpiyo.itdddkt.common.exception.domain.ArgumentCircleNameException
import com.github.bazpiyo.itdddkt.common.exception.domain.CircleFullException
import com.github.bazpiyo.itdddkt.common.exception.domain.CircleJoinException
import com.github.bazpiyo.itdddkt.common.exception.domain.CircleLeaveException
import com.github.bazpiyo.itdddkt.domain.models.users.User
import com.github.bazpiyo.itdddkt.domain.models.users.UserId

data class Circle(
  val circleId: CircleId,
  val circleName: CircleName,
  val owner: UserId,
  val members: List<User>
) {

  fun circleNameValue(): String = circleName.value
  fun circleIdValue(): Int = circleId.value

  fun join(user: User, fullSpec: CircleFullSpecification): Circle =
    when {
      user.userId == owner -> throw CircleJoinException(circleId.value)
      fullSpec.isSatisfiedBy(this) -> throw CircleFullException(circleId.value)
      else -> copy(members = members + user)
    }

  fun leave(user: User, leaveSpecification: CircleLeaveSpecification): Circle =
    if (leaveSpecification.isLeave(circleId)) copy(members = members.filterNot { it == user })
    else throw CircleLeaveException(user.userIdValue())

  fun changeName(circleName: CircleName): Circle =
    copy(circleName = circleName)

  fun getMembers(containsOwner: Boolean = true): List<UserId> =
    if (containsOwner) listOf(owner) + members.map { it.userId }
    else members.map { it.userId }

  fun countMembers(): Int = members.size + 1

  companion object {
    fun of(circleId: CircleId, circleName: CircleName, owner: UserId, members: List<User>): Circle =
      Circle(
        circleId = circleId,
        circleName = circleName,
        owner = owner,
        members = members
      )
  }
}

data class CircleId(val value: Int) {
  companion object {
    fun of(value: Int): CircleId = CircleId(value)
  }
}

data class CircleName(val value: String) {
  companion object {
    fun of(circleName: String): CircleName =
      circleName.length.let {
        when {
          it < 3 -> throw ArgumentCircleNameException("3文字以上です。CircleName=${it}")
          it > 20 -> throw ArgumentCircleNameException("20文字以下です。CircleName=${it}")
          else -> CircleName(circleName)
        }
      }
  }
}
