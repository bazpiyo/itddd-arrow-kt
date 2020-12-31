package com.github.bazpiyo.itdddkt.application.users.register

import com.github.bazpiyo.itdddkt.domain.models.users.UserId

data class UserRegisterResult(val createdUserId: Int) {
  companion object {
    fun of(createdUserId: UserId): UserRegisterResult = UserRegisterResult(createdUserId.value)
  }
}
