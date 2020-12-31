package com.github.bazpiyo.itdddkt.application.users

import com.github.bazpiyo.itdddkt.application.users.commons.UserData
import com.github.bazpiyo.itdddkt.application.users.delete.UserDeleteCommand
import com.github.bazpiyo.itdddkt.application.users.get.UserGetCommand
import com.github.bazpiyo.itdddkt.application.users.get.UserGetResult
import com.github.bazpiyo.itdddkt.application.users.getall.UserGetAllResult
import com.github.bazpiyo.itdddkt.application.users.register.UserRegisterCommand
import com.github.bazpiyo.itdddkt.application.users.register.UserRegisterResult
import com.github.bazpiyo.itdddkt.application.users.update.UserUpdateCommand
import com.github.bazpiyo.itdddkt.common.exception.application.CanNotRegisterUserException
import com.github.bazpiyo.itdddkt.common.exception.application.NotFoundUserException
import com.github.bazpiyo.itdddkt.domain.models.users.IUserFactory
import com.github.bazpiyo.itdddkt.domain.models.users.IUserRepository
import com.github.bazpiyo.itdddkt.domain.models.users.UserId
import com.github.bazpiyo.itdddkt.domain.models.users.UserName
import com.github.bazpiyo.itdddkt.domain.services.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserApplicationService(
  private val userFactory: IUserFactory,
  private val userRepository: IUserRepository,
) {
  private val userService: UserService = UserService(userRepository)

  @Transactional(readOnly = true)
  fun getAll(): UserGetAllResult =
    userRepository.findAll()
      .map(UserData.Companion::of)
      .let(UserGetAllResult.Companion::of)

  @Transactional(readOnly = true)
  fun get(userGetCommand: UserGetCommand): UserGetResult =
    UserId.of(userGetCommand.id).let { id ->
      userRepository.find(id)
        ?.let(UserData.Companion::of)
        ?.let(UserGetResult.Companion::of)
        ?: throw NotFoundUserException(id.value)
    }

  @Transactional
  fun register(userRegisterCommand: UserRegisterCommand): UserRegisterResult {
    val name = UserName.of(userRegisterCommand.name)
    val user = userFactory.create(name)
    if (userService.exists(user)) throw CanNotRegisterUserException(user.userNameValue())
    userRepository.save(user)
    return UserRegisterResult.of(user.userId)
  }

  @Transactional
  fun update(userUpdateCommand: UserUpdateCommand) {
    val id = UserId.of(userUpdateCommand.id)
    val user = userRepository.find(id) ?: throw NotFoundUserException(id.value)
    val name = UserName.of(userUpdateCommand.name)
    val changeUser = user.changeName(name)
    if (userService.exists(changeUser)) throw CanNotRegisterUserException(changeUser.userNameValue())
    userRepository.save(changeUser)
  }

  @Transactional
  fun delete(userDeleteCommand: UserDeleteCommand) {
    val id = UserId.of(userDeleteCommand.id)
    val user = userRepository.find(id) ?: return
    userRepository.delete(user)
  }
}
