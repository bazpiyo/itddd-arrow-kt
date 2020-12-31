package com.github.bazpiyo.itdddkt.domain.models.circles

import com.github.bazpiyo.itdddkt.domain.models.users.IUserRepository
import com.github.bazpiyo.itdddkt.domain.models.users.User
import com.github.bazpiyo.itdddkt.domain.models.users.UserType


class CircleFullSpecification(private val userRepository: IUserRepository) {
  fun isSatisfiedBy(circle: Circle): Boolean =
    circle.countMembers().let { membersCount ->
      if (membersCount < 30) false
      else {
        val members = circle.getMembers()
        val users = userRepository.find(members)
        val premiumMemberCount = countPremiumMember(users)
        val max = if (premiumMemberCount > 10) 50 else 40
        membersCount >= max
      }
    }

  private fun countPremiumMember(members: List<User>): Int =
    members.filter { it.userType is UserType.Premium }.size

}