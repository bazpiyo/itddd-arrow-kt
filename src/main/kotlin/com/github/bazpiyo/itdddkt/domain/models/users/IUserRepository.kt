package com.github.bazpiyo.itdddkt.domain.models.users

interface IUserRepository {
  fun find(userId: UserId): User?
  fun find(userName: UserName): User?
  fun find(ids: List<UserId>): List<User>
  fun findAll(): List<User>
  fun save(user: User)
  fun delete(user: User)
}
