package com.github.bazpiyo.itdddkt.domain.models.users

interface IUserFactory {
  fun create(userName: UserName): User
}
