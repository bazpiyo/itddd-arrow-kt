package com.github.bazpiyo.itdddkt.infrastructure.persistence.users

import com.github.bazpiyo.itdddkt.domain.models.users.User
import com.github.bazpiyo.itdddkt.domain.models.users.UserId
import com.github.bazpiyo.itdddkt.domain.models.users.UserName
import com.github.bazpiyo.itdddkt.infrastructure.persistence.datamodels.UserDataModel
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface UserMapper {
  fun nextId(): Int
  fun findAll(): List<UserDataModel>
  fun findById(@Param("id") userId: UserId): UserDataModel?
  fun findByName(@Param("userName") userName: UserName): UserDataModel?
  fun findByIds(@Param("ids") ids: List<UserId>): List<UserDataModel>
  fun save(@Param("user") user: User)
  fun delete(@Param("userId") userId: UserId)

}
