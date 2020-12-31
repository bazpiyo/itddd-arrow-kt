package com.github.bazpiyo.itdddkt.application.circles

import com.github.bazpiyo.itdddkt.application.circles.commons.CircleData
import com.github.bazpiyo.itdddkt.application.circles.create.CircleCreateCommand
import com.github.bazpiyo.itdddkt.application.circles.create.CircleCreateResult
import com.github.bazpiyo.itdddkt.application.circles.delete.CircleDeleteCommand
import com.github.bazpiyo.itdddkt.application.circles.get.CircleGetCommand
import com.github.bazpiyo.itdddkt.application.circles.get.CircleGetResult
import com.github.bazpiyo.itdddkt.application.circles.getall.CircleGetAllResult
import com.github.bazpiyo.itdddkt.application.circles.join.CircleJoinCommand
import com.github.bazpiyo.itdddkt.application.circles.leave.CircleLeaveCommand
import com.github.bazpiyo.itdddkt.application.circles.update.CircleUpdateCommand
import com.github.bazpiyo.itdddkt.common.exception.application.CanNotRegisterCircleException
import com.github.bazpiyo.itdddkt.common.exception.application.NotFoundCirclesException
import com.github.bazpiyo.itdddkt.common.exception.application.NotFoundUserException
import com.github.bazpiyo.itdddkt.domain.models.circles.*
import com.github.bazpiyo.itdddkt.domain.models.users.IUserRepository
import com.github.bazpiyo.itdddkt.domain.models.users.UserId
import com.github.bazpiyo.itdddkt.domain.services.CircleService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CircleApplicationService(
  private val circleFactory: ICircleFactory,
  private val userRepository: IUserRepository,
  private val circleRepository: ICircleRepository,
) {
  private val circleService: CircleService = CircleService(circleRepository)
  private val circleFullSpecification: CircleFullSpecification =
    CircleFullSpecification(userRepository)
  private val circleLeaveSpecification: CircleLeaveSpecification =
    CircleLeaveSpecification(circleRepository)

  @Transactional(readOnly = true)
  fun getAll(): CircleGetAllResult =
    circleRepository
      .findAll()
      .map(CircleData.Companion::of)
      .let(CircleGetAllResult.Companion::of)

  @Transactional(readOnly = true)
  fun get(command: CircleGetCommand): CircleGetResult {
    val circleId = CircleId.of(command.id)
    return circleId.let(circleRepository::find)
      ?.let(CircleData.Companion::of)
      ?.let(CircleGetResult.Companion::of)
      ?: throw NotFoundCirclesException(circleId.value)
  }

  @Transactional
  fun create(command: CircleCreateCommand): CircleCreateResult {
    val userId = UserId.of(command.ownerId)
    val user = userRepository.find(userId) ?: throw NotFoundUserException(userId.value)
    val circleName = CircleName.of(command.name)
    val circle = circleFactory.create(circleName, user)
    if (circleService.exists(circle)) throw CanNotRegisterCircleException(circle.circleNameValue())
    circleRepository.save(circle)
    return CircleCreateResult(circle.circleIdValue())
  }

  @Transactional
  fun update(command: CircleUpdateCommand) {
    val circleId = CircleId.of(command.ownerId)
    val circle = circleRepository.find(circleId) ?: throw NotFoundCirclesException(circleId.value)
    val circleName = CircleName.of(command.name)
    val changeCircle = circle.changeName(circleName)
    circleRepository.save(changeCircle)
  }

  @Transactional
  fun join(command: CircleJoinCommand) {
    val circleId = CircleId.of(command.circleId)
    val circle = circleRepository.find(circleId) ?: throw NotFoundCirclesException(circleId.value)
    val userId = UserId.of(command.memberId)
    val user = userRepository.find(userId) ?: throw NotFoundUserException(userId.value)
    val updateCircle = circle.join(user, circleFullSpecification)
    circleRepository.save(updateCircle)
  }

  @Transactional
  fun leave(command: CircleLeaveCommand) {
    val circleId = CircleId.of(command.circleId)
    val circle = circleRepository.find(circleId) ?: throw NotFoundCirclesException(circleId.value)
    val userId = UserId.of(command.memberId)
    val user = userRepository.find(userId) ?: throw NotFoundUserException(userId.value)
    val updateCircle = circle.leave(user, circleLeaveSpecification)
    circleRepository.save(updateCircle)
  }

  @Transactional
  fun delete(command: CircleDeleteCommand) {
    val circleId = CircleId.of(command.id)
    val circle = circleRepository.find(circleId) ?: return
    circleRepository.delete(circle)
  }

}