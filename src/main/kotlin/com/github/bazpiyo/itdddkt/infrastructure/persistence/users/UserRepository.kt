package com.github.bazpiyo.itdddkt.infrastructure.persistence.users

import com.github.bazpiyo.itdddkt.domain.models.users.IUserRepository
import com.github.bazpiyo.itdddkt.domain.models.users.User
import com.github.bazpiyo.itdddkt.domain.models.users.UserId
import com.github.bazpiyo.itdddkt.domain.models.users.UserName
import com.github.bazpiyo.itdddkt.infrastructure.persistence.UserDataModelUtilTrait
import org.springframework.stereotype.Repository

@Repository
class UserRepository(private val mybatisUserMapper: UserMapper) : IUserRepository,
  UserDataModelUtilTrait {
  override fun findAll(): List<User> =
    mybatisUserMapper
      .findAll()
      .toListUser()

  override fun find(userId: UserId): User? =
    mybatisUserMapper.findById(userId)?.toUser()

  override fun find(userName: UserName): User? =
    mybatisUserMapper.findByName(userName)?.toUser()

  override fun find(ids: List<UserId>): List<User> =
    mybatisUserMapper
      .findByIds(ids)
      .toListUser()

  override fun save(user: User): Unit =
    mybatisUserMapper.save(user)

  override fun delete(user: User): Unit =
    mybatisUserMapper.delete(user.userId)

}
